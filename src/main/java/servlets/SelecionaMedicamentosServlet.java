package servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import conexao.HibernateUtil;
import controle.ProdutosDAO;
import modelo.Produtos;

@WebServlet("/selecionaMedicamentosServlet")
public class SelecionaMedicamentosServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String[] medicamentos = req.getParameterValues("produtosGrupo");
		ArrayList<String> nomesMedicamentos = new ArrayList<String>();
		ProdutosDAO pDAO = new ProdutosDAO(HibernateUtil.getSessionFactory().openSession());
		Produtos tmpProd = null;
		
		for(String codigo : medicamentos)
		{
			tmpProd = pDAO.getProduto(Integer.parseInt(codigo));
			
			if(tmpProd != null)
				nomesMedicamentos.add(tmpProd.getNome());
		}
		
		req.getSession().setAttribute("medicamentos", nomesMedicamentos);
		req.getRequestDispatcher("receituarioMedico.jsp").forward(req, resp);
	}

}
