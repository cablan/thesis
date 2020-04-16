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
		
		List<SpentAmount> windowContent = new  ArrayList<SpentAmount>();
		for(SpentAmount x: windowContentIterator){
			windowContent.add(x);
		}
		
		var cont = 0
    var lastTransactionIndex = -1
    var partialSums: mutable.Map[String,Int] = mutable.Map()

    for (t <- input) {
      if(partialSums.key.getField(0)Set.contains(t.dataSubject)){
        partialSums += (t.dataSubject -> (partialSums(t.dataSubject) + t.amount))
      } else{
        partialSums.put(t.dataSubject, t.amount)
      }

      if(t.transactionId.substring(1, t.transactionId.length).toInt > lastTransactionIndex){
        lastTransactionIndex = t.transactionId.substring(1, t.transactionId.length).toInt
      }
    }

    for (k <- partialSums){
      if(k._2 > 1000){
        cont = cont + 1
      }
    }
    
    out.collect(new TopConsumers(cont, window.getEnd))
	}


}
