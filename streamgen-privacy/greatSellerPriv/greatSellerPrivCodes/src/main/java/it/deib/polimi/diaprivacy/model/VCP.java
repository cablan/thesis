package it.deib.polimi.diaprivacy.model;

import java.util.List;
import java.util.Map;

public class VCP  extends PrivacyPolicy {
	
	Map<String, Integer> generalization;
	
	public VCP() {
		
	}
	
	public VCP(String dataSubject, List<ContextualCondition> simpleConditions, List<PastCondition> pastConditions) {
		super.setDataSubject(dataSubject);
		super.setSimpleConditions(simpleConditions);
		super.setPastConditions(pastConditions);
	}
	public Map<String, Integer> getGeneralization() {
		return generalization;
	}

	public void setGeneralization(Map<String, Integer> generalization) {
		this.generalization = generalization;
	}

	public GeneralizationVector getGeneralizationVector() {
		GeneralizationVector toReturn = new GeneralizationVector();
		for(String k: this.generalization.keySet()) {
			toReturn.setVariableGenLevel(k, this.generalization.get(k));
		}
		return toReturn;
	}

}
