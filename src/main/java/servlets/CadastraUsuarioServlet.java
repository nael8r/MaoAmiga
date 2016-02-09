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

@WebServlet("/cadastraUsuario")
public class CadastraUsuarioServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Usuario usuario = new Usuario(req.getParameter("nome"),
				req.getParameter("login"),
				req.getParameter("senha"), 
				req.getParameter("tipo").charAt(0));
		
		UsuarioDAO usuDAO = new UsuarioDAO(HibernateUtil.getSessionFactory().openSession());
		
		if(!usuDAO.validaNomeLogin(usuario.getLogin()))
		{
			usuDAO.getSessao().close();
			req.getRequestDispatcher("mensagensErroServlet?mensagem=Nem todos os campos foram preenchidos&"
					+ "direcao=cadastraUsuario.jsp");
			
		}
		else
		{
			usuDAO.salvar(usuario);
			usuDAO.getSessao().close();
			req.getRequestDispatcher("consultarUsuariosServlet").forward(req, resp);
		}
	}

}
