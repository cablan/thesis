package coolanalyst.functions;

import coolanalyst.datatypes.roomStatistics;
import coolanalyst.datatypes.roomTemp;
import coolanalyst.datatypes.tempPred;


import java.util.*;

import org.apache.flink.api.java.tuple.Tuple;
import org.apache.flink.streaming.api.functions.windowing.WindowFunction;
import org.apache.flink.streaming.api.windowing.windows.TimeWindow;
import org.apache.flink.util.Collector;

public class RoomStatistics implements WindowFunction<roomTemp, roomStatistics, Tuple, TimeWindow> {
	
	@Override
	public void apply(Tuple key, TimeWindow window, Iterable<roomTemp> windowContentIterator, Collector<roomStatistics> out) throws Exception {
		
		List<roomTemp> windowContent = new  ArrayList<roomTemp>();
		for(roomTemp x: windowContentIterator){
			windowContent.add(x);
		}
		
		Double avgTemp = new Double(0.0);
		Double maxTemp =  new Double(-9999);
		Double minTemp = new Double(9999);

		for(roomTemp r: windowContent){
			avgTemp = avgTemp + r.getRoomTemp();
			if(maxTemp < r.getRoomTemp()){
				maxTemp = r.getRoomTemp();
			}
			if(minTemp > r.getRoomTemp()){
				minTemp = r.getRoomTemp();
			}
		}

		avgTemp = avgTemp/windowContent.size();

		out.collect(new roomStatistics(key.getField(0), maxTemp, minTemp, avgTemp, window.getEnd()));
	}


}
