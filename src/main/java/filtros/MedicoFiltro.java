package filtros;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import modelo.Medico;
import modelo.Usuario;

/*
	Filtro responsável por gerenciar as páginas que são responsáveis
		pelo médico.
	Qualquer acesso que não for oriundo de um médico será considerado
		intruso
*/

public class MedicoFiltro implements Filter {

	@Override
	public void destroy() {

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;


		// Recolhe a sessão atual sem criar uma nova
		HttpSession session = req.getSession(false);
		Usuario usuario = null;
		
		// Verifica se a sessão existe e se possui um usuário autenticado nela
		if (session != null && session.getAttribute("usuarioAutenticado") != null)
			// Se a sessão existir, recebe o usuário autenticado para avaliação
			usuario = (Usuario) session.getAttribute("usuarioAutenticado");

		// Caso o usuário autenticado seja inexistente
		if (usuario == null) {

			// Direciona-o para a tela de login
			req.getRequestDispatcher(
					"mensagensErroServlet?mensagem=ERRO! - Usuário não autenticado!&direcao=login.jsp"
					).forward(req, resp);

			// Caso exista o usuário autenticado
		} else {
			// Verifica se quem está solicitando acesso é um médico
			if (usuario.getTipo() == 'm') {
				// Se sim, requisita o objeto que é acoplado à sessão do tipo Medico

				if (session.getAttribute("medicoAutenticado") != null) {
					// Se existir, recebe este
					Medico m = (Medico) session.getAttribute("medicoAutenticado");

					// Verifica se o nome de ambos os objetos (usuario e medico) são iguais
					if (usuario.getNome().equals(m.getNome())) {

						// Se sim, libera para acesso à requisição feita
						chain.doFilter(request, response);
						
						// Caso contrário, diz que o acesso é proibido
					} else {
						req.getRequestDispatcher(
							"mensagensErroServlet?mensagem=ERRO! - Usuário não autenticado!&direcao=login.jsp"
							).forward(req, resp);
					}

					// Caso contrário, diz que o acesso é proibido
				} else {
					req.getRequestDispatcher(
						"mensagensErroServlet?mensagem=ERRO! - Usuário não autenticado!&direcao=login.jsp"
						).forward(req, resp);
				}
			// Verifica se é um secretário (u)
			} else if (usuario.getTipo() == 'u') {

				// Informa que médico não pode realizar esta operação
					// encaminha para o index
				req.getRequestDispatcher(
					"mensagensErroServlet?mensagem=ERRO! - Você não é um médico!&direcao=index.jsp"
					).forward(req, resp);

			// Se também não for um médico, fala que o usuário é inválido
				// encaminha para o login
			} else {
				req.getRequestDispatcher(
					"mensagensErroServlet?mensagem=ERRO! - Usuário não autenticado!&direcao=login.jsp"
					).forward(req, resp);
			}
		}

	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {

	}

}
