package it.deib.polimi.diaprivacy.model;

import java.util.ArrayList;
import java.util.List;
import org.apache.flink.streaming.api.datastream.DataStream;

public class ApplicationDataStream {
	
	Boolean isSubjectSpecific;
	
	String id;
	
	List<Field> fields;
	
	DataStream<?> concreteStream;
	
	String sourceId;
	
	public String getSourceId() {
		return sourceId;
	}

	public void setSourceId(String sourceId) {
		this.sourceId = sourceId;
	}

	public DataStream<?> getConcreteStream() {
		return concreteStream;
	}

	public void setConcreteStream(DataStream<?> concreteStream) {
		this.concreteStream = concreteStream;
	}

	public ApplicationDataStream(String id, Boolean isSubjectSpecific) {
		this.id = id;
		this.isSubjectSpecific = isSubjectSpecific;
		this.fields = new ArrayList<Field>();
	}

	public Boolean getIsSubjectSpecific() {
		return isSubjectSpecific;
	}

	public void setIsSubjectSpecific(Boolean isSubjectSpecific) {
		this.isSubjectSpecific = isSubjectSpecific;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public List<Field> getFields() {
		return fields;
	}

	public void setFields(List<Field> fields) {
		this.fields = fields;
	}

	public ApplicationDataStream () {
		
	}	
	
	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return this.id.hashCode();
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.id;
	}

}
