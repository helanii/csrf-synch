package org.csrf.synctoks.services;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.csrf.synctoks.db.Database;
import org.csrf.synctoks.model.Essential;



@WebServlet("/MainController")
public class MainController extends HttpServlet
{

  public static Map<String, String> csrfTokenStore = new HashMap<String, String>();
  private static final long serialVersionUID = 1L;

  
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
  {
    response.getWriter().append("Served at: ").append(request.getContextPath());
  }

 
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
  {

    String username = request.getParameter("username"); //get user name
    String password = request.getParameter("password");//get password
    HttpSession session = request.getSession(true); //create a new session if session has expired/doesn't exist

    if (Database.isValidUser(username, password)) //if the given user name and password are valid
    {
      String csrfToken = generateCSRFToken(session.getId()); //generate a csrf token against the session created
      csrfTokenStore.put(session.getId(), csrfToken); // adding to token store
      response.addCookie(Essential.COOKIE_WITH_SESSION_ID.apply(session));//send response with the cookie which carries the cdrf token and session id

      session.removeAttribute("invalidCredentials");
      response.sendRedirect("./Home.jsp"); //direct to home page
    }
    else //if user name and password are invalid
    {
      session.setAttribute("invalidCredentials", "retry"); //prompt to retry
      response.sendRedirect("./Login.jsp"); //redirect to login page
    }
  }

  private String generateCSRFToken(String strClearText) //generate a csrf token
  {
    return strClearText + "." + getRandomString();  //generate a random string to embed in the csrf token
  }

  private String getRandomString() //generate a random string
  {
    UUID randomUuid = UUID.randomUUID();
    return randomUuid.toString();
  }
}
