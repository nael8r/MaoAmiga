package servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import conexao.HibernateUtil;
import controle.ProdutosDAO;
import modelo.Produtos;


/*
	Página de geração de lista de medicamentos
*/
@WebServlet("/selecionaMedicamentosServlet")
public class SelecionaMedicamentosServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// Recebe os medicamentos selcioados por parâmetro
		String[] medicamentos = req.getParameterValues("produtosGrupo");
		// Nova lista com os nomes dos medicamentod escolhidos
		ArrayList<String> nomesMedicamentos = new ArrayList<String>();
		// Controle de produtos
		ProdutosDAO pDAO = new ProdutosDAO(HibernateUtil.getSessionFactory().openSession());
		// Variável temporária de produtos
		Produtos tmpProd = null;
		
		// Varre o vetor de strigs procurando os campos onde não são nulos
		for(String codigo : medicamentos)
		{
			// Pega o produto do respectivo código
			tmpProd = pDAO.getProduto(Integer.parseInt(codigo));
			
			// Verifica se Este produto existe
			if(tmpProd != null)
				// Se sim, adiciona o nome do produto escolhido na lista
				nomesMedicamentos.add(tmpProd.getNome());
		}
		
		// Define a nova lista na sessão
		req.getSession().setAttribute("medicamentos", nomesMedicamentos);
		// Direciona para o receituário médico
		req.getRequestDispatcher("receituarioMedico.jsp").forward(req, resp);
	}

}
