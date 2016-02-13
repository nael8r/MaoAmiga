package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import conexao.HibernateUtil;
import controle.UsuarioDAO;
import modelo.Usuario;

/*
	Servlet responsável por por cadastrar um novo usuário
*/

@WebServlet("/cadastraUsuario")
public class CadastraUsuarioServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// Recebe os dados por parâmetro
		Usuario usuario = new Usuario(req.getParameter("nome"),
				req.getParameter("login"),
				req.getParameter("senha"), 
				req.getParameter("tipo").charAt(0));
		
		UsuarioDAO usuDAO = new UsuarioDAO(HibernateUtil.getSessionFactory().openSession());
		
		// Verifica se o novo usuário possui um login válido.
		if(!usuDAO.validaNomeLogin(usuario.getLogin()))
		{
			// Se não, informa erro
			usuDAO.getSessao().close();
			req.getRequestDispatcher("mensagensErroServlet?mensagem=Nome de login já utilizado&"
					+ "direcao=cadastraUsuario.jsp");
			
		}
		else
		{
			// Se sim, salva o novo usuário
			usuDAO.salvar(usuario);
			usuDAO.getSessao().close();
			req.getRequestDispatcher("consultarUsuariosServlet").forward(req, resp);
		}
	}

}
