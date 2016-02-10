package servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;

import conexao.HibernateUtil;
import controle.ConsultaDAO;
import controle.MedicoDAO;
import modelo.Consulta;
import modelo.Medico;

@WebServlet("/prontuarioMedico")
public class ProntuarioMedicoServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2875070341838719565L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// Instancia os objetos para operação de cadastramento
		Session sessao = HibernateUtil.getSessionFactory().openSession();

		// Retoma a consulta para alteração
		ConsultaDAO consultaDao = new ConsultaDAO(sessao);
		
		
		if (req.getParameter("acao") != null && req.getParameter("acao").equals("cancelar")) {

			req.getRequestDispatcher("index.jsp").forward(req, resp);
			
			
		} else if (req.getParameter("acao") != null && req.getParameter("acao").equals("atestado")) {

			req.getRequestDispatcher("atestadoMedico.jsp").forward(req, resp);
			
			
		} else if (req.getParameter("acao") != null && req.getParameter("acao").equals("receituario")) {

			req.getRequestDispatcher("receituarioMedico.jsp").forward(req, resp);
			
		} else 
			
			
			
			if (req.getParameter("codigo_selecionado") != null
				&& !req.getParameter("codigo_selecionado").isEmpty()) {
			
			Consulta consulta = new Consulta();
			consulta = consultaDao.getConsulta(Integer.parseInt(req.getParameter("codigo_selecionado")));

			req.getSession().setAttribute("consulta", consulta);

			
			if (req.getParameter("acao") != null && req.getParameter("acao").equals("preConsulta")) {

				req.getRequestDispatcher("prontuarioMedico.jsp").forward(req, resp);
				
				
			} else if (req.getParameter("acao") != null && req.getParameter("acao").equals("imprimir")) {

				req.getRequestDispatcher("imprimirConsulta.jsp").forward(req, resp);
				
			}
		} else {
			req.getRequestDispatcher("mensagensErroServlet?mensagem=ERRO! - Comandos Inválidos!&direcao=index.jsp")
					.forward(req, resp);
		}
	}
}
