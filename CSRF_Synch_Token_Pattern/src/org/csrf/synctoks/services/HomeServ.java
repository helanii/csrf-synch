package org.csrf.synctoks.services;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/HomeServ")
	public class HomeServ extends HttpServlet
	{
	  private static final long serialVersionUID = 1L;

	  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	  {
	    response.getWriter().append("Served at: ").append(request.getContextPath());
	  }

	  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	  {
            
	    HttpSession session = request.getSession(false);
	    String hiddenToken = request.getParameter("hiddenField"); //get value in hidden field

	    String csrfToken = MainController.csrfTokenStore.get(session.getId()); //get value of the csrf token stored along with the session id

	    if (csrfToken.equals(hiddenToken)) //check if the csrf token value and the hidden filed token value is the same
	    {
	      response.getWriter().append("Success!"); //if same
	    }
	    else
	    {
	      response.getWriter().append("ERROR!");//if not
	    }
	  }
}
