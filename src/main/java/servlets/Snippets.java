package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/snippets")
public class Snippets extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4215763913495711913L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		PrintWriter o = resp.getWriter();
		
		resp.setContentType("text/html");
		o.println("<div class=\"card-panel center red darken-2\">"
				+ "<b>"
					+ req.getParameter("mensagem")
				+ "</b>"
			+ "</div>");
		req.getRequestDispatcher(req.getParameter("direcao")).include(req, resp);
	}
}
