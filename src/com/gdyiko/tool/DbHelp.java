package com.gdyiko.tool;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DbHelp
{
  private static DbHelp dbUtil;
  private String drivers;
  private String url;
  private String username;
  private String password;

  public static DbHelp getInstance()
  {
    if (dbUtil == null) {
      dbUtil = new DbHelp();
    }
    return dbUtil;
  }

  public Connection getConnection(String FILE_PATH_NAME) {
    try {
      InputStream in = getClass().getResourceAsStream(FILE_PATH_NAME);
      Properties props = new Properties();

      props.load(in);
      in.close();
      this.drivers = props.getProperty("driver");
      this.url =props.getProperty("url");
      this.username = props.getProperty("user");
      this.password = props.getProperty("password");
    } catch (IOException e) {
      e.printStackTrace();
    }
    Connection conn = null;
    try {
      Class.forName(this.drivers);
      conn = DriverManager.getConnection(this.url, this.username, this.password);
    } catch (SQLException e) {
      e.printStackTrace();
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    }
    return conn;
  }
}