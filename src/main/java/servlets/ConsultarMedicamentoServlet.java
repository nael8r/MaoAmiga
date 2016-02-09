package servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import conexao.HibernateUtil;
import controle.ProdutosDAO;
import modelo.Produtos;

public class ConsultarMedicamentoServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<Produtos> produtos = new ArrayList<Produtos>();

		ProdutosDAO produtosDAO = new ProdutosDAO(HibernateUtil.getSessionFactory().openSession());
		
		if (!req.getParameter("cod").isEmpty() && req.getParameter("nome").isEmpty()) {
			
			Integer codigo = Integer.parseInt(req.getParameter("cod"));
			
			Produtos produto = produtosDAO.getProduto(codigo);
			
			if (produto != null)
				produtos.add(produto);
		}
		else if (req.getParameter("cod").isEmpty() && !req.getParameter("nome").isEmpty()) {
			
			Produtos produto = produtosDAO.getProduto(req.getParameter("nome"));

			if (produto != null)
				produtos.add(produto);
			
		}
		else {
			
			produtos = produtosDAO.getProdutos();
		}

		req.getSession().setAttribute("produtos", produtos);
		
		req.getRequestDispatcher("consultarMedicamentos.jsp").forward(req, resp);
	}

}
