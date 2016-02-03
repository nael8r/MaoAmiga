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

import org.hibernate.Session;

import conexao.HibernateUtil;
import controle.ConsultaDAO;
import controle.EsperaDAO;
import controle.MedicoDAO;
import controle.PacienteDAO;
import controle.ProdutosDAO;
import controle.ReceituarioExamesDAO;
import controle.ReceituarioMedicoDAO;
import controle.UsuarioDAO;

public class RegisterDaoFiltro implements Filter {

	public void destroy() {

	}

	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest requisicao = (HttpServletRequest) req;
		HttpServletResponse resposta = (HttpServletResponse) resp;
		
		ConsultaDAO cstDAo = new ConsultaDAO(HibernateUtil.getSessionFactory().openSession());
		EsperaDAO espDAO = new EsperaDAO(HibernateUtil.getSessionFactory().openSession());
		MedicoDAO mdcDAO = new MedicoDAO(HibernateUtil.getSessionFactory().openSession());
		PacienteDAO pctDAo = new PacienteDAO(HibernateUtil.getSessionFactory().openSession());
		ProdutosDAO pdtDAO = new ProdutosDAO(HibernateUtil.getSessionFactory().openSession());
		ReceituarioExamesDAO reDAO = new ReceituarioExamesDAO(HibernateUtil.getSessionFactory().openSession());
		ReceituarioMedicoDAO rmDAO = new ReceituarioMedicoDAO(HibernateUtil.getSessionFactory().openSession());
		UsuarioDAO usuDAO = new UsuarioDAO(HibernateUtil.getSessionFactory().openSession());
		
		HttpSession sessaoHttp = requisicao.getSession();
		
		sessaoHttp.setAttribute("consultaDAO", cstDAo);
		sessaoHttp.setAttribute("EsperaDAO", espDAO);
		sessaoHttp.setAttribute("medicoDAO", mdcDAO);
		sessaoHttp.setAttribute("pacienteDAO", pctDAo);
		sessaoHttp.setAttribute("produtosDAO", pdtDAO);
		sessaoHttp.setAttribute("receituarioExamesDAO", reDAO);
		sessaoHttp.setAttribute("receituarioMedicoDAO", rmDAO);
		sessaoHttp.setAttribute("usuarioDAO", usuDAO);
		
		chain.doFilter(requisicao, resposta);

	}

	public void init(FilterConfig arg0) throws ServletException {

	}

}
