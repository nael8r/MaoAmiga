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
import modelo.Usuario;

/*
	Servlet responsável por realizar buscas de usuários no banco de dados
*/

@WebServlet("/consultarUsuariosServlet")
public class ConsultarUsuariosServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// Lista dos usuários a serem exibidos
		List<Usuario> usuarios = new ArrayList<Usuario>();
		UsuarioDAO usuDAO = new UsuarioDAO(HibernateUtil.getSessionFactory().openSession());

		// Verifica o tipo de solicitação
			// Código
		if (req.getParameter("cod") != null && !req.getParameter("cod").isEmpty()) {

			Integer codigo = Integer.parseInt(req.getParameter("cod"));

			Usuario usuario = usuDAO.getUsuario(codigo);

			if (usuario != null)
				usuarios.add(usuario);
		} 

			// Nome do usuário
		else if (req.getParameter("nome") != null && !req.getParameter("nome").isEmpty()) {

			Usuario usuario = usuDAO.getUsuario(req.getParameter("nome"));

			if (usuario != null)
				usuarios.add(usuario);

		} else {

			// Ou a exibição de todos
			usuarios = usuDAO.getUsuarios();
		}

		usuDAO.getSessao().close();

		// Define a lista na sessão
		req.getSession().setAttribute("usuarios", usuarios);

		// Redireciona para a exibição
		req.getRequestDispatcher("gerenciarUsuarios.jsp").forward(req, resp);
	}

}
