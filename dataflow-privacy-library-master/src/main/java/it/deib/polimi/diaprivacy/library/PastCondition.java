package it.deib.polimi.diaprivacy.library;

import java.io.Serializable;

public class PastCondition implements Serializable{
	
	  private String variable;
	  
	  private String variableType;
	  
	  private Operator operator;
	  
	  private Object value;
	  
	  private Long timeWindowMilliseconds;
	  
	  public PastCondition() {
		  
	  }
	  
	  public PastCondition(String variable, String variableType, Operator operator, Object value, Long timeWindowMilliseconds) {
		  	this.variable = variable;
		  	this.variable = variableType;
		  	this.operator = operator;
		  	this.value = value;
		  	this.timeWindowMilliseconds = timeWindowMilliseconds;
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
	  
	  public Long getTimeWindowMilliseconds() {
		  return timeWindowMilliseconds;
	  }

  	  @Override
      public String toString() {
  		  StringBuilder sb = new StringBuilder();
  		  sb.append(variable).append(",");
  		  sb.append(variableType).append(",");
  		  sb.append(operator).append(",");
  		  sb.append(value).append(",");
  		  sb.append(timeWindowMilliseconds).append(",");

  		  return sb.toString();
	  }
}
