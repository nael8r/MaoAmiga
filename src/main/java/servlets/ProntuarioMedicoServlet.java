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
import controle.ConsultaDAO;
import controle.ProdutosDAO;
import controle.ReceituarioMedicoDAO;
import modelo.Consulta;
import modelo.Produtos;
import modelo.ReceituarioMedico;

@WebServlet("/prontuarioMedico")
public class ProntuarioMedicoServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2875070341838719565L;

	private List<ReceituarioMedico> criaReceituarioMedico (List<String> nomes, List<String> medicamentos, Consulta consulta, Session sessao) {
		
		List<ReceituarioMedico> lista = new ArrayList<ReceituarioMedico>();
		ReceituarioMedicoDAO rDAO = new ReceituarioMedicoDAO(sessao);
		
		ReceituarioMedico rm;
		ProdutosDAO pDAO = new ProdutosDAO(sessao);
		int i = 0;
		
		for (String nome : nomes) {
			rm = new ReceituarioMedico();
			rm.setConsulta(consulta);
			rm.setMedicamentos(medicamentos.get(i));
			rm.setProdutos(pDAO.getProduto(Integer.parseInt(nome)));

			rDAO.salvar(rm);
			lista.add(rm);
			
			i++;
		}		
		
		return lista;
	}
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		req.getRequestDispatcher("mensagensErroServlet?mensagem=ERRO! - Comandos Inválidos!&direcao=index.jsp")
				.forward(req, resp);
	}
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		Session session = (Session) req.getSession().getAttribute("sessao");
		
		// Retoma a consulta para alteração
		ConsultaDAO consultaDao = new ConsultaDAO(session);
		
		
		if (req.getParameter("acao") != null && req.getParameter("acao").equals("cancelar")) {

			req.getRequestDispatcher("index.jsp").forward(req, resp);
			
			
		} else if (req.getParameter("acao") != null && req.getParameter("acao").equals("atestado")) {

			req.getRequestDispatcher("atestadoMedico.jsp").forward(req, resp);
			
		// Operações com a consulta
		} else if (req.getParameter("acao") != null && req.getParameter("acao").equals("receituario")) {

			Consulta consulta = (Consulta) req.getSession().getAttribute("consulta");
			
			req.getSession().setAttribute("medicamentos", consultaDao.getReceituariosMedicos(consulta.getCodigo()));
			
			req.getRequestDispatcher("receituarioMedico.jsp").forward(req, resp);
			
		// Operações com a consulta
		} else {
			
			String queixa = null, anotacoesFinais = null;
			ArrayList<String> nomesMedicamentos, descricaoMedicamentos;
			List<ReceituarioMedico> lista_receituarioMedico = null;
			char pessoal_flags = 0;
			Consulta consulta = (Consulta) req.getSession().getAttribute("consulta");
			
			try {
				
				// queixa ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
				queixa = req.getParameter("queixa");
				
				// medicamentos ~~~~~~~~~~~~~~~~~~~~~~~~~
				String[] medicamentos = req.getParameterValues("produtosGrupo");
				nomesMedicamentos = new ArrayList<String>();
				String[] descricao = req.getParameterValues("medicamentos_descricao");
				descricaoMedicamentos = new ArrayList<String>();
				ProdutosDAO pDAO = new ProdutosDAO(session);
				Produtos tmpProd = null;
				
				if (medicamentos != null && medicamentos.length > 0) {
					
					for (int i = 0; i < descricao.length; i++) {
						if (!descricao[i].isEmpty()) {
							descricaoMedicamentos.add(descricao[i]);
							nomesMedicamentos.add(medicamentos[i]);
						}
					}
					
					lista_receituarioMedico = criaReceituarioMedico(nomesMedicamentos, descricaoMedicamentos, consulta, session);
				}
				
				// pessoal ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
				String[] pessoal = req.getParameterValues("pessoal");
				
				if (pessoal != null && pessoal.length > 0) {
					for (String string : pessoal) {
						if (string.equals("alergia")) {
							pessoal_flags += 0x1;
						} else if (string.equals("febre")) {
							pessoal_flags += 0x10;
						}
					}
				}
				
				// anotacoes Finais ~~~~~~~~~~~~~~~~~~~~~
				anotacoesFinais = req.getParameter("anotacoesFinais");
				
			} catch (Exception e) {
				req.getRequestDispatcher("mensagensErroServlet?mensagem=ERRO! - Comandos Inválidos!&direcao=index.jsp")
				.forward(req, resp);
				e.printStackTrace();
			}
			
		try {
			consulta.setQueixa(queixa);
			consulta.setReceituariosMedicos(lista_receituarioMedico);
			if (lista_receituarioMedico != null && !lista_receituarioMedico.isEmpty()) {
				consulta.setUsoMedicamentos(true);
			} else {
				consulta.setUsoMedicamentos(false);
			}
			consulta.setPessoais(pessoal_flags);
			consulta.setAnotacoesFinais(anotacoesFinais);
			
			consultaDao.atualizar(consulta);

			req.getRequestDispatcher("procurarConsulta.jsp").forward(req, resp);
			
			} catch (Exception e) {
				
				ReceituarioMedicoDAO rmDAO = new ReceituarioMedicoDAO(session);
				rmDAO.excluirReceituarioDaConsulta(consulta);
				
				//req.getRequestDispatcher("mensagensErroServlet?mensagem=ERRO! - Não foi possível salvar os dados digitados no banco de dados!&direcao=index.jsp").forward(req, resp);
				e.printStackTrace();
			}
		}
		
	}
}
