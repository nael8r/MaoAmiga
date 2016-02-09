package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.Session;

import conexao.HibernateUtil;
import controle.UsuarioDAO;
import modelo.Usuario;



public class Autenticador extends HttpServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1557989432133977441L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Session session = HibernateUtil.getSessionFactory().openSession();
		
		String login = req.getParameter("login");
		String senha = req.getParameter("senha");
		
		UsuarioDAO usuarioDAO = new UsuarioDAO(session);
		Usuario usuario = usuarioDAO.validaLogin(login, senha);
		
		if (usuario != null) {
			// Recupera e cria uma nova caso nao tiver/
			HttpSession sessao = req.getSession();
			
			sessao.setAttribute("usuarioAutenticado", usuario);
			
			req.getRequestDispatcher("index.jsp").forward(req, resp);
		} else {
			
			session.clear();
			
			//req.getRequestDispatcher("mensagemErroServlet?mensagem=ERRO! - Dados de login inválidos!&direcao=login.jsp").forward(req, resp);
		}
			
	}
}
