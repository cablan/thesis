package coolanalyst.functions;

import coolanalyst.datatypes.roomStatistics;
import coolanalyst.datatypes.roomTemp;
import coolanalyst.datatypes.tempPred;


import org.apache.flink.api.java.tuple.Tuple;
import org.apache.flink.streaming.api.functions.co.RichCoMapFunction;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.util.Collector;
import java.util.*;

public class TempParser extends RichCoMapFunction<String, String, roomTemp> {

	
	@Override 
	public void open(Configuration conf) {
	}
	
	@Override
	public roomTemp map1(String tuple) throws Exception {
		String[] fields = tuple.split(",");
		
		return new roomTemp(fields[0],Double.parseDouble(fields[2]));
	}

	@Override
	public roomTemp map2(String tuple) throws Exception {
		String[] fields;
		
		fields = tuple.split(",");
		
		return new roomTemp(fields[0],Double.parseDouble(fields[2]));
	}
	
}

