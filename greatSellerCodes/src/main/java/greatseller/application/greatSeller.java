package greatseller.application;

import greatseller.datatypes.InputTransaction;
import greatseller.datatypes.IssuedTransactions;
import greatseller.datatypes.SpentAmount;
import greatseller.datatypes.NumberUsers;

import greatseller.functions.TupleParser;
import greatseller.functions.TupleS2Generator;
import greatseller.functions.OP1;
import greatseller.functions.OP2;
import greatseller.functions.OP3;
import greatseller.functions.TupleS3Generator;

import java.util.*;
import java.util.concurrent.TimeUnit;
import org.apache.flink.api.common.JobExecutionResult;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.windowing.time.Time;




public class greatSeller {

    public static void main(String[] args) throws Exception {

       final StreamExecutionEnvironment env = StreamExecutionEnvironment
               .getExecutionEnvironment();
       
       //uncomment the below if you want to set the default parallelism for the project.
       //env.setParallelism(1);
       
       		DataStream<String> rawTuples = env.readTextFile("/home/cablan/Desktop/thesisFiles/tuples10File.txt");
       		;

			DataStream<InputTransaction> streamS1 = rawTuples
				.map(new TupleParser())
				;
			
			DataStream<IssuedTransactions> streamS2 = streamS1
			      	.keyBy("dataSubject")
			        .timeWindow(Time.minutes(10))
			        .apply(new OP1())
					;

			DataStream<String> resultS2Tuple = streamS2
			      	.keyBy("dataSubject")
					.map(new TupleS2Generator())
					;
			
			resultS2Tuple
				.writeAsText("/home/cablan/Desktop/thesisFiles/resultsS2.txt")
				.setParallelism(1);
		
			DataStream<SpentAmount> streamS3 = streamS1
		      	.keyBy("dataSubject")
		        .timeWindow(Time.minutes(10))
		        .apply(new OP2())
				;
		
			DataStream<NumberUsers> streamS4 = streamS3
		      	.keyBy("dataSubject")
		        .timeWindow(Time.hours(1))
		        .apply(new OP3())
				;
		
			DataStream<String> streamS3Tuple = streamS3
		      	.keyBy("dataSubject")
				.map(new TupleS3Generator())
				;
		
			streamS3Tuple
				.writeAsText("/home/cablan/Desktop/thesisFiles/resultsS3.txt")
				.setParallelism(1);
			
			streamS4
				.writeAsText("/home/cablan/Desktop/thesisFiles/resultsS4.txt")
				.setParallelism(1);

       JobExecutionResult result = env.execute();
       System.out.println("EXECUTION TIME: " + result.getNetRuntime(TimeUnit.SECONDS));

    }
}
