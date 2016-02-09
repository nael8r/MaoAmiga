package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.Transaction;

import conexao.HibernateUtil;
import controle.PacienteDAO;
import modelo.Paciente;
import controle.PacienteDAO;

@WebServlet("/cadastraPaciente")
public class CadastraPaciente extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		
		PacienteDAO pacienteDAO = new PacienteDAO(sessao);
		
		Paciente paciente = new Paciente();
		
		paciente.setCodigo(Integer.parseInt(req.getParameter("cod")));
		paciente.setNome(req.getParameter("nome"));
		paciente.setCpf(req.getParameter("cpf"));
		paciente.setRg(req.getParameter("rg"));
		
		
		if (pacienteDAO.getPaciente(paciente.getCodigo()) == null) {

			Transaction transacao = sessao.beginTransaction();
			pacienteDAO.salvar(paciente);
			transacao.commit();
		}
		else {
			pacienteDAO.atualiza(paciente);
		}
		
		
		req.getRequestDispatcher("index.jsp").forward(req, resp);
	}
}
