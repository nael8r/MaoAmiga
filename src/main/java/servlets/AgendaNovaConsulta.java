package servlets;

import java.io.IOException;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.Session;

import conexao.HibernateUtil;
import controle.ConsultaDAO;
import controle.EsperaDAO;
import controle.MedicoDAO;
import controle.PacienteDAO;
import modelo.Consulta;
import modelo.Espera;
import modelo.Paciente;

/*
	Servlet de agendamento de uma nova consulta com sua informações básicas
*/

@WebServlet("/agendarConsulta")
public class AgendaNovaConsulta extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		HttpSession session = req.getSession();

		Session sessao = HibernateUtil.getSessionFactory().openSession();
		
		try {

			// Retoma a consulta para alteração
			Consulta consulta = (Consulta)session.getAttribute("consulta");
			ConsultaDAO consultaDao = new ConsultaDAO(sessao);

			// Recebe os dados básicos da consulta
			String data = req.getParameter("data");
			int hora = Integer.parseInt(req.getParameter("hora"));
			int min = Integer.parseInt(req.getParameter("minuto"));
			Character tipo = req.getParameter("tipo").charAt(0);

			// Realiza conversão do formato da hora para o arquivamento no banco de dados
			Calendar dt = Calendar.getInstance();
			Calendar horario = Calendar.getInstance();
			
			int dia, mes, ano;

			// hora ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
			horario.set(Calendar.HOUR_OF_DAY, hora);
			horario.set(Calendar.MINUTE, min);
			
			consulta.setHora(horario.getTime());
			
			// dia ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
			String diaString = null;
			
			if (data.charAt(1) == ' ') {

				diaString = data.substring(0, 1);
				
				
				data = "x".concat(data);
				
			} else {
				diaString = data.substring(0, 2);
			}
			
			dia = Integer.parseInt(diaString);
			
			// ano ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
			int tamStringData = data.length();
			
			ano = Integer.parseInt(data.substring(tamStringData - 4, tamStringData));
		
			// mes ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~	
			data = data.substring(3, 6);
			
			if (data.equals("Jan")) {
				mes = 0;
			} else if (data.equals("Feb")) {
				mes = 1;
			} else if (data.equals("Mar")) {
				mes = 2;
			} else if (data.equals("Apr")) {
				mes = 3;
			} else if (data.equals("May")) {
				mes = 4;
			} else if (data.equals("Jun")) {
				mes = 5;
			} else if (data.equals("Jul")) {
				mes = 6;
			} else if (data.equals("Aug")) {
				mes = 7;
			} else if (data.equals("Sep")) {
				mes = 8;
			} else if (data.equals("Oct")) {
				mes = 9;
			} else if (data.equals("Nov")) {
				mes = 10;
			} else if (data.equals("Dez")) {
				mes = 11;
			} else mes = 0;

			
			dt.set(ano, mes + 1, dia);
			
			int quantConsultasDia = consultaDao.getConsultas(dt.getTime()).size();
			
			// Verifica o limite de 7 consultas ao dia
			if (quantConsultasDia >= 7) {	
				sessao.close();
				// Direciona-o para a tela de index
				req.getRequestDispatcher(
						"mensagensErroServlet?mensagem=ERRO! - O número máximo de consultas (7) já foi atingido!&direcao=index.jsp"
						).forward(req, resp);
				
				// Caso contrário, continua com o cadastro
			} else {

				dt.set(ano, mes, dia);
				
				// cria um novo objeto para arquivamento da lista de espera
				Espera novaEspera = new Espera(dt.getTime(), consulta.getPaciente());
				EsperaDAO esperaDAO = new EsperaDAO(HibernateUtil.getSessionFactory().openSession());
				
				
				consulta.setData(dt.getTime());
				
				consulta.setTipoConsulta(tipo);
				
				// Salva a consulta e tambem o item Espera
				consultaDao.salvar(consulta);
				esperaDAO.salvar(novaEspera);
				
				sessao.close();
				
				// Redireciona para a próxima página 
				resp.sendRedirect("index.jsp");
			}
		} catch (Exception e) {
			sessao.close();
			// Direciona-o para a tela de index
			req.getRequestDispatcher(
					"mensagensErroServlet?mensagem=ERRO! - Dados incorretos!&direcao=index.jsp"
					).forward(req, resp);
		}
		
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		HttpSession session = req.getSession();
		
		if (req.getParameter("acao").equals("procurarPaciente")) {

			// Instancia os objetos para operação de cadastramento
			Session sessao = HibernateUtil.getSessionFactory().openSession();
			
			Consulta consulta = new Consulta();
			
			// Define cada um na sessão para uso posteriori
			session.setAttribute("consulta", consulta);
			session.setAttribute("sessao", sessao);

			req.getRequestDispatcher("procurarPaciente.jsp?cod=&nome=").forward(req, resp);
		}
		else if (req.getParameter("acao").equals("procurarMedico")) {
			
				// Retoma a consulta para alteração
				Consulta consulta = (Consulta)session.getAttribute("consulta");
				
				// Instancia o PacienteDAO para que possa recuperar o objeto paciente pelo seu código
				PacienteDAO pacienteDAO = new PacienteDAO( (Session) session.getAttribute("sessao"));
				
				// Recebe o código do paciente
				int pacienteCod = Integer.parseInt(req.getParameter("cod"));
				
				Paciente paciente = pacienteDAO.getPaciente(pacienteCod);
				
				// Procura o objeto Paciente por meio do código e define no atributo da consulta
				consulta.setPaciente(paciente);
				
				session.setAttribute("consulta", consulta);
				
				// Redireciona para a próxima página 
				req.getRequestDispatcher("procurarMedico.jsp?cod=&nome=").forward(req, resp);
			}
			else if (req.getParameter("acao").equals("agendarConsulta")) {
				
					// Retoma a consulta para alteração
					Consulta consulta = (Consulta)session.getAttribute("consulta");
					
					// Instancia o PacienteDAO para que possa recuperar o objeto paciente pelo seu código
					MedicoDAO medicoDAO = new MedicoDAO( (Session) session.getAttribute("sessao"));
					
					// Recebe o código do paciente
					int medicoCod = Integer.parseInt(req.getParameter("cod"));
					
					// Procura o objeto Paciente por meio do código e define no atributo da consulta
					consulta.setMedico(medicoDAO.getMedico(medicoCod));
					
					session.setAttribute("consulta", consulta);
					
					// Redireciona para a próxima página 
					req.getRequestDispatcher("agendarConsulta.jsp").forward(req, resp);
				}		
	}
}
