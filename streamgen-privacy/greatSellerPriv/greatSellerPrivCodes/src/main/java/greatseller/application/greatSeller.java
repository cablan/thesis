package greatseller.application;

import greatseller.datatypes.InputTransaction;
import greatseller.datatypes.IssuedTransactions;
import greatseller.datatypes.SpentAmount;
import greatseller.datatypes.NumberUsers;

import greatseller.functions.TupleParser;
import greatseller.functions.OP1;
import greatseller.functions.OP2;
import greatseller.functions.OP3;

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


public class greatSeller {

    public static void main(String[] args) throws Exception {
		
		final StreamExecutionEnvironment env = StreamExecutionEnvironment
               .getExecutionEnvironment();
       
       //uncomment the below if you want to set the default parallelism for the project.
       //env.setParallelism(1);

			// privacy init
			// begin privacy source definition
			DataStream<String> contextString = env.readTextFile("/home/cablan/Desktop/thesisFiles/inputs/scvFile.txt")
				;
		
			DataStream<PrivacyContext> contextStream = contextString.map(new PrivacyContextParser());
			// finish privacy source definition
		
			Yaml yaml = new Yaml();
			
			String content = FileUtils.readFileToString(new File("/home/cablan/Desktop/thesisFiles/config/privacy-config.yml"), "UTF-8");
			
			ApplicationPrivacy app = yaml.loadAs(content, ApplicationPrivacy.class);
		
			// finish privacy init


			// begin input stream definition
			DataStream<String> rawTuples = env.socketTextStream("localhost", 9999);
			// finish input stream definition
		
			// begin stream definition
			DataStream<InputTransaction> streamS1 = 
				rawTuples
				.map(new TupleParser())
				;
		
			app.getStreamByID("streamS1").setConcreteStream(streamS1);
			// finish stream definition
		
			// begin stream definition
			DataStream<IssuedTransactions> streamS2 =
				streamS1
		      	.keyBy("dataSubject")
		        .timeWindow(Time.seconds(10))
		        .apply(new OP1())
				;
		
			app.getStreamByID("streamS2").setConcreteStream(streamS2);
			// finish stream definition
			
			// begin stream privacy conf
			ApplicationDataStream app_streamS2 = app.getStreamByID("streamS2");
					
			ProtectedStream<IssuedTransactions> streamS2_p = 
				new ProtectedStream<IssuedTransactions>(false,"",-1,1,false,0,"/home/cablan/Desktop/thesisFiles/log/greatSeller");
			
			streamS2_p.setStreamToProtect((DataStream<IssuedTransactions>) app_streamS2.getConcreteStream());
			
				for (VCP vcp : app.getVCPs(app_streamS2.getId())) {
					streamS2_p.addVCP(app_streamS2, (VCP) vcp, app);
				}
			
			DataStream<IssuedTransactions> streamS2_f = streamS2_p.finalize(env, contextStream);
			// finish stream privacy conf
		
			// begin stream definition
			DataStream<SpentAmount> streamS3 =
				streamS1
		      	.keyBy("dataSubject")
		        .timeWindow(Time.seconds(10))
		        .apply(new OP2())
				;
		
			app.getStreamByID("streamS3").setConcreteStream(streamS3);
			// finish stream definition
			
			// begin stream privacy conf
			ApplicationDataStream app_streamS3 = app.getStreamByID("streamS3");
					
			ProtectedStream<SpentAmount> streamS3_p = 
				new ProtectedStream<SpentAmount>(false,"",-1,1,false,0,"/home/cablan/Desktop/thesisFiles/log/greatSeller");
			
			streamS3_p.setStreamToProtect((DataStream<SpentAmount>) app_streamS3.getConcreteStream());
			
				for (DSEP dsep : app.getDSEPs(app_streamS3.getId())) {
					streamS3_p.addDSEP(app_streamS3, (DSEP) dsep, app);
				}
			
			DataStream<SpentAmount> streamS3_f = streamS3_p.finalize(env, contextStream);
			// finish stream privacy conf
		
			// begin stream definition
			DataStream<NumberUsers> streamS4 =
				streamS3_f
		      	.keyBy("dataSubject")
		        .timeWindow(Time.minutes(1))
		        .apply(new OP3())
				;
		
			// finish stream definition
		
			// begin sink definition
			streamS2_f
			.keyBy("dataSubject")
			.writeAsText("/home/cablan/Desktop/thesisFiles/outputs/resultsS2priv.txt", WriteMode.OVERWRITE)
			.setParallelism(1);
			// finish sink definition
		
			// begin sink definition
			streamS3_f
			.keyBy("dataSubject")
			.writeAsText("/home/cablan/Desktop/thesisFiles/outputs/resultsS3priv.txt", WriteMode.OVERWRITE)
			.setParallelism(1);
			// finish sink definition
		
			// begin sink definition
			streamS4
			.writeAsText("/home/cablan/Desktop/thesisFiles/outputs/resultsS4priv.txt", WriteMode.OVERWRITE)
			.setParallelism(1);
			// finish sink definition

       JobExecutionResult result = env.execute();
       System.out.println("EXECUTION TIME: " + result.getNetRuntime(TimeUnit.SECONDS));

    }
}
