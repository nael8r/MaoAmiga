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

public class MedicoFiltro implements Filter {

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;

		System.out.println(req.getServerName() + ":" + req.getServerPort() + ":" + req.getContextPath()+ ":" + req.getRequestURI());	
		
		HttpSession session = req.getSession(false);
		Usuario usuario = null;
		
		if (session != null && session.getAttribute("usuarioAutenticado") != null)
			usuario = (Usuario) session.getAttribute("usuarioAutenticado");

		if (usuario == null) {

			req.getRequestDispatcher(
					"mensagensErroServlet?mensagem=ERRO! - Usuário não autenticado!&direcao=login.jsp"
					).forward(req, resp);
		} else {

			if (usuario.getTipo() == 'm') {
				if (session.getAttribute("medicoAutenticado") != null) {
					Medico m = (Medico) session.getAttribute("medicoAutenticado");
					if (usuario.getNome().equals(m.getNome())) {

						chain.doFilter(request, response);
						
					} else {
						req.getRequestDispatcher(
							"mensagensErroServlet?mensagem=ERRO! - Usuário não autenticado!&direcao=login.jsp"
							).forward(req, resp);
					}
				} else {
					req.getRequestDispatcher(
						"mensagensErroServlet?mensagem=ERRO! - Usuário não autenticado!&direcao=login.jsp"
						).forward(req, resp);
				}
			} else if (usuario.getTipo() == 'u') {

				req.getRequestDispatcher(
					"mensagensErroServlet?mensagem=ERRO! - Você não é um médico!&direcao=index.jsp"
					).forward(req, resp);
			} else {
				req.getRequestDispatcher(
					"mensagensErroServlet?mensagem=ERRO! - Usuário não autenticado!&direcao=login.jsp"
					).forward(req, resp);
			}
		}

	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

}
