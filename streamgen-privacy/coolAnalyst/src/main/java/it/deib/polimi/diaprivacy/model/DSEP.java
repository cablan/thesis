package it.deib.polimi.diaprivacy.model;

import java.util.List;

public class DSEP extends PrivacyPolicy {
	
	String dataSubject;
	
	List<ContextualCondition> simpleConditions;
	
	List<PastCondition> pastConditions;
	
	public DSEP() {
		
	}
	
	public DSEP(String dataSubject, List<ContextualCondition> simpleConditions, List<PastCondition> pastConditions) {
		this.dataSubject = dataSubject;
		this.simpleConditions = simpleConditions;
		this.pastConditions = pastConditions;
	}
	
	public String getDataSubject() {
		return dataSubject;
	}

	public void setDataSubject(String dataSubject) {
		this.dataSubject = dataSubject;
	}

	public List<ContextualCondition> getSimpleConditions() {
		return simpleConditions;
	}

	public void setSimpleConditions(List<ContextualCondition> simpleConditions) {
		this.simpleConditions = simpleConditions;
	}

	public List<PastCondition> getPastConditions() {
		return pastConditions;
	}

	public void setPastConditions(List<PastCondition> pastConditions) {
		this.pastConditions = pastConditions;
	}

}
