package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.Session;

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

		ConsultaDAO cDAO;
		
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		
		HttpSession session = req.getSession();

		if (req.getParameter("acao") != null) {
			
			if (req.getParameter("acao").equals("preConsultaConcluida")) {

				cDAO = new ConsultaDAO(sessao);
				Consulta consulta = (Consulta) session.getAttribute("consulta");

				int idade;
				float altura, temperatura, peso;
				char sexo;
				String pressao;

				try {
					altura = Float.parseFloat(req.getParameter("altura"));
					temperatura = Float.parseFloat(req.getParameter("temperatura"));
					pressao = req.getParameter("pressao");
					peso = Float.parseFloat(req.getParameter("peso"));
					idade = Integer.parseInt(req.getParameter("idade"));
					sexo = req.getParameter("sexo").charAt(0);

					if (sexo == 'X') {
						req.getRequestDispatcher(
								"mensagensErroServlet?mensagem=ERRO! - Preencha o campo Sexo corretamente!&direcao=preConsulta.jsp")
								.forward(req, resp);
					}

					consulta.setAltura(altura);
					consulta.setPeso(peso);
					consulta.setTemperatura(temperatura);
					consulta.setIdade(idade);
					consulta.setSexo(sexo);
					consulta.setPressaoArterial(pressao);

					cDAO.atualizar(consulta);

					sessao.close();
					consulta = null;

					req.getRequestDispatcher("index.jsp")
							.forward(req, resp);

				} catch (Exception e) {
					req.getRequestDispatcher(
							"mensagensErroServlet?mensagem=ERRO! - Erro no ato de salvar os dados!&direcao=listaConsultas.jsp")
							.forward(req, resp);
					sessao.close();
					e.printStackTrace();
				}
			} else {
				cDAO = new ConsultaDAO(sessao);
				session.setAttribute("consultaDAO", cDAO);
				
				Consulta consulta = cDAO.getConsulta(Integer.parseInt(req.getParameter("cod")));

				
				if (req.getParameter("acao").equals("preConsulta")) {

					session.setAttribute("consulta", consulta);

					sessao.close();
					
					req.getRequestDispatcher("preConsulta.jsp").forward(req, resp);
					
				} else if (req.getParameter("acao").equals("imprimir")) {

					session.setAttribute("consulta", consulta);
					
					consulta = cDAO.getConsulta(Integer.parseInt(req.getParameter("cod")));

					sessao.close();
					
					req.getRequestDispatcher("imprimirConsulta.jsp").forward(req, resp);
					
				} else if (req.getParameter("acao").equals("excluir")) {

					// TODO
					cDAO.excluir(consulta);

					consulta = null;
					
					sessao.close();

					req.getRequestDispatcher("index.jsp")
							.forward(req, resp);
				}
			}
		}
	}
}
