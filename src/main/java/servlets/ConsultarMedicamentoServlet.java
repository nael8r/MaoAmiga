package servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import conexao.HibernateUtil;
import controle.ProdutosDAO;
import modelo.Produtos;

@WebServlet("/consultarMedicamentoServlet")
public class ConsultarMedicamentoServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<Produtos> produtos = new ArrayList<Produtos>();
		ProdutosDAO produtosDAO = new ProdutosDAO(HibernateUtil.getSessionFactory().openSession());
		
		if (req.getParameter("cod") != null && !req.getParameter("cod").isEmpty()) {
			
			Integer codigo = Integer.parseInt(req.getParameter("cod"));
			
			Produtos produto = produtosDAO.getProduto(codigo);
			
			if (produto != null)
				produtos.add(produto);
		}
		else if (req.getParameter("nome") != null && !req.getParameter("nome").isEmpty()) {
			
			produtos = produtosDAO.getProdutos(req.getParameter("nome"));
			
		}
		else {
			
			produtos = produtosDAO.getProdutos();
		}
		
		produtosDAO.getSessao().close();

		req.getSession().setAttribute("produtos", produtos);
		
		req.getRequestDispatcher("procurarMedicamento.jsp").forward(req, resp);
	}

}
