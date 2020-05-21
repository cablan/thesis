package coolanalyst.datatypes;

import java.io.Serializable;
import java.lang.reflect.Field;

public class roomTemp implements Serializable{

	private String roomId;

	private Double roomTemp;

 
    public roomTemp() {

    }

    public roomTemp(String roomId, Double roomTemp) {
        this.roomId = roomId;
        this.roomTemp = roomTemp;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public Double getRoomTemp() {
        return roomTemp;
    }

    public void setRoomTemp(Double roomTemp) {
        this.roomTemp = roomTemp;
    }

	@Override
    public String toString() {

    	StringBuilder sb = new StringBuilder();

		sb.append(this.roomId + "," + this.roomTemp );

        return sb.toString();
    }


}
