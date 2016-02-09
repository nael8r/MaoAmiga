package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.Session;
import org.hibernate.Transaction;

import conexao.HibernateUtil;
import controle.ConsultaDAO;
import modelo.Consulta;

@WebServlet("/operaConsulta")
public class OperaConsulta extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5692333961401394154L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		Session sessao = HibernateUtil.getSessionFactory().openSession();
		
		ConsultaDAO cDAO = new ConsultaDAO(sessao);

		HttpSession session = req.getSession();
		
		if (req.getParameter("acao").equals("preConsulta")) {

			Consulta consulta = new Consulta();
			consulta = cDAO.getConsulta(Integer.parseInt(req.getParameter("cod")));
			
			session.setAttribute("consulta", consulta);
			
			req.getRequestDispatcher("preConsulta.jsp").forward(req, resp);
		}
		else if (req.getParameter("acao").equals("preConsultaConcluida")) {
			
			Consulta consulta = (Consulta)session.getAttribute("consulta");	
			
			int idade;
			float altura, temperatura, peso;
			char sexo;
			String pressao;

			try {
				altura          = Float.parseFloat(req.getParameter("altura"));
				temperatura     = Float.parseFloat(req.getParameter("temperatura"));
				pressao         = req.getParameter("pressao");
				peso            = Float.parseFloat(req.getParameter("peso"));
				idade           = Integer.parseInt(req.getParameter("idade"));
				sexo            = req.getParameter("sexo").charAt(0);
			
				if (sexo == 'X') {
					req.getRequestDispatcher("mensagemErroServlet?mensagem=ERRO! - Preencha o campo Sexo corretamente!&direcao=preConsulta.jsp").forward(req, resp);
				}
				
	
				consulta.setAltura(altura);
				consulta.setPeso(peso);
				consulta.setTemperatura(temperatura);
				consulta.setIdade(idade);
				consulta.setSexo(sexo);
				consulta.setPressaoArterial(pressao);
				
				Transaction t = sessao.beginTransaction();
				
					cDAO.atualizar(consulta);
					
					consulta = null;
				
				t.commit();
				
				sessao.close();		
	
				resp.sendRedirect("index.jsp");
				
				
			} catch (Exception e) {
				req.getRequestDispatcher("mensagemErroServlet?mensagem=ERRO! - Preencha todos os campos corretamente!&direcao=preConsulta.jsp").forward(req, resp);
			}
		}
		else if (req.getParameter("acao").equals("excluir")) {

			Consulta consulta = new Consulta();
			consulta = cDAO.getConsulta(Integer.parseInt(req.getParameter("cod")));
			
			
			Transaction t = sessao.beginTransaction();
			
				cDAO.excluir(consulta);
			
				consulta = null;
			
			t.commit();
			
			
			sessao.close();

			resp.sendRedirect("agendaConsultas.jsp?data=");
		}
	}
}
