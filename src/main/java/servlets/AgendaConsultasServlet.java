package servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import conexao.HibernateUtil;
import controle.ConsultaDAO;
import modelo.Consulta;

/*
	Procedimento de busca de consultas por meio de datas.
	Escolhido uma data, cria-se três listas.
		Lista de consultas da data selecionada - (ontem)
		Lista de consultas da data anterior à selecionada - (hoje)
		Lista de consultas da data posterior selecionada - (amanha)
*/

@WebServlet("/agendaConsultasServlet")
public class AgendaConsultasServlet extends HttpServlet {

	private static final long serialVersionUID = -2945681250702720282L;
		
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Cria as 3 listas que serão preenchidas com consults
		List<Consulta> ontem = new ArrayList<Consulta>();
		List<Consulta> hoje = new ArrayList<Consulta>();
		List<Consulta> amanha = new ArrayList<Consulta>();

		try {
			// Verifica se o parametro data foi preenchido corretamente
			if (!request.getParameter("data").isEmpty()) {

				// Realiza a leitura deste e conversão para a consulta no banco de dados
				Calendar dt = Calendar.getInstance();
				Calendar diaAnterior = Calendar.getInstance();
				Calendar diaPosterior = Calendar.getInstance();
				int dia, mes, ano;
				int tamStringData;

				// Retoma a consulta para alteração
				ConsultaDAO consultaDao = new ConsultaDAO(HibernateUtil.getSessionFactory().openSession());

				String data = request.getParameter("data");

				// data - recebe o dia ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
				String diaString = null;

				if (data.charAt(1) == ' ') {

					diaString = data.substring(0, 1);

					data = "x".concat(data);

				} else {
					diaString = data.substring(0, 2);
				}

				dia = Integer.parseInt(diaString);

				// ano - recebe o ano ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
				tamStringData = data.length();

				ano = Integer.parseInt(data.substring(tamStringData - 4, tamStringData));

				// Mes - recebe o Mes ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
				data = data.substring(3, 6);

				mes = util.DateUtils.getMes(data) + 1;

				dt.set(ano, mes, dia);

				// Obtem a data anterior e posterior subtraindo/adicionando 1 (um) dia em milissegundos (86.400.000 milissegundos)
				diaAnterior.setTimeInMillis(dt.getTimeInMillis() - 24 * 60 * 60 * 1000);
				diaPosterior.setTimeInMillis(dt.getTimeInMillis() + 24 * 60 * 60 * 1000);

				// Recebe as consultas referentes a cada dia
				ontem = consultaDao.getConsultas(diaAnterior.getTime());
				hoje = consultaDao.getConsultas(dt.getTime());
				amanha = consultaDao.getConsultas(diaPosterior.getTime());

			}
		} catch (NullPointerException e) {
			e.printStackTrace();
		}
		finally {
		}
		
		// acopla cada uma na sessão
		request.getSession().setAttribute("ontem", ontem);
		request.getSession().setAttribute("hoje", hoje);
		request.getSession().setAttribute("amanha", amanha);
		
		// redireciona para a página de exibição
		request.getRequestDispatcher("listaConsultas.jsp").forward(request, response);;
	}

}
