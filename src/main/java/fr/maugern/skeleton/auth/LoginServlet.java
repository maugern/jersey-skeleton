package fr.maugern.skeleton.auth;

import fr.maugern.skeleton.api.BDDFactory;
import fr.maugern.skeleton.api.User;
import fr.maugern.skeleton.api.UserDao;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;

@Controller
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet implements Serializable{
	
	private static final long serialVersionUID = -2777055764423755263L;
	
	private static UserDao dao = BDDFactory.getDbi().open(UserDao.class);
	
	@RequestMapping(value="login", method = RequestMethod.GET)
	public String login(){
		return "redirect:signin.jsp";
	}
	
	@Override
	@RequestMapping(value="/signin", method = RequestMethod.POST)
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// get request parameters for userID and password
		String alias = request.getParameter("username");
		String password = request.getParameter("password");
		
		User user = dao.findByAlias(alias);
		
		if(user.isGoodPassword(password)){
			HttpSession session = request.getSession();
			session.setAttribute("username", alias);
            Cookie loginCookie = new Cookie("user", user.toString()); // TODO maybe a not very good cookie
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