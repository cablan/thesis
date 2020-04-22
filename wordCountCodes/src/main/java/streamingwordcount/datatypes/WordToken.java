package streamingwordcount.datatypes;

import java.io.Serializable;
import java.lang.reflect.Field;

public class WordToken implements Serializable{

    private String word;

    private Integer token;

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
 
    public WordToken() {

    }

    public WordToken(String word, Integer token) {
        this.word = word;
        this.token = token;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public Integer getToken() {
        return token;
    }

    public void setToken(Integer token) {
        this.token = token;
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
