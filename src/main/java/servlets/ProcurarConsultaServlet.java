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

/*
	Servlet que realiza todos os procedimentos de busca de consulta
*/

@WebServlet("/procurarConsultaServlet")
public class ProcurarConsultaServlet extends HttpServlet {

	// Método para obter consultas que tiverem deerminado médico
	public List<Consulta> consultasApenasDoMedico(List<Consulta> consultas, Medico m) {
		// lista que será retornado os dados
		List<Consulta> novaLista = new ArrayList<Consulta>();

		// Para cada item da lista de consultas, seleciona apenas que tiverem determinado médico
		for (Consulta c : consultas) {
			if (c.getMedico().getCodigo() == m.getCodigo()) {
				novaLista.add(c);
			}
		}
		return novaLista;
	}

	private static final long serialVersionUID = -2945681250702720282L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// Verifica se exite alguma seção já definida anteriormente 
			// pra pegar a sessão de conexão com o banco
		Session sessao;
		if (req.getAttribute("sessao") == null) {
			// Instancia os objetos para operação de cadastramento
			sessao = HibernateUtil.getSessionFactory().openSession();
			req.getSession().setAttribute("sessao", sessao);
		} else {
			sessao = (Session) req.getSession().getAttribute("sessao");
		}
		
		

		// Controle de consultas
		ConsultaDAO consultaDao = new ConsultaDAO(sessao);

		// Caso a operação seja de procura
		if (req.getParameter("acao") != null && req.getParameter("acao").equals("procurar")) {

			// Define uma nova lista de consulta
			List<Consulta> consultas = new ArrayList<Consulta>();


			MedicoDAO medicoDAO = new MedicoDAO(HibernateUtil.getSessionFactory().openSession());
			Medico medico = null;

			// Recebe o médico responsável pelas consultas
			try {
				medico = (Medico) req.getSession().getAttribute("medicoAutenticado");
			} catch (Exception e) {
				req.getRequestDispatcher(
						"mensagemErroServlet?mensagem=ERRO! - Você não está logado como um usuário Médico!&direcao=login.jsp")
						.forward(req, resp);
			}

			//Se a procura for por:
				//data da consulta
			if (req.getParameter("data") != null && !req.getParameter("data").isEmpty()
					&& !req.getParameter("data").contains("data")) {

				// Recebe a data
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


					// Busca no banco pela data informada
					consultas = consultaDao.getConsultas(dt.getTime());

					// Filtra apenas os dados do médico
					consultas = consultasApenasDoMedico(consultas, medico);


				} catch (NullPointerException e) {
					req.getRequestDispatcher(
							"mensagensErroServlet?mensagem=ERRO! - Dados da data estão incorretos. Ex: 3, March 2010&direcao=procurarConsulta.jsp")
							.forward(req, resp);
				} finally {
					sessao.close();
				}


				// Por nome do paciente.
			} else if (req.getParameter("nome") != null && !req.getParameter("nome").isEmpty()) {

				try {

					// Pegas as consultas do médico
					consultas = medicoDAO.getConsultas(medico.getCodigo());

					// Filtra as que possui o respectivo nome do paciente
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


				// Por código da consulta
			} else if (req.getParameter("cod") != null && !req.getParameter("cod").isEmpty()) {

				Integer codigo = Integer.parseInt(req.getParameter("cod"));

				Consulta consulta = consultaDao.getConsulta(codigo);

				if (consulta != null) {
					consultas.add(consulta);

					consultas = consultasApenasDoMedico(consultas, medico);
				}


			// Caso nenhum parâmetro tenha sido definido, retorna todas as consultas
				// do respectivo médico
			} else {

				consultas = medicoDAO.getConsultas(medico.getCodigo());
			}

			// Define a lista na sessão
			req.getSession().setAttribute("consultas", consultas);

			// Redireciona para a exibição dos novos resulados
			req.getRequestDispatcher("procurarConsulta.jsp").forward(req, resp);



			// Operação de cancelamento
		} else if (req.getParameter("acao") != null && req.getParameter("acao").equals("cancelar")) {

			req.getRequestDispatcher("index.jsp").forward(req, resp);
			
			
			// Caso tenha selecionado alguma consulta
		} else if (req.getParameter("codigo_selecionado") != null
				&& !req.getParameter("codigo_selecionado").isEmpty()) {
			
			// Recebe os dados dessa consulta
			Consulta consulta = new Consulta();
			consulta = consultaDao.getConsulta(Integer.parseInt(req.getParameter("codigo_selecionado")));

			HttpSession session = req.getSession();
			
			// Define-a na sessão
			session.setAttribute("consulta", consulta);

			
			// Caso a ação seja
				// Prontoário médico
			if (req.getParameter("acao") != null && req.getParameter("acao").equals("prontuarioMedico")) {
				
				req.getRequestDispatcher("prontuarioMedico.jsp").forward(req, resp);
				
				
				// Exames
			} else if (req.getParameter("acao") != null && req.getParameter("acao").equals("exames")) {
				
				req.getRequestDispatcher("listaExames.jsp?").forward(req, resp);
				
				
				// Imprimir Consulta
			} else if (req.getParameter("acao") != null && req.getParameter("acao").equals("imprimir")) {

				req.getRequestDispatcher("imprimirConsulta.jsp").forward(req, resp);
				
			}

			// caso contrário, exibe comando inválido
		} else {
			req.getRequestDispatcher("mensagensErroServlet?mensagem=ERRO! - Comandos Inválidos!&direcao=index.jsp")
					.forward(req, resp);
		}
	}

}
