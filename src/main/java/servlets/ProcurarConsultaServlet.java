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
import javax.servlet.http.HttpSession;

import org.hibernate.Session;

import conexao.HibernateUtil;
import controle.ConsultaDAO;
import controle.MedicoDAO;
import modelo.Consulta;
import modelo.Medico;

@WebServlet("/procurarConsultaServlet")
public class ProcurarConsultaServlet extends HttpServlet {

	public List<Consulta> consultasApenasDoUsuario(List<Consulta> consultas, Medico m) {
		List<Consulta> novaLista = new ArrayList<Consulta>();

		for (Consulta c : consultas) {
			if (c.getMedico().getCodigo() == m.getCodigo()) {
				novaLista.add(c);
			}
		}
		return novaLista;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -2945681250702720282L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		Session sessao;
		if (req.getAttribute("sessao") == null) {
			// Instancia os objetos para operação de cadastramento
			sessao = HibernateUtil.getSessionFactory().openSession();
			req.getSession().setAttribute("sessao", sessao);
		} else {
			sessao = (Session) req.getSession().getAttribute("sessao");
		}
		
		

		// Retoma a consulta para alteração
		ConsultaDAO consultaDao = new ConsultaDAO(sessao);

		if (req.getParameter("acao") != null && req.getParameter("acao").equals("procurar")) {

			List<Consulta> consultas = new ArrayList<Consulta>();

			MedicoDAO medicoDAO = new MedicoDAO(HibernateUtil.getSessionFactory().openSession());
			Medico medico = null;

			try {
				medico = (Medico) req.getSession().getAttribute("medicoAutenticado");
			} catch (Exception e) {
				req.getRequestDispatcher(
						"mensagemErroServlet?mensagem=ERRO! - Você não está logado como um usuário Médico!&direcao=login.jsp")
						.forward(req, resp);
			}
			if (req.getParameter("data") != null && !req.getParameter("data").isEmpty()
					&& !req.getParameter("data").contains("data")) {

				try {
					Calendar dt = Calendar.getInstance();
					int dia, mes, ano;
					int tamStringData;

					String data = req.getParameter("data");

					if (data.contains("data")) {
						req.getRequestDispatcher(
								"mensagemErroServlet?mensagem=ERRO! - Dados da data estão incorretos. Ex: 3, March 2010&direcao=procurarConsulta.jsp")
								.forward(req, resp);
					}

					// data - recebe o dia
					// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
					String diaString = null;

					if (data.charAt(1) == ' ') {

						diaString = data.substring(0, 1);

						data = "x".concat(data);

					} else {
						diaString = data.substring(0, 2);
					}

					dia = Integer.parseInt(diaString);

					// ano - recebe o ano
					// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
					tamStringData = data.length();

					ano = Integer.parseInt(data.substring(tamStringData - 4, tamStringData));

					// Mes - recebe o Mes
					// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
					data = data.substring(3, 6);

					mes = util.DateUtils.getMes(data) + 1;

					dt.set(ano, mes, dia);

					consultas = consultaDao.getConsultas(dt.getTime());

					consultas = consultasApenasDoUsuario(consultas, medico);

				} catch (NullPointerException e) {
					req.getRequestDispatcher(
							"mensagensErroServlet?mensagem=ERRO! - Dados da data estão incorretos. Ex: 3, March 2010&direcao=procurarConsulta.jsp")
							.forward(req, resp);
				} finally {
					sessao.close();
				}
			} else if (req.getParameter("nome") != null && !req.getParameter("nome").isEmpty()) {

				try {

					consultas = medicoDAO.getConsultas(medico.getCodigo());

					List<Consulta> consultasNomePaciente = new ArrayList<Consulta>();

					for (Consulta consulta : consultas) {
						if (consulta.getPaciente().getNome().contains(req.getParameter("nome"))) {
							consultasNomePaciente.add(consulta);
						}
					}

					consultas.clear();

					consultas = consultasNomePaciente;

				} catch (Exception e) {
					req.getRequestDispatcher(
							"mensagensErroServlet?mensagem=ERRO! - Você não está logado como um usuário Médico!&direcao=login.jsp")
							.forward(req, resp);
				}

			} else if (req.getParameter("cod") != null && !req.getParameter("cod").isEmpty()) {

				Integer codigo = Integer.parseInt(req.getParameter("cod"));

				Consulta consulta = consultaDao.getConsulta(codigo);

				if (consulta != null) {
					consultas.add(consulta);

					consultas = consultasApenasDoUsuario(consultas, medico);
				}

			} else {

				consultas = medicoDAO.getConsultas(medico.getCodigo());
			}

			req.getSession().setAttribute("consultas", consultas);

			req.getRequestDispatcher("procurarConsulta.jsp").forward(req, resp);

		} else if (req.getParameter("acao") != null && req.getParameter("acao").equals("cancelar")) {

			req.getRequestDispatcher("index.jsp").forward(req, resp);
			
			
		} else if (req.getParameter("codigo_selecionado") != null
				&& !req.getParameter("codigo_selecionado").isEmpty()) {
			
			Consulta consulta = new Consulta();
			consulta = consultaDao.getConsulta(Integer.parseInt(req.getParameter("codigo_selecionado")));

			HttpSession session = req.getSession();
			
			session.setAttribute("consulta", consulta);
			

			
			if (req.getParameter("acao") != null && req.getParameter("acao").equals("prontuarioMedico")) {
				
				req.getRequestDispatcher("prontuarioMedico.jsp").forward(req, resp);
				
				
			} else if (req.getParameter("acao") != null && req.getParameter("acao").equals("exames")) {
				
				req.getRequestDispatcher("listaExames.jsp?").forward(req, resp);
				
				
			} else if (req.getParameter("acao") != null && req.getParameter("acao").equals("imprimir")) {

				req.getRequestDispatcher("imprimirConsulta.jsp").forward(req, resp);
				
			}
		} else {
			req.getRequestDispatcher("mensagensErroServlet?mensagem=ERRO! - Comandos Inválidos!&direcao=index.jsp")
					.forward(req, resp);
		}
	}

}
