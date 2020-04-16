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

public class OP2 implements WindowFunction<InputTransaction, SpentAmount, Tuple, TimeWindow> {
	
	@Override
	public void apply(Tuple key, TimeWindow window, Iterable<InputTransaction> windowContentIterator, Collector<SpentAmount> out) throws Exception {
		
		List<InputTransaction> windowContent = new  ArrayList<InputTransaction>();
		for(InputTransaction x: windowContentIterator){
			windowContent.add(x);
		}
		
		var sum = 0
    var lastTransactionIndex = -1

    for (t <- input) {
      sum += t.amount
      if(t.transactionId.substring(1, t.transactionId.length).toInt > lastTransactionIndex){
        lastTransactionIndex = t.transactionId.substring(1, t.transactionId.length).toInt
      }
	}


}
