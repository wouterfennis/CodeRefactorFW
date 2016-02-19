package util.FamilyWeb;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import domain.FamilyWeb.Administrator;
import domain.FamilyWeb.User;

/**
 * Servlet Filter implementation class ÀdminFilter
 */
public class AdminFilter implements Filter {	

	private final String PAGE_STARTSCREEN_SOCIALWORKER = "/socialworker/startscreen_socialworker.jsp";
	private final String PAGE_PASSWORD_RESET = "/password_reset.jsp";
	private final String PAGE_LOGIN = "/login.jsp";
	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest) request;
		Object userObject = req.getSession().getAttribute("user");
		
		// User signed in.
		if((userObject != null) && (userObject instanceof User)){
			
			if(userObject instanceof Administrator){
				User currentUser = (Administrator) userObject;
				
				// User is active with valid password.
				if (currentUser.isActive() && !currentUser.isWwreset()) {
					chain.doFilter(req, response);
					
				// User is active with not valid password
				} else if (currentUser.isActive() && currentUser.isWwreset()) {
					req.setAttribute("messageType", "warning");
					req.setAttribute("message", "Je hebt geen rechten om naar deze pagina te gaan, reset eerst je wachtwoord.");
					req.getRequestDispatcher(PAGE_PASSWORD_RESET).forward(req, response);
					
				// User is not active
				} else {
					req.setAttribute("messageType", "warning");
					req.setAttribute("message", "Je hebt geen rechten om naar deze pagina te gaan, je account is niet actief.");
					req.getRequestDispatcher(PAGE_LOGIN).forward(req, response);
				}
				
			// User is socialworker and not administrator
			}else{
				req.setAttribute("messageType", "warning");
				req.setAttribute("message", "Je hebt geen rechten om naar deze pagina te gaan.");
				req.getRequestDispatcher(PAGE_STARTSCREEN_SOCIALWORKER).forward(req, response);
			}
			
		// User not signed in.
		}else{
			req.setAttribute("messageType", "warning");
			req.setAttribute("message", "Je moet eerst inloggen om verder te gaan.");
			req.getRequestDispatcher(PAGE_LOGIN).forward(req, response);
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {}
	
	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {	}
}
