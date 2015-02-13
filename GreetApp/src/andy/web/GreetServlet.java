package andy.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import andy.core.Greet;
import andy.ejb.GreetBean;

/**
 * Servlet implementation class GreetServlet
 */
@WebServlet("/GreetServlet")
public class GreetServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@javax.ejb.EJB
	private GreetBean bean;

	@javax.inject.Inject
	private Greet obj;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		java.io.Writer out=response.getWriter();

		out.append("<html><body>");
		out.append("<h1>Shalom aleikhem</h1>");
		out.append("<h1>"+bean.greet()+"</h1>");
		out.append("<h1>"+obj.greet()+"</h1>");
		out.append("</body></html>");

	}

}
