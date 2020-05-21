package it.deib.polimi.diaprivacy.model;

public class PastCondition extends ContextualCondition {
	
	private static final long serialVersionUID = -1298160797129971733L;

	Integer lowerTemporalBound;
	
	Integer upperTemporalBound;
	
	public PastCondition() {
		
	}
	
	public PastCondition(String variable, VariableType type, RelationalOperator op, Object value, Integer t1, Integer t2, String streamId) {
		super(variable, type, op, value, streamId);
		this.lowerTemporalBound = t1;
		this.upperTemporalBound = t2;
	}
	
	public PastCondition(String variable, VariableType type, RelationalOperator op, Object value, Integer t1, Integer t2) {
		super(variable, type, op, value);
		this.lowerTemporalBound = t1;
		this.upperTemporalBound = t2;
	}
	
	public Integer getLowerTemporalBound() {
		return lowerTemporalBound;
	}

	public void setLowerTemporalBound(Integer lowerTemporalBound) {
		this.lowerTemporalBound = lowerTemporalBound;
	}

	public Integer getUpperTemporalBound() {
		return upperTemporalBound;
	}

	public void setUpperTemporalBound(Integer upperTemporalBound) {
		this.upperTemporalBound = upperTemporalBound;
	}
	
	public Integer timeWindowMilliseconds() {
		return this.upperTemporalBound - this.lowerTemporalBound;
	}
	
    @Override
    public String toString() {
    	
    	StringBuilder sb = new StringBuilder();
    	
        sb.append(this.variable + ", ");
        sb.append(this.operator + ", ");
        sb.append(this.value + ", ");
        sb.append(this.lowerTemporalBound + ", ");
        sb.append(this.upperTemporalBound + "\n");

        return sb.toString();
    }
	
}
