package it.deib.polimi.diaprivacy.library;

import scala.collection.mutable.Map;

public class GeneralizationVector {
	
	private Map<String, Integer> vector;
	
	public void setGeneralizationVector(Map<String, Integer> vector) {
		this.vector = vector;
	}
	
	public Map<String, Integer> getVector(){
		return vector;
	}

}