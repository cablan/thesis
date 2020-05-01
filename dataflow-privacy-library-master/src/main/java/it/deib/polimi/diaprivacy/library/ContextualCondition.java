package it.deib.polimi.diaprivacy.library;

import java.io.Serializable;

public class ContextualCondition implements Serializable{
	
	  private String variable;
	  
	  private String variableType;
	  
	  private Operator operator;
	  
	  private Object value;
	  
	  public ContextualCondition() {
		  
	  }
	  
	  public ContextualCondition(String variable, String variableType, Operator operator, Object value) {
		  	this.variable = variable;
		  	this.variableType = variableType;
		  	this.operator = operator;
		  	this.value = value;
		  }
	  
	  public String getVariable() {
		  return variable;
	  }
	  
	  public String getVariableType() {
		  return variableType;
	  }
	  
	  public Operator getOperator() {
		  return operator;
	  }
	  
	  public Object getValue() {
		  return value;
	  }

  	  @Override
      public String toString() {
  		  StringBuilder sb = new StringBuilder();
  		  sb.append(variable).append(",");
  		  sb.append(variableType).append(",");
  		  sb.append(operator).append(",");
  		  sb.append(value).append(",");

  		  return sb.toString();
	  }
}
