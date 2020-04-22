package streamingwordcount.functions;

import streamingwordcount.datatypes.WordCount;
import streamingwordcount.datatypes.WordToken;


import java.util.*;

import org.apache.flink.api.java.tuple.Tuple;
import org.apache.flink.streaming.api.functions.windowing.WindowFunction;
import org.apache.flink.streaming.api.windowing.windows.TimeWindow;
import org.apache.flink.util.Collector;

public class WordCounter implements WindowFunction<WordToken, WordCount, Tuple, TimeWindow> {
	
	@Override
	public void apply(Tuple key, TimeWindow window, Iterable<WordToken> windowContentIterator, Collector<WordCount> out) throws Exception {
		
		List<WordToken> windowContent = new  ArrayList<WordToken>();
		for(WordToken x: windowContentIterator){
			windowContent.add(x);
		}
		
		int count = 0;
for(WordToken token: windowContent){
count++;
}
out.collect(new WordCount(key.getField(0),count));
	}


}
