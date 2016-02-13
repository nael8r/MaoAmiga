package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.Session;

import conexao.HibernateUtil;
import controle.MedicoDAO;
import controle.UsuarioDAO;
import modelo.Medico;
import modelo.Usuario;


/*
	Procedimento responsável por realizar a autenticação do usuário ao 
	iniciar um acesso às páginas
*/

public class Autenticador extends HttpServlet {
	
	private static final long serialVersionUID = -1557989432133977441L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Session session = HibernateUtil.getSessionFactory().openSession();
		
		// Como a página inicial, invalida qualquer tipo de sessão prévia do usuário.
			// isso para que ele realize um novo login sem depender de qualquer histórico
			// ou cookie
		req.getSession().invalidate();
		
		// Recebe os valores digitados
		String login = req.getParameter("login");
		String senha = req.getParameter("senha");
		
		// Instancia um novo usuário
		UsuarioDAO usuarioDAO = new UsuarioDAO(session);

		// Valida os dados informados e salva o resultado no novo usuário
		Usuario usuario = usuarioDAO.validaLogin(login, senha);
		
		// Verifica se o resultado é válido
		if (usuario != null) {
			// Se sim
				// Recupera e cria uma nova caso nao tiver/
			HttpSession sessao = req.getSession();
			
			// salva o novo usuário na sessão.
			sessao.setAttribute("usuarioAutenticado", usuario);
			
			// Se for o login de um médico, salva seus dados também na sessão
				// Isso é necessário para que depois possa recuperar informações
				// exclusivas deste médico
			if (Character.toLowerCase(usuario.getTipo()) == 'm') {
				MedicoDAO medicoDAO = new MedicoDAO(session);
				
				// cria e acopla a sessão.
				Medico medico = medicoDAO.getMedico(usuario.getNome());
				
				sessao.setAttribute("medicoAutenticado", medico);
			}
			// caso contrário, não faz nada

			// redireciona para a página principal
			req.getRequestDispatcher("index.jsp").forward(req, resp);

			// se não for um usuário valido
		} else {
			// Limpa a sessão parar firmar a exclusão
			session.clear();
			
			// informa o erro
			req.getRequestDispatcher("mensagensErroServlet?mensagem=ERRO! - Dados de login inválidos!&direcao=login.jsp").forward(req, resp);
		}
			
	}
}
