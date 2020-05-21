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





public class coolAnalyst {

    public static void main(String[] args) throws Exception {
		
		final StreamExecutionEnvironment env = StreamExecutionEnvironment
               .getExecutionEnvironment();
       
       //uncomment the below if you want to set the default parallelism for the project.
       //env.setParallelism(1);


///////////////////////////////////////////////////////////////Streams From Sources///////////////////////////////////////////////////////////////////

			// begin input stream definition
			DataStream<String> roomOneStream = env.socketTextStream("localhost", 8888);
			// finish input stream definition
			// begin input stream definition
			DataStream<String> roomTwoStream = env.socketTextStream("localhost", 8887);
			// finish input stream definition

////////////////////////////////////////////////Streams From Transformations Before Privacy Policies////////////////////////////////////////////////////

			DataStream<roomTemp> tempTupleStream = roomOneStream
				.connect(
				roomTwoStream
				)
				.map(new TempParser())
				;
		
				// finish stream definition
		
		
			// begin stream definition
			DataStream<roomStatistics> statTupleStream =
				tempTupleStream
		      	.keyBy("roomId")
		        .timeWindow(Time.seconds(1))
		        .apply(new RoomStatistics())
				;
		
			// finish stream definition
		
			// begin stream definition
			DataStream<tempPred> predTupleStream =
				statTupleStream
		      	.keyBy("roomId")
		        .timeWindow(Time.seconds(1))
		        .apply(new TemperaturePredictor())
				;
		
			// finish stream definition

///////////////////////////////////////////////////////Streams From Privacy Policies///////////////////////////////////////////////////////////////////


////////////////////////////////////////////////Streams From Transformations After Privacy Policies////////////////////////////////////////////////////


///////////////////////////////////////////////////////////Streams To Sinks//////////////////////////////////////////////////////////////////////////////

		
			// begin sink definition
			statTupleStream
			.keyBy("roomId")
			.writeAsText("/home/cablan/Desktop/thesisFiles/outputs/stats.txt", WriteMode.OVERWRITE)
			.setParallelism(1);
			// finish sink definition
		
			// begin sink definition
			predTupleStream
			.keyBy("roomId")
			.writeAsText("/home/cablan/Desktop/thesisFiles/outputs/preds.txt", WriteMode.OVERWRITE)
			.setParallelism(1);
			// finish sink definition


       JobExecutionResult result = env.execute();
       System.out.println("EXECUTION TIME: " + result.getNetRuntime(TimeUnit.SECONDS));

    }
}
