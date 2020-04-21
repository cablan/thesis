package greatseller.datatypes;

import java.io.Serializable;
import java.lang.reflect.Field;

public class SpentAmount implements Serializable{

    private String dataSubject;

    private Integer totalAmount;

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
 
    public SpentAmount() {

    }

    public SpentAmount(String dataSubject, Integer totalAmount) {
        this.dataSubject = dataSubject;
        this.totalAmount = totalAmount;
    }

    public String getDataSubject() {
        return dataSubject;
    }

    public void setDataSubject(String dataSubject) {
        this.dataSubject = dataSubject;
    }

    public Integer getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Integer totalAmount) {
        this.totalAmount = totalAmount;
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
