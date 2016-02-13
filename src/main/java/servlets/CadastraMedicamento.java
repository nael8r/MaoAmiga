package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;

import conexao.HibernateUtil;
import controle.ProdutosDAO;
import modelo.Produtos;

/*
	Procedimento de cadastro de um novo Médico
*/

@WebServlet("/cadastraMedicamento")
public class CadastraMedicamento extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2519196547741338362L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		
		ProdutosDAO produtosDAO = new ProdutosDAO(sessao);
		
		// Novo objeto para armazenamento das informações
		Produtos produto = new Produtos();
		
		// Salva os novos dados no objeto
		produto.setNome(req.getParameter("nome"));
		
		// Salva o objeto no banco de dados
		produtosDAO.salvar(produto);
		
		// Redireciona para o index
		req.getRequestDispatcher("index.jsp").forward(req, resp);
	}
}
