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
import controle.PacienteDAO;
import controle.UsuarioDAO;
import modelo.Usuario;

@WebServlet("/listaPacientes")
public class ListaPacientes extends HttpServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7990034399384818368L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		MedicoDAO dao = new MedicoDAO(sessao);
		
		req.getSession().setAttribute("mdcDAO", dao);
		
		req.getRequestDispatcher("listaPacientes.jsp").forward(req, resp);
	}
}
