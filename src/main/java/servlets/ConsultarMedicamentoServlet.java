package servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		ProdutosDAO produtosDAO = new ProdutosDAO(sessao);

		// Verifica se o comando de ação foi realmente preenhido
		if (req.getParameter("acao") != null && !req.getParameter("acao").isEmpty()) {

			// Se for o ato de atualizar as quantidades dos cam
			if (req.getParameter("acao").equals("atualiza")) {
				
				String[] quantidades_String = null;

				
				quantidades_String = req.getParameterValues("quantidades");
				
				if (quantidades_String.length > 0) {
					
					for (String quantidade : quantidades_String) {
	
						if (Integer.parseInt(quantidade) < 0) {

							sessao.close();
							
							req.getRequestDispatcher(
									"mensagensErroServlet?mensagem=ERRO! - Valores incorretos!&direcao=procurarMedicamento.jsp")
							.forward(req, resp);
							
							return ;
						}
					}
					
					
					produtos = produtosDAO.getProdutos();
	
					int i = 0;
					for (String quantidade : quantidades_String) {
						produtos.get(i).setQuantidade(Integer.parseInt(quantidade));
						produtosDAO.atualizar(produtos.get(i));
	
						i++;
					}
				}
				
				
				req.getRequestDispatcher("procurarMedicamento.jsp").forward(req, resp);
					
			} else if (req.getParameter("acao").equals("procura")) {
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
	
				} else {
	
					// ou exibe todos
					produtos = produtosDAO.getProdutos();
				}
	
				produtosDAO.getSessao().close();
	
				// define o resultado na sessão
				req.getSession().setAttribute("produtos", produtos);
	
				req.getRequestDispatcher("procurarMedicamento.jsp").forward(req, resp);
			}
			
			
		} else if (req.getParameter("excluir") != null && !req.getParameter("excluir").isEmpty()) {
			
			int codigo = Integer.parseInt(req.getParameter("excluir"));
			
			produtosDAO.excluir(produtosDAO.getProduto(codigo));
			
			req.getRequestDispatcher("procurarMedicamento.jsp").forward(req, resp);
		}
	} 

}
