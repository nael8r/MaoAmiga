package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;

import conexao.HibernateUtil;
import controle.MedicoDAO;

/*
	Servlet que realiza a inicialização dos dados da listagem de pacientes.
		É definido o controle do médico na sessão para que a página possa selecionar 
		apenas os pacietnes do médico responsável
*/

@WebServlet("/listaPacientes")
public class ListaPacientes extends HttpServlet {
	
	private static final long serialVersionUID = 7990034399384818368L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		MedicoDAO dao = new MedicoDAO(sessao);
		
		req.getSession().setAttribute("mdcDAO", dao);
		
		req.getRequestDispatcher("listaPacientes.jsp").forward(req, resp);
	}
}
