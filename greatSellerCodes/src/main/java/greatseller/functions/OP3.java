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

public class OP3 implements WindowFunction<SpentAmount, NumberUsers, Tuple, TimeWindow> {
	
	@Override
	public void apply(Tuple key, TimeWindow window, Iterable<SpentAmount> windowContentIterator, Collector<NumberUsers> out) throws Exception {
		
		int cont = 0;
		
		int sum = 0;

		for(SpentAmount x: windowContentIterator){
			sum += x.getTotalAmount();
			}
		
		if(sum > 1000) {
			cont += cont + 1;
		}
    
		out.collect(new NumberUsers(cont));
	}


}
