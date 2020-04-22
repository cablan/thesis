package greatseller.functions;

import greatseller.datatypes.InputTransaction;
import greatseller.datatypes.IssuedTransactions;
import greatseller.datatypes.SpentAmount;
import greatseller.datatypes.NumberUsers;

import org.apache.flink.api.java.tuple.Tuple;
import org.apache.flink.api.common.functions.RichMapFunction;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.util.Collector;
import java.util.*;

public class TupleS2Generator extends RichMapFunction<IssuedTransactions, String> {

	@Override 
	public void open(Configuration conf) {
		
	}
	
	@Override
	public String map(IssuedTransactions tuple) throws Exception {
		String joinTuple;
		
		return joinTuple = String.join(",", tuple.getDataSubject(), tuple.getNTransactions().toString());
	}
	
}

