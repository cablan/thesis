package it.deib.polimi.diaprivacy.library;

import java.io.Serializable;

public class PrivacyContext implements Serializable{
	
	  private String purpose;
	  
	  private String userId;
	  
	  private String role;
	  
	  private Long strangeValue;
	  
	  public PrivacyContext() {
		  
	  }
	  
	  public PrivacyContext(String purpose, String userId, String role, Long strangeValue) {
		  	this.purpose = purpose;
		  	this.userId = userId;
		  	this.role = role;
		  	this.strangeValue = strangeValue;
		  }
	  
	  public String getPurpose() {
		  return purpose;
	  }
	  
	  public String getUserId() {
		  return userId;
	  }
	  
	  public String getRole() {
		  return role;
	  }

  	  @Override
      public String toString() {
  		  StringBuilder sb = new StringBuilder();
  		  sb.append(purpose).append(",");
  		  sb.append(userId).append(",");
  		  sb.append(role).append(",");

  		  return sb.toString();
	  }
}