package org.csrf.synctoks.db;

public class Database {
     //hard code user name and password in to application
	 private static final String USERNAME = "test";

	  private static final String PASSWORD = "test";

	  //function to check if the user is valid by checking for username and password in db
	  public static boolean isValidUser(String username, String password)
	  {
	    return USERNAME.equalsIgnoreCase(username) && PASSWORD.equalsIgnoreCase(password);
	  }
}
