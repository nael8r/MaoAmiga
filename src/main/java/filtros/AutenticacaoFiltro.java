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

public class AutenticacaoFiltro implements Filter {

	public void destroy() {
		// TODO Auto-generated method stub
		
	}
	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;

		/*
		System.out.println(req.getServerName() + ":" + req.getServerPort() + ":" + req.getContextPath()+ ":" + req.getRequestURI());
		
		
		
		if (req.getRequestURI().equals("/MaoAmiga/") || req.getRequestURI().contains("login.jsp") || req.getRequestURI().contains("css") || req.getRequestURI().contains("js/") || req.getRequestURI().contains("font")) {
			chain.doFilter(request, response);
		} else {
			HttpSession session = req.getSession(false);
			Usuario usuario = null;
			
			if (session != null && session.getAttribute("usuarioAutenticado") != null)
				usuario = (Usuario) session.getAttribute("usuarioAutenticado");

			if (usuario == null) {
				
				req.getRequestDispatcher("login.jsp").forward(req, resp);
			} else {

				chain.doFilter(request, response);
			}
		}
		*/
		
		/*
		else if (usuario.getTipo() == 'm') {
			if (session.getAttribute("medicoAutenticado") != null) {
				Medico m = (Medico) session.getAttribute("medicoAutenticado");
				if (usuario.getNome().equals(m.getNome())) {
					
					if (req.getRequestURL().toString().contains("AutenticaLogin")) {
						chain.doFilter(request, response);
						
					} else if (req.getRequestURL().toString().contains("listaPacientes")) {
						chain.doFilter(request, response);
						
					} else if (req.getRequestURL().toString().contains("index")) {
						chain.doFilter(request, response);
						
					} else if (req.getRequestURL().toString().contains("procurarMedicamento")) {
						chain.doFilter(request, response);
						
					} else if (req.getRequestURL().toString().contains("consultarMedicamentoServlet")) {
						chain.doFilter(request, response);
						
					} else if (req.getRequestURL().toString().contains("listaEspera")) {
						chain.doFilter(request, response);
						
					} else if (req.getRequestURL().toString().contains("procurarConsulta")) {
						chain.doFilter(request, response);
						
					} else if (req.getRequestURL().toString().contains("prontuarioMedico")) {
						chain.doFilter(request, response);
		
					} else {
						session.invalidate();
						resp.sendRedirect("login.jsp");
					}
					
				} else {
					session.invalidate();
					resp.sendRedirect("login.jsp");
				}
			} else {
				session.invalidate();
				resp.sendRedirect("login.jsp");
			}
		} else if (usuario.getTipo() == 'u') {
			
		} else {
			session.invalidate();
			resp.sendRedirect("login.jsp");
		
		}*/
		
		//chain.doFilter(request, response);
	}
	
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}

}
