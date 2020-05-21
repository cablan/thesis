package it.deib.polimi.diaprivacy.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GeneralizationVector implements Serializable {

	private static final long serialVersionUID = -5618846322776911067L;
	
	private Map<String, Integer> vector;
	
	public GeneralizationVector() {
		this.vector = new HashMap<String, Integer>();
		
	}
	
	public GeneralizationVector(List<String> variables) {
		this.vector = new HashMap<String, Integer>();

		for(String v: variables) {
			this.vector.put(v, 0);
		}
	}
	
	public void initialize(List<String> variables) {
		for(String v: variables) {
			this.vector.put(v, 0);
		}
	}
	
	public GeneralizationVector(Map<String, Integer> vector) {
		this.vector = vector;
	}
	
	public void setVariableGenLevel(String variable, Integer level) {
		this.vector.put(variable, level);
	}
	
	public Integer getVariableGenLevel(String variable) {
		return this.vector.get(variable);
	}

	public Map<String, Integer> getVector() {
		return vector;
	}

	public void setVector(Map<String, Integer> vector) {
		this.vector = vector;
	}
}
