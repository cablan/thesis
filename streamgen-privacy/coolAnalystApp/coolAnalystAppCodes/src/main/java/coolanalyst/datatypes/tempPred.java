package coolanalyst.datatypes;

import java.io.Serializable;
import java.lang.reflect.Field;

public class tempPred implements Serializable{

    private String roomId;

    private Double predTemp;

    private Long eventTime;


	private String tupleId;

	private String streamId;

	public String getTupleId() {
		return this.tupleId;
	}
	
	public void setTupleId(String tupleId) {
		this.tupleId = tupleId;
	}

	public String getStreamId() {
		return this.streamId;
	}
	
	public void setStreamId(String streamId) {
		this.streamId = streamId;
	}
 
    public tempPred() {

    }

    public tempPred(String roomId, Double predTemp, Long eventTime) {
        this.roomId = roomId;
        this.predTemp = predTemp;
        this.eventTime = eventTime;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public Double getPredTemp() {
        return predTemp;
    }

    public void setPredTemp(Double predTemp) {
        this.predTemp = predTemp;
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

		sb.append(this.roomId + "," + this.predTemp + "," + this.eventTime );

        return sb.toString();
    }

	@Override
	public int hashCode() {
		return this.tupleId.hashCode();
	}
	
	@Override
	public boolean equals(Object other) {
		Field t;
		try {
			t = other.getClass().getDeclaredField("tupleId");
			t.setAccessible(true);
			return this.tupleId.equals((String) t.get(other));
		} catch (NoSuchFieldException | SecurityException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		
		return false;

	}

}
