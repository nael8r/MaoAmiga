package servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
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
import controle.PacienteDAO;
import modelo.Consulta;
import modelo.Paciente;
import modelo.ReceituarioExames;
import modelo.ReceituarioMedico;

@WebServlet("/agendarConsulta")
public class agendarNovaConsulta extends HttpServlet {

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

			req.getRequestDispatcher("procurarPaciente.jsp").forward(req, resp);
		}
		else if (req.getParameter("acao").equals("procurarMedico")) {
			
				// Retoma a consulta para alteração
				Consulta consulta = (Consulta)session.getAttribute("consulta");
				
				// Instancia o PacienteDAO para que possa recuperar o objeto paciente pelo seu código
				PacienteDAO pacienteDAO = new PacienteDAO( (Session) session.getAttribute("sessao"));
				
				// Recebe o código do paciente
				String pacienteCod = req.getParameter("paciente");
				
				// Procura o objeto Paciente por meio do código e define no atributo da consulta
				consulta.setPaciente(pacienteDAO.getPaciente(Integer.parseInt(pacienteCod)));
				
				// Redireciona para a próxima página 
				req.getRequestDispatcher("procurarMedico.jsp").forward(req, resp);
			}
			else if (req.getParameter("acao").equals("agendarConsulta")) {
				
					// Retoma a consulta para alteração
					Consulta consulta = (Consulta)session.getAttribute("consulta");
					
					// Instancia o PacienteDAO para que possa recuperar o objeto paciente pelo seu código
					MedicoDAO medicoDAO = new MedicoDAO( (Session) session.getAttribute("sessao"));
					
					// Recebe o código do paciente
					String medicoCod = req.getParameter("medico");
					
					// Procura o objeto Paciente por meio do código e define no atributo da consulta
					consulta.setMedico(medicoDAO.getMedico(Integer.parseInt(medicoCod)));
					
					// Redireciona para a próxima página 
					req.getRequestDispatcher("agendarConsulta.jsp").forward(req, resp);
				}
				else if (req.getParameter("acao").equals("final")) {

						// Retoma a consulta para alteração
						Consulta consulta = (Consulta)session.getAttribute("consulta");

						ConsultaDAO consultaDao = new ConsultaDAO((Session) session.getAttribute("sessao"));
						
						session.setAttribute("consultaDao", consultaDao);
						
						Float altura, temperatura, peso;
						// TODO data e hora
						//Date data, hora;
						Boolean usoMedicamentos, atestado;
						Character tipoConsulta, pessoais;
						String anotacoesFinais, pressaoArterial, queixa;
						// TODO ReceituarioSSSSS
						//List<ReceituarioExames> receituariosExames = new ArrayList<ReceituarioExames>();
						//List<ReceituarioMedico> receituariosMedicos = new ArrayList<ReceituarioMedico>();
						

						altura          = Float.parseFloat(req.getParameter("altura"));
						temperatura     = Float.parseFloat(req.getParameter("temperatura"));
						peso            = Float.parseFloat(req.getParameter("peso"));
						
						usoMedicamentos = Boolean.parseBoolean(req.getParameter("usoMedicamentos"));
						atestado        = Boolean.parseBoolean(req.getParameter("atestado"));
						
						tipoConsulta    = req.getParameter("tipoConsulta").charAt(0);
						pessoais        = req.getParameter("pessoais").charAt(0);
						
						anotacoesFinais = req.getParameter("anotacoesFinais");
						pressaoArterial = req.getParameter("pressaoArterial");
						queixa          = req.getParameter("queixa");
						
						consulta.setAltura(altura);
						consulta.setAnotacoesFinais(anotacoesFinais);
						consulta.setAtestado(atestado);
						// TODO
						//consulta.setData(data);
						//consulta.setHora(hora);
						consulta.setPeso(peso);
						consulta.setPessoais(pessoais);
						consulta.setPressaoArterial(pressaoArterial);
						consulta.setQueixa(queixa);
						// TODO
						//consulta.setReceituariosExames(receituariosExames);
						//consulta.setReceituariosMedicos(receituariosMedicos);
						consulta.setTemperatura(temperatura);
						consulta.setTipoConsulta(tipoConsulta);
						consulta.setUsoMedicamentos(usoMedicamentos);
						
						consultaDao.salvar(consulta);
						
						// Redireciona para a próxima página 
						req.getRequestDispatcher("index.jsp").include(req, resp);
					}
		
	}
}
