package greatseller.datatypes;

import java.io.Serializable;
import java.lang.reflect.Field;

public class NumberUsers implements Serializable{

    private Integer NTopUsers;


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
 
    public NumberUsers() {

    }

    public NumberUsers(Integer NTopUsers) {
        this.NTopUsers = NTopUsers;
    }

    public Integer getNTopUsers() {
        return NTopUsers;
    }

    public void setNTopUsers(Integer NTopUsers) {
        this.NTopUsers = NTopUsers;
    }

	@Override
    public String toString() {

    	StringBuilder sb = new StringBuilder();

		sb.append(this.NTopUsers );

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
