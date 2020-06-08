package it.deib.polimi.diaprivacy.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.flink.streaming.api.datastream.DataStream;

public class ApplicationPrivacy {

	Map<ApplicationDataStream, List<PrivacyPolicy>> policiesPerStream;
	
	public ApplicationPrivacy() {
		
	}
	
	public Map<ApplicationDataStream, List<PrivacyPolicy>> getPoliciesPerStream() {
		return policiesPerStream;
	}

	public void setPoliciesPerStream(Map<ApplicationDataStream, List<PrivacyPolicy>> policiesPerStream) {
		this.policiesPerStream = policiesPerStream;
	}

	public ApplicationPrivacy(List<ApplicationDataStream> managedStreams) {
		
		policiesPerStream = new ConcurrentHashMap<ApplicationDataStream, List<PrivacyPolicy>>();
		
		for(ApplicationDataStream s: managedStreams) {
			policiesPerStream.put(s, new ArrayList<PrivacyPolicy>());
		}
	}
	
	public void addPolicy(String stream, PrivacyPolicy policy) {
		policiesPerStream.get(getStreamByID(stream)).add(policy);
	}
	
	public List<ApplicationDataStream> getApplicationStreams(){
		List<ApplicationDataStream> toReturn = new ArrayList<ApplicationDataStream>();
		
		for(ApplicationDataStream s: policiesPerStream.keySet()) {
			toReturn.add(s);
		}
		
		return toReturn;
	}
	
	public List<String> getApplicationStreamsNames(){
		List<String> toReturn = new ArrayList<String>();
		
		for(ApplicationDataStream s: policiesPerStream.keySet()) {
			toReturn.add(s.id);
		}
		
		return toReturn;
	}
	
	public ApplicationDataStream getStreamByID(String stream) {
		for(ApplicationDataStream s: this.policiesPerStream.keySet()) {
			if(s.getId().equals(stream))
				return s;
		}
		
		return null;
	}
	
	public void setConcreteStream(String stream, DataStream<?> concreteStream) {
		this.getStreamByID(stream).setConcreteStream(concreteStream);
	}
	
	public boolean hasSubjectEvictionPolicy(String stream) {
		ApplicationDataStream s = this.getStreamByID(stream);
		
		for(PrivacyPolicy p: this.policiesPerStream.get(s)) {
			if(p instanceof DSEP) {
				return true;
			}
		}
		
		return false;
	}
	
	public boolean hasViewCreationPolicy(String stream) {
		ApplicationDataStream s = this.getStreamByID(stream);
		
		for(PrivacyPolicy p: this.policiesPerStream.get(s)) {
			if(p instanceof VCP) {
				return true;
			}
		}
		
		return false;
	}
	
	public List<DSEP> getDSEPs(String stream){
		ApplicationDataStream s = this.getStreamByID(stream);
		List<DSEP> toReturn = new ArrayList<DSEP>();
		
		for(PrivacyPolicy p: this.policiesPerStream.get(s)) {
			if(p instanceof DSEP) {
				toReturn.add((DSEP) p);
			}
		}
		
		return toReturn;
	}
	
	public List<VCP> getVCPs(String stream){
		ApplicationDataStream s = this.getStreamByID(stream);
		List<VCP> toReturn = new ArrayList<VCP>();
		
		for(PrivacyPolicy p: this.policiesPerStream.get(s)) {
			if(p instanceof VCP) {
				toReturn.add((VCP) p);
			}
		}
		
		return toReturn;
	}
}
