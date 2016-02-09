package servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import conexao.HibernateUtil;
import controle.UsuarioDAO;
import modelo.Produtos;
import modelo.Usuario;

@WebServlet("/consultarUsuariosServlet")
public class ConsultarUsuariosServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<Usuario> usuarios = new ArrayList<Usuario>();
		UsuarioDAO usuDAO = new UsuarioDAO(HibernateUtil.getSessionFactory().openSession());

		if (req.getParameter("cod") != null && !req.getParameter("cod").isEmpty()) {

			Integer codigo = Integer.parseInt(req.getParameter("cod"));

			Usuario usuario = usuDAO.getUsuario(codigo);

			if (usuario != null)
				usuarios.add(usuario);
		} else if (req.getParameter("nome") != null && !req.getParameter("nome").isEmpty()) {

			Usuario usuario = usuDAO.getUsuario(req.getParameter("nome"));

			if (usuario != null)
				usuarios.add(usuario);

		} else {

			usuarios = usuDAO.getUsuarios();
		}

		usuDAO.getSessao().close();

		req.getSession().setAttribute("usuarios", usuarios);

		req.getRequestDispatcher("gerenciarUsuarios.jsp").forward(req, resp);
	}

}
