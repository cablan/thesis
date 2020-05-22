package greatseller.functions;

import greatseller.datatypes.InputTransaction;
import greatseller.datatypes.IssuedTransactions;
import greatseller.datatypes.SpentAmount;
import greatseller.datatypes.NumberUsers;


import java.util.*;

import org.apache.flink.api.java.tuple.Tuple;
import org.apache.flink.streaming.api.functions.windowing.WindowFunction;
import org.apache.flink.streaming.api.windowing.windows.TimeWindow;
import org.apache.flink.util.Collector;

public class OP1 implements WindowFunction<InputTransaction, IssuedTransactions, Tuple, TimeWindow> {
	
	@Override
	public void apply(Tuple key, TimeWindow window, Iterable<InputTransaction> windowContentIterator, Collector<IssuedTransactions> out) throws Exception {
		
		List<InputTransaction> windowContent = new  ArrayList<InputTransaction>();
		for(InputTransaction x: windowContentIterator){
			windowContent.add(x);
		}
		
				out.collect(new IssuedTransactions(key.getField(0), new Integer(windowContent.size())));
	}


}
