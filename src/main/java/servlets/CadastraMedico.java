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
import modelo.Medico;

/*
	Procedimento de cadastro de um novo Médico
*/

@WebServlet("/cadastraMedico")
public class CadastraMedico extends HttpServlet {

	private static final long serialVersionUID = 3311403160524351732L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		
		MedicoDAO medicoDAO = new MedicoDAO(sessao);
		
		// Novo objeto para armazenamento das informações
		Medico medico = new Medico();
		
		// Salva os novos dados no objeto
		medico.setNome(req.getParameter("nome"));
		medico.setCrm(req.getParameter("crm"));
		medico.setEndereco(req.getParameter("endereco"));
		medico.setEspecialidade(req.getParameter("especialidades"));
		medico.setTelefone(req.getParameter("telefone"));
		
		// Salva o objeto no banco de dados
		medicoDAO.salvar(medico);
		
		// Redireciona para o index
		req.getRequestDispatcher("index.jsp").forward(req, resp);
	}
}
