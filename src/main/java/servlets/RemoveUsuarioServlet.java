package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import conexao.HibernateUtil;
import controle.UsuarioDAO;

@WebServlet("/removeUsuarioServlet")
public class RemoveUsuarioServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Integer codigo = Integer.parseInt(req.getParameter("cod"));
		
		UsuarioDAO usuDAO = new UsuarioDAO(HibernateUtil.getSessionFactory().openSession());
		
		usuDAO.excluir(usuDAO.getUsuario(codigo));
		
		usuDAO.getSessao().close();
		
		req.getRequestDispatcher("gerenciarUsuarios.jsp").forward(req, resp);
	}

}
