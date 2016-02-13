package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;

import conexao.HibernateUtil;
import controle.PacienteDAO;
import modelo.Paciente;

/*
	Procedimento de cadastramento de um novo paciente
*/

@WebServlet("/cadastraPaciente")
public class CadastraPaciente extends HttpServlet {

	private static final long serialVersionUID = 1286739559946171924L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		
		PacienteDAO pacienteDAO = new PacienteDAO(sessao);
		
		// Cria um novo objeto para salvar as informações
		Paciente paciente = new Paciente();
		
		// Salva os dados no objeto
		paciente.setNome(req.getParameter("nome"));
		paciente.setCpf(req.getParameter("cpf"));
		paciente.setRg(req.getParameter("rg"));
		
		// Salva o novo objeto no banco de dados
		pacienteDAO.salvar(paciente);
		
		
		// Redireciona-o para o index
		req.getRequestDispatcher("index.jsp").forward(req, resp);
	}
}
