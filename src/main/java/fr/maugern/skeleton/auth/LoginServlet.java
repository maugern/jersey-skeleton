package fr.maugern.skeleton.auth;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import fr.maugern.skeleton.api.BDDFactory;
import fr.maugern.skeleton.api.User;
import fr.maugern.skeleton.api.UserDao;

@Controller
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	
	private UserDao dao = BDDFactory.getDbi().open(UserDao.class);
	
	@RequestMapping(value="login", method = RequestMethod.GET)
	public String login(){
		return "redirect:signin.jsp";
	}
	
	@RequestMapping(value="/signin", method = RequestMethod.POST)
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// get request parameters for userID and password
		String alias = request.getParameter("username");
		String password = request.getParameter("password");
		
		User user = dao.findByAlias(alias);
		
		if(user.isGoodPassword(password)){
			HttpSession session = request.getSession();
			session.setAttribute("username", alias);
			Cookie loginCookie = new Cookie("user",user.toString()); // TODO maybe a dot very good cookie
			//setting cookie to expiry in 30 mins
			loginCookie.setMaxAge(30*60);
			response.addCookie(loginCookie);
			response.sendRedirect("/index");
		}else{
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/signin");
			PrintWriter out= response.getWriter();
			out.println("<font color=red>Either user name or password is wrong.</font>");
			rd.include(request, response);
		}

	}
	
}