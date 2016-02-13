package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import conexao.HibernateUtil;
import controle.UsuarioDAO;

/*
	Servlet que realiza a remoção de um usuário do banco de dados
*/
@WebServlet("/removeUsuarioServlet")
public class RemoveUsuarioServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// Obtem o código do usuário a ser excluido
		Integer codigo = Integer.parseInt(req.getParameter("cod"));
		
		// Controle de Usuarios
		UsuarioDAO usuDAO = new UsuarioDAO(HibernateUtil.getSessionFactory().openSession());
		
		// Exclui o usuário do banco de dados
		usuDAO.excluir(usuDAO.getUsuario(codigo));
		
		// Fecha a conexão
		usuDAO.getSessao().close();
		
		//Redireciona para a página de gerenciamento de usuários
		req.getRequestDispatcher("gerenciarUsuarios.jsp").forward(req, resp);
	}

}
