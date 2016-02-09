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

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		if (req.getParameter("acao").equals("preConsulta")) {
			
		}
		else if (req.getParameter("acao").equals("excluir")) {
			HttpSession session = req.getSession();

			Session sessao = HibernateUtil.getSessionFactory().openSession();
			
			ConsultaDAO cDAO = new ConsultaDAO(sessao);
			
			Consulta consulta = new Consulta();
			
			cDAO.excluir();
		}
	}
}
