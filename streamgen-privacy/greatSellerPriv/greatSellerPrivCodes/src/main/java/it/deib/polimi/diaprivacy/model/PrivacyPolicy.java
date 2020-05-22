package it.deib.polimi.diaprivacy.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.flink.streaming.api.datastream.DataStream;

public abstract class PrivacyPolicy {

	private List<ContextualCondition> simpleConditions;

	private List<PastCondition> pastConditions;

	private String segPolicy;

	private String dataSubject;

	private PrivacyContext privacyContext;
	
	public PrivacyPolicy() {
		this.simpleConditions = new ArrayList<ContextualCondition>();
		this.pastConditions = new ArrayList<PastCondition>();
	}

	public PrivacyContext getPrivacyContext() {
		return privacyContext;
	}

	public void setPrivacyContext(PrivacyContext privacyContext) {
		this.privacyContext = privacyContext;
	}

	public String getSegPolicy() {
		return segPolicy;
	}

	public void setSegPolicy(String segPolicy) {
		this.segPolicy = segPolicy;
	}

	public Map<DataStream<?>, PastCondition> getSubjectSpecificPastConditions(ApplicationPrivacy app) {
		Map<DataStream<?>, PastCondition> toReturn = new HashMap<DataStream<?>, PastCondition>();
		for (PastCondition pc : this.getPastConditions()) {
			if (app.getStreamByID(pc.getContainerStreamId()).getIsSubjectSpecific()) {
				toReturn.put(app.getStreamByID(pc.getContainerStreamId()).concreteStream, pc);
			}
		}
		return toReturn;
	}

	public Map<DataStream<?>, PastCondition> getGenericPastConditions(ApplicationPrivacy app) {
		Map<DataStream<?>, PastCondition> toReturn = new HashMap<DataStream<?>, PastCondition>();
		for (PastCondition pc : this.getPastConditions()) {
			if (!app.getStreamByID(pc.getContainerStreamId()).getIsSubjectSpecific()) {
				toReturn.put(app.getStreamByID(pc.getContainerStreamId()).concreteStream, pc);
			}
		}
		return toReturn;
	}

	public Map<DataStream<?>, ContextualCondition> getSubjectSpecificStaticConditions(ApplicationPrivacy app, ApplicationDataStream protectedStream) {
		Map<DataStream<?>, ContextualCondition> toReturn = new HashMap<DataStream<?>, ContextualCondition>();
		for (ContextualCondition c : this.getSimpleConditions()) {
			if (app.getStreamByID(c.getContainerStreamId()).getIsSubjectSpecific() && !app.getStreamByID(c.getContainerStreamId()).getId().equals(protectedStream.getId())) {
				toReturn.put(app.getStreamByID(c.getContainerStreamId()).concreteStream, c);
			}
		}
		return toReturn;

	}

	public Map<DataStream<?>, ContextualCondition> getGenericStaticConditions(ApplicationPrivacy app, ApplicationDataStream protectedStream) {
		Map<DataStream<?>, ContextualCondition> toReturn = new HashMap<DataStream<?>, ContextualCondition>();
		for (ContextualCondition c : this.getSimpleConditions()) {
			if (!app.getStreamByID(c.getContainerStreamId()).getIsSubjectSpecific() && !app.getStreamByID(c.getContainerStreamId()).getId().equals(protectedStream.getId())) {
				toReturn.put(app.getStreamByID(c.getContainerStreamId()).concreteStream, c);
			}
		}
		return toReturn;
	}

	public List<ContextualCondition> getProtectedStreamConds(ApplicationPrivacy app, ApplicationDataStream protectedStream) {
		List<ContextualCondition> toReturn = new ArrayList<ContextualCondition>();
		for (ContextualCondition c : this.getSimpleConditions()) {
			if (app.getStreamByID(c.getContainerStreamId()).getId().equals(protectedStream.getId())) {
				toReturn.add(c);
			}
		}
		return toReturn;
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
