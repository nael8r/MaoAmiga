package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
	Servlet responsável por exibir a mensagem de erro e redirecionar para alguma página

	Forma de invocação:
	req.getRequestDispatcher("mensagensErroServlet?mensagem=ERRO! - Dados de login inválidos!&direcao=login.jsp").forward(req, resp);
*/

@WebServlet("/mensagensErroServlet")
public class MensagensErroServlet extends HttpServlet {

	/*
		Parâmetros recebidos: 
			mensagem: mensagem a ser exibida no topo
			direção: redirecionamento final
		É exibido uma tarja vermelha no topo da página com a mensagem na página redirencionada.
		Utilizou-se de include para mesclar a tarja com a página direção.
	*/
	
	private static final long serialVersionUID = 4215763913495711913L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter o = resp.getWriter();
		
		resp.setContentType("text/html");
		o.println("<div class=\"card-panel center red darken-2\">"
				+ "<b>"
					+ req.getParameter("mensagem")
				+ "</b>"
			+ "</div>");
		req.getRequestDispatcher(req.getParameter("direcao")).include(req, resp);
	}
	
	
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
