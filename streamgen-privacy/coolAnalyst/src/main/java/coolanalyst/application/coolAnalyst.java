package coolanalyst.application;

import coolanalyst.datatypes.roomStatistics;
import coolanalyst.datatypes.roomTemp;
import coolanalyst.datatypes.tempPred;

import coolanalyst.functions.TempParser;
import coolanalyst.functions.RoomStatistics;
import coolanalyst.functions.TemperaturePredictor;

import java.util.*;
import java.util.concurrent.TimeUnit;
import org.apache.flink.api.common.JobExecutionResult;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.windowing.time.Time;
import org.apache.flink.core.fs.FileSystem.WriteMode;



import org.apache.commons.io.FileUtils;
import java.io.File;
import org.yaml.snakeyaml.Yaml;
import it.deib.polimi.diaprivacy.library.GeneralizationFunction;
import it.deib.polimi.diaprivacy.library.ProtectedStream;
import it.deib.polimi.diaprivacy.model.ApplicationDataStream;
import it.deib.polimi.diaprivacy.model.ApplicationPrivacy;
import it.deib.polimi.diaprivacy.model.DSEP;
import it.deib.polimi.diaprivacy.model.PrivacyContext;
import it.deib.polimi.diaprivacy.library.PrivacyContextParser;
import it.deib.polimi.diaprivacy.model.VCP;
import it.deib.polimi.diaprivacy.library.PrivacyContextFixedSource;


public class coolAnalyst {

    public static void main(String[] args) throws Exception {
		
		final StreamExecutionEnvironment env = StreamExecutionEnvironment
               .getExecutionEnvironment();
       
       //uncomment the below if you want to set the default parallelism for the project.
       //env.setParallelism(1);

			// privacy init
			// begin privacy source definition
			DataStream<String> contextString = env.readTextFile("/home/cablan/Desktop/thesis/conf/observers.txt")
				;
		
			DataStream<PrivacyContext> contextStream = contextString.map(new PrivacyContextParser());
			// finish privacy source definition
		
			Yaml yaml = new Yaml();
			
			String content = FileUtils.readFileToString(new File("/home/cablan/Desktop/thesis/conf/privacy-config.yml"), "UTF-8");
			
			ApplicationPrivacy app = yaml.loadAs(content, ApplicationPrivacy.class);
		
			// finish privacy init


			// begin input stream definition
			DataStream<String> roomOneStream = env.socketTextStream("localhost", 8888);
			// finish input stream definition
			// begin input stream definition
			DataStream<String> roomTwoStream = env.socketTextStream("localhost", 8887);
			// finish input stream definition
		
			// begin stream definition
			DataStream<roomTemp> tempTupleStream = roomOneStream
				.connect(
				roomTwoStream
				)
				.map(new TempParser())
				;
		
				app.getStreamByID("tempTupleStream").setConcreteStream(tempTupleStream);
				// finish stream definition
			
			// begin stream privacy conf
			ApplicationDataStream app_tempTupleStream = app.getStreamByID("tempTupleStream");
					
			ProtectedStream<roomTemp> tempTupleStream_p = 
				new ProtectedStream<roomTemp>(false,"",-1,1,false,0,"/home/cablan/Desktop/thesis/conf/");
			
			tempTupleStream_p.setStreamToProtect((DataStream<roomTemp>) app_tempTupleStream.getConcreteStream());
			
				for (DSEP dsep : app.getDSEPs(app_tempTupleStream.getId())) {
					tempTupleStream_p.addDSEP(app_tempTupleStream, (DSEP) dsep, app);
				}
			
			DataStream<roomTemp> tempTupleStream_f = tempTupleStream_p.finalize(env, contextStream);
			// finish stream privacy conf
		
			// begin stream definition
			DataStream<roomStatistics> statTupleStream =
				tempTupleStream_f
		      	.keyBy("roomId")
		        .timeWindow(Time.seconds(1))
		        .apply(new RoomStatistics())
				;
		
			app.getStreamByID("statTupleStream").setConcreteStream(statTupleStream);
			// finish stream definition
		
			// begin stream definition
			DataStream<tempPred> predTupleStream =
				statTupleStream
		      	.keyBy("roomId")
		        .timeWindow(Time.seconds(1))
		        .apply(new TemperaturePredictor())
				;
		
			app.getStreamByID("predTupleStream").setConcreteStream(predTupleStream);
			// finish stream definition
		
			// begin sink definition
			predTupleStream
			.keyBy("roomId")
			.writeAsText("/home/cablan/Desktop/thesisFiles/outputs/preds.txt", WriteMode.OVERWRITE)
			.setParallelism(1);
			// finish sink definition
		
			// begin stream definition
			DataStream<roomTemp> cleanDataStream =
				tempTupleStream
		      	.keyBy("roomId")
				.filter((roomTemp tuple) -> tuple.getRoomTemp() < 9999 & 
		tuple.getRoomTemp() > -9999 & 
		tuple.getRoomTemp() != null  &
		tuple.getRoomId() !=  null)
				;
		
			app.getStreamByID("cleanDataStream").setConcreteStream(cleanDataStream);
			// finish stream definition
		
			// begin sink definition
			cleanDataStream
			.writeAsText("/home/cablan/Desktop/thesisFiles/outputs/cleanData.txt", WriteMode.OVERWRITE)
			.setParallelism(1);
			// finish sink definition

       JobExecutionResult result = env.execute();
       System.out.println("EXECUTION TIME: " + result.getNetRuntime(TimeUnit.SECONDS));

    }
}
