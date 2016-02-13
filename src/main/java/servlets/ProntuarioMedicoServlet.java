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

import controle.ConsultaDAO;
import controle.ProdutosDAO;
import controle.ReceituarioMedicoDAO;
import modelo.Consulta;
import modelo.ReceituarioMedico;

/*
	Servlet responsável por gerenciar os comandos e dados do prontuário médico incluindo o receituário e atestado medicos
*/
@WebServlet("/prontuarioMedico")
public class ProntuarioMedicoServlet extends HttpServlet {

	private static final long serialVersionUID = 2875070341838719565L;

	// Método de criação de um novo receituário médico
		// Este procedimento recebe os nomes dos medicamentos selecionados pelo médico
		// Para que possa ser criado um novos receituários médicos
	private List<ReceituarioMedico> criaReceituarioMedico (List<String> nomes, List<String> medicamentos, Consulta consulta, Session sessao) {
		// Lista dos novos Receituários
		List<ReceituarioMedico> lista = new ArrayList<ReceituarioMedico>();
		// Controle de Receituário
		ReceituarioMedicoDAO rDAO = new ReceituarioMedicoDAO(sessao);
		// Receituário temporário
		ReceituarioMedico rm;
		// Controle Produto 
		ProdutosDAO pDAO = new ProdutosDAO(sessao);
		int i = 0;
		
		// Para cada nome encontrado do medicamento, adiciona os dados na lista que será retornada
		for (String nome : nomes) {
			// Crianvo receituário
			rm = new ReceituarioMedico();
			// Adiciona os respectivos dados
			rm.setConsulta(consulta);
			rm.setMedicamentos(medicamentos.get(i));
			rm.setProdutos(pDAO.getProduto(Integer.parseInt(nome)));

			// Salva no banco dade dados
			rDAO.salvar(rm);
			// Adiciona-o numa lista
			lista.add(rm);
			
			i++;
		}		
		
		// Retorna a lista
		return lista;
	}
	
	
	// Caso haja uma solicitação get, redireciona para a saida de erro
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		req.getRequestDispatcher("mensagensErroServlet?mensagem=ERRO! - Comandos Inválidos!&direcao=index.jsp")
				.forward(req, resp);
	}
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// Recebe a sessão atual da conexão
		Session session = (Session) req.getSession().getAttribute("sessao");
		
		// Retoma a consulta para alteração dos dados no banco de dados quando necessário
		ConsultaDAO consultaDao = new ConsultaDAO(session);
		
		
		// Verifica se a ação a ser feita
			// Cancelar e votlar para o índice
		if (req.getParameter("acao") != null && req.getParameter("acao").equals("cancelar")) {

			req.getRequestDispatcher("index.jsp").forward(req, resp);
			
			
			// Gerar o atestado
		} else if (req.getParameter("acao") != null && req.getParameter("acao").equals("atestado")) {

			req.getRequestDispatcher("atestadoMedico.jsp").forward(req, resp);
		

			// Gerar o receituário de acordo com a consulta
		} else if (req.getParameter("acao") != null && req.getParameter("acao").equals("receituario")) {

			Consulta consulta = (Consulta) req.getSession().getAttribute("consulta");
			
			req.getSession().setAttribute("medicamentos", consultaDao.getReceituariosMedicos(consulta.getCodigo()));
			
			req.getRequestDispatcher("receituarioMedico.jsp").forward(req, resp);
			
		
		// Caso queira realizar operações de atualização da consulta
		} else {
			
			// Recebe  os parâmetros a serem adicionados
			String queixa = null, anotacoesFinais = null;
			ArrayList<String> nomesMedicamentos, descricaoMedicamentos;
			List<ReceituarioMedico> lista_receituarioMedico = null;
			char pessoal_flags = 0;

			// Recebe a consulta atual
			Consulta consulta = (Consulta) req.getSession().getAttribute("consulta");
			
			try {
				
				// Recebe: queixa ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
				queixa = req.getParameter("queixa");
				

				// Recebe: medicamentos ~~~~~~~~~~~~~~~~~~~~~~~~~
				String[] medicamentos = req.getParameterValues("produtosGrupo");
				nomesMedicamentos = new ArrayList<String>();
				String[] descricao = req.getParameterValues("medicamentos_descricao");
				descricaoMedicamentos = new ArrayList<String>();
				
				// caso existam, cria listas dos valores
				if (medicamentos != null && medicamentos.length > 0) {
					
					for (int i = 0; i < descricao.length; i++) {
						if (!descricao[i].isEmpty()) {
							descricaoMedicamentos.add(descricao[i]);
							nomesMedicamentos.add(medicamentos[i]);
						}
					}
					
					// Cria a lista de receituário médico 
					lista_receituarioMedico = criaReceituarioMedico(nomesMedicamentos, descricaoMedicamentos, consulta, session);
				}

				
				// Recebe: pessoal ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
				String[] pessoal = req.getParameterValues("pessoal");
				
				// Utiliza o conceito de máscara de bits para a manipulação dos resultados
				if (pessoal != null && pessoal.length > 0) {
					for (String string : pessoal) {
						if (string.equals("alergia")) {
							pessoal_flags += 0x1;
						} else if (string.equals("febre")) {
							pessoal_flags += 0x10;
						}
					}
				}
				

				// Recebe: anotacoes Finais ~~~~~~~~~~~~~~~~~~~~~
				anotacoesFinais = req.getParameter("anotacoesFinais");
				
			} catch (Exception e) {
				req.getRequestDispatcher("mensagensErroServlet?mensagem=ERRO! - Comandos Inválidos!&direcao=index.jsp")
				.forward(req, resp);
				e.printStackTrace();
			}
			
		try {
			// Recebido os dados, realiza-se a atualização.

			// Atualiza os novos valores na consulta
			consulta.setQueixa(queixa);
			consulta.setReceituariosMedicos(lista_receituarioMedico);
			if (lista_receituarioMedico != null && !lista_receituarioMedico.isEmpty()) {
				consulta.setUsoMedicamentos(true);
			} else {
				consulta.setUsoMedicamentos(false);
			}
			consulta.setPessoais(pessoal_flags);
			consulta.setAnotacoesFinais(anotacoesFinais);
			
			// Ao final, atualiza o banco de dados
			consultaDao.atualizar(consulta);

			// Redireciona para a procura de consultas
			req.getRequestDispatcher("procurarConsulta.jsp").forward(req, resp);
			
			} catch (Exception e) {
				
				// Caso tenha acontecido algum erro, realiza o procedimento de exclusão dos receituários criados
					// já que esses são dependentes do sucesso de todo o processo
				ReceituarioMedicoDAO rmDAO = new ReceituarioMedicoDAO(session);
				rmDAO.excluirReceituarioDaConsulta(consulta);
				
				//req.getRequestDispatcher("mensagensErroServlet?mensagem=ERRO! - Não foi possível salvar os dados digitados no banco de dados!&direcao=index.jsp").forward(req, resp);
				e.printStackTrace();
			}
		}
		
	}
}
