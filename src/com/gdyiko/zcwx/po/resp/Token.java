package com.gdyiko.zcwx.po.resp;

public class Token {
  // 接口访问凭证
  private String accessToken;
  private Long begin_time;
  // 凭证有效期，单位：秒
  private int expiresIn;
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

  public Long getBegin_time() {
    return begin_time;
  }

  public void setBegin_time(Long begin_time) {
    this.begin_time = begin_time;
  }

    @Override
    public String toString() {
        return "Token{" +
                "accessToken='" + accessToken + '\'' +
                ", begin_time=" + begin_time +
                ", expiresIn=" + expiresIn +
                '}';
    }
}

