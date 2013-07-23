package org.avaje.jersey.usersecurity;

/**
 * Simple bean implementation of WebSession.
 */
public class WebSessionBean implements WebSession {

  private final boolean secure;
  private final String sessionId;
  private final String userId;
  
  public WebSessionBean() {
    this(false, null, null);
  }
  
  public WebSessionBean(boolean secure,  String sessionId, String userId) {
    this.secure = secure;
    this.sessionId = sessionId;
    this.userId = userId;
  }
  
  public boolean isSecure() {
    return secure;
  }

  public String getId() {
    return sessionId;
  }

  public String getUserId() {
    return userId;
  }
  
}
