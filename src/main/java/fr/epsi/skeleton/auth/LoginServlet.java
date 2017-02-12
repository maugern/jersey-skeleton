package fr.epsi.skeleton.auth;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response.Status;

import fr.epsi.skeleton.api.BDDFactory;
import fr.epsi.skeleton.api.User;
import fr.epsi.skeleton.api.UserDao;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		// get request parameters for userID and password
		String login = request.getParameter("login");
		String password = request.getParameter("password");
		
		User user = loadUserFromLogin(login);
		
		if(user.isGoodPassword(password)){
			Cookie loginCookie = new Cookie("user",user.toString()); // TODO maybe a dot very good cookie
			//setting cookie to expiry in 30 mins
			loginCookie.setMaxAge(30*60);
			response.addCookie(loginCookie);
			response.sendRedirect("LoginSuccess.jsp");
		}else{
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/login.html");
			PrintWriter out= response.getWriter();
			out.println("<font color=red>Either user name or password is wrong.</font>");
			rd.include(request, response);
		}

	}
	
	/**
     * Load User from the login.
     * @param Login can be alias or email.
     * @return User who match with the login, anonymous user if not found.
     */
    private User loadUserFromLogin(String login) {
        UserDao dao = BDDFactory.getDbi().open(UserDao.class);
        User user;
        if ((user = dao.findByAlias(login)) != null)
        	return user;
        else if ((user = dao.findByEmail(login)) != null)
            return user;
        else
        	return User.getAnonymousUser();
    }
    
    /**
     * Check if the array "loginPassword" is not null and had 2 values.
     * If not, throw a new WebApplicationException with NOT ACCEPTABLE status (406).
     * @param loginPassword
     */
    private void checkLoginPassword(String[] loginPassword) {
        if (loginPassword == null || loginPassword.length != 2) {
            throw new WebApplicationException(Status.NOT_ACCEPTABLE);
        }
    }

}