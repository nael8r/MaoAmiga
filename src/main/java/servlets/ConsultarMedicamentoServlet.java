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

/*
	Servlet responsável por realizar a lista de exibição dos medicamentos de acordo
		com o código ou pelo nome
*/

@WebServlet("/consultarMedicamentoServlet")
public class ConsultarMedicamentoServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// Nova lista a ser preenchida
		List<Produtos> produtos = new ArrayList<Produtos>();
		ProdutosDAO produtosDAO = new ProdutosDAO(HibernateUtil.getSessionFactory().openSession());
		
		// Verifica se a procura é por
			// código do produto
		if (req.getParameter("cod") != null && !req.getParameter("cod").isEmpty()) {
			
			Integer codigo = Integer.parseInt(req.getParameter("cod"));
			
			Produtos produto = produtosDAO.getProduto(codigo);
			
			if (produto != null)
				produtos.add(produto);
		}

			// nome do produto
		else if (req.getParameter("nome") != null && !req.getParameter("nome").isEmpty()) {
			
			produtos = produtosDAO.getProdutos(req.getParameter("nome"));
			
		}
		else {
			
			// ou exibe todos
			produtos = produtosDAO.getProdutos();
		}
		
		produtosDAO.getSessao().close();

		// define o resultado na sessão
		req.getSession().setAttribute("produtos", produtos);
		
		req.getRequestDispatcher("procurarMedicamento.jsp").forward(req, resp);
	}

}
