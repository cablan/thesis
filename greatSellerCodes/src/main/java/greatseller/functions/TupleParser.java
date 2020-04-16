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

public class TupleParser extends RichMapFunction<String, InputTransaction> {

	@Override 
	public void open(Configuration conf) {
		
	}
	
	@Override
	public InputTransaction map(String tuple) throws Exception {
		String[] fields = tuple.split(","); return new InputTransaction( fields[0],Long.parseLong(fields[1]));
	}
	
}

