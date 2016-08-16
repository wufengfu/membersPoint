package com.tianxiafen.entity;
/**
* 类名: Token
* 描述: 凭证
* 发布版本：V1.0
 */
public class Token {
	  // 接口访问凭证
	  private String accessToken;
	  // 凭证有效期，单位：秒
	  private int expiresIn;
	  private String refreshToken;
	  private String openId;
	  private String scope;
	  public String getAccessToken() {
	    return accessToken;
	  }
	  public void setAccessToken(String accessToken) {
	    this.accessToken = accessToken;
	  }
	  public int getExpiresIn() {
	    return expiresIn;
	  }
	  public void setExpiresIn(int expiresIn) {
	    this.expiresIn = expiresIn;
	  }
	public String getRefreshToken() {
		return refreshToken;
	}
	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}
	public String getOpenId() {
		return openId;
	}
	public void setOpenId(String openId) {
		this.openId = openId;
	}
	public String getScope() {
		return scope;
	}
	public void setScope(String scope) {
		this.scope = scope;
	}
	  
}
