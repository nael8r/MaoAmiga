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
import controle.MedicoDAO;
import controle.ProdutosDAO;
import modelo.Medico;
import modelo.Produtos;

@WebServlet("/cadastraMedico")
public class CadastraMedico extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		
		MedicoDAO medicoDAO = new MedicoDAO(sessao);
		
		Medico medico = new Medico();
		
		//medico.setCodigo(Integer.parseInt(req.getParameter("cod")));
		medico.setNome(req.getParameter("nome"));
		medico.setCrm(req.getParameter("crm"));
		medico.setEndereco(req.getParameter("endereco"));
		medico.setEspecialidade(req.getParameter("especialidades"));
		medico.setTelefone(req.getParameter("telefone"));
		
		
		medicoDAO.salvar(medico);
		
		req.getRequestDispatcher("index.jsp").forward(req, resp);
	}
}
