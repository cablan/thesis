package it.deib.polimi.diaprivacy.model;

import java.io.Serializable;

public class ContextualCondition implements Serializable {
	

	private static final long serialVersionUID = 2504843942020766782L;

	protected String variable;
	
	protected String containerStreamId;

	protected VariableType type;
	
	protected RelationalOperator operator;
	
	protected Object value;
	
	public ContextualCondition() {
		
	}
	
	public String getContainerStreamId() {
		return containerStreamId;
	}

	public void setContainerStreamId(String containerStreamId) {
		this.containerStreamId = containerStreamId;
	}
	
	public ContextualCondition(String variable, VariableType type, RelationalOperator op, Object value) {
		this.variable = variable;
		this.type = type;
		this.operator = op;
		this.value = value;
	}
	
	public ContextualCondition(String variable, VariableType type, RelationalOperator op, Object value, String streamId) {
		this.variable = variable;
		this.type = type;
		this.operator = op;
		this.value = value;
		this.containerStreamId = streamId;
	}

	public String getVariable() {
		return variable;
	}

	public void setVariable(String variable) {
		this.variable = variable;
	}

	public RelationalOperator getOperator() {
		return operator;
	}

	public void setOperator(RelationalOperator operator) {
		this.operator = operator;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}

	public VariableType getType() {
		return type;
	}

	public void setType(VariableType type) {
		this.type = type;
	}
	
    @Override
    public String toString() {
    	
    	StringBuilder sb = new StringBuilder();
    	
        sb.append(this.variable + ", ");
        sb.append(this.operator + ", ");
        sb.append(this.value + "\n");
        
        return sb.toString();
    }
}
