package coolanalyst.datatypes;

import java.io.Serializable;
import java.lang.reflect.Field;

public class roomStatistics implements Serializable{

	private String roomId;

	private Double maxTemp;

	private Double minTemp;

	private Double avgTemp;

	private Long eventTime;

 
    public roomStatistics() {

    }

    public roomStatistics(String roomId, Double maxTemp, Double minTemp, Double avgTemp, Long eventTime) {
        this.roomId = roomId;
        this.maxTemp = maxTemp;
        this.minTemp = minTemp;
        this.avgTemp = avgTemp;
        this.eventTime = eventTime;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public Double getMaxTemp() {
        return maxTemp;
    }

    public void setMaxTemp(Double maxTemp) {
        this.maxTemp = maxTemp;
    }

    public Double getMinTemp() {
        return minTemp;
    }

    public void setMinTemp(Double minTemp) {
        this.minTemp = minTemp;
    }

    public Double getAvgTemp() {
        return avgTemp;
    }

    public void setAvgTemp(Double avgTemp) {
        this.avgTemp = avgTemp;
    }

    public Long getEventTime() {
        return eventTime;
    }

    public void setEventTime(Long eventTime) {
        this.eventTime = eventTime;
    }

	@Override
    public String toString() {

    	StringBuilder sb = new StringBuilder();

		sb.append(this.roomId + "," + this.maxTemp + "," + this.minTemp + "," + this.avgTemp + "," + this.eventTime );

        return sb.toString();
    }


}
