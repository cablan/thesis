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





public class greatSeller {

    public static void main(String[] args) throws Exception {
		
		final StreamExecutionEnvironment env = StreamExecutionEnvironment
               .getExecutionEnvironment();
       
       //uncomment the below if you want to set the default parallelism for the project.
       //env.setParallelism(1);



			// begin input stream definition
			DataStream<String> rawTuples = env.socketTextStream("localhost", 9999);
			// finish input stream definition
		
			// begin stream definition
			DataStream<InputTransaction> streamS1 = 
				rawTuples
				.map(new TupleParser())
				;
		
			// finish stream definition
		
			// begin stream definition
			DataStream<IssuedTransactions> streamS2 =
				streamS1
		      	.keyBy("dataSubject")
		        .timeWindow(Time.seconds(10))
		        .apply(new OP1())
				;
		
			// finish stream definition
		
			// begin stream definition
			DataStream<SpentAmount> streamS3 =
				streamS1
		      	.keyBy("dataSubject")
		        .timeWindow(Time.seconds(10))
		        .apply(new OP2())
				;
		
			// finish stream definition
		
			// begin stream definition
			DataStream<NumberUsers> streamS4 =
				streamS3
		      	.keyBy("dataSubject")
		        .timeWindow(Time.minutes(1))
		        .apply(new OP3())
				;
		
			// finish stream definition
		
			// begin sink definition
			streamS2
			.keyBy("dataSubject")
			.writeAsText("/home/cablan/Desktop/thesisFiles/outputs/resultsS2priv.txt", WriteMode.OVERWRITE)
			.setParallelism(1);
			// finish sink definition
		
			// begin sink definition
			streamS3
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
