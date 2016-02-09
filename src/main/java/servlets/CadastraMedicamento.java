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
import controle.ProdutosDAO;
import modelo.Produtos;

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
		
		Produtos produto = new Produtos();
		
		produto.setCodigo(Integer.parseInt(req.getParameter("cod")));
		produto.setNome(req.getParameter("nome"));
		
		if (produtosDAO.getProduto(produto.getCodigo()) == null) {
			produtosDAO.salvar(produto);
		}
		else {
			produtosDAO.atualizar(produto);
		}
		
		req.getRequestDispatcher("index.jsp").forward(req, resp);
	}
}
