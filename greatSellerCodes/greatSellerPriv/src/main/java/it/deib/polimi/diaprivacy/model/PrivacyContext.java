package it.deib.polimi.diaprivacy.model;

import java.io.Serializable;
import java.util.Date;

public class PrivacyContext implements Serializable {

	private static final long serialVersionUID = -607178777841751659L;

	private String userId;

	private String role;

	private String purpose;

	private Long timestamp;

	public PrivacyContext() {
		this.userId = "";
		this.role = "";
		this.purpose = "";
		this.timestamp = -1L;
	}

	public PrivacyContext(String userId, String role, String purpose, Long timestamp) {
		this.userId = userId;
		this.role = role;
		this.purpose = purpose;
		this.timestamp = timestamp;
	}
	
	public PrivacyContext(String userId, String role, String purpose) {
		this.userId = userId;
		this.role = role;
		this.purpose = purpose;
		this.timestamp = -1L;
	}

	public Long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Long timestamp) {
		this.timestamp = timestamp;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getPurpose() {
		return purpose;
	}

	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		Date d = new Date(this.timestamp);

		sb.append("@" + this.timestamp);
		sb.append(" context (");
		sb.append(this.userId).append(")");

		return sb.toString();
	}

}
