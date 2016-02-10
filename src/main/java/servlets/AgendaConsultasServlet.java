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

import org.hibernate.Session;

import conexao.HibernateUtil;
import controle.ConsultaDAO;
import modelo.Consulta;

@WebServlet("/agendaConsultasServlet")
public class AgendaConsultasServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2945681250702720282L;
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Consulta> ontem = new ArrayList<Consulta>();
		List<Consulta> hoje = new ArrayList<Consulta>();
		List<Consulta> amanha = new ArrayList<Consulta>();

		// Instancia os objetos para operação de cadastramento
		Session sessao = HibernateUtil.getSessionFactory().openSession();

		try {
			if (!request.getParameter("data").isEmpty()) {

				Calendar dt = Calendar.getInstance();
				Calendar diaAnterior = Calendar.getInstance();
				Calendar diaPosterior = Calendar.getInstance();
				int dia, mes, ano;
				int tamStringData;

				// Retoma a consulta para alteração
				ConsultaDAO consultaDao = new ConsultaDAO(sessao);

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

				mes = util.DateUtils.getMes(data);

				dt.set(ano, mes, dia);

				diaAnterior.setTimeInMillis(dt.getTimeInMillis() - 24 * 60 * 60 * 1000);
				diaPosterior.setTimeInMillis(dt.getTimeInMillis() + 24 * 60 * 60 * 1000);

				ontem = consultaDao.getConsultas(diaAnterior.getTime());
				hoje = consultaDao.getConsultas(dt.getTime());
				amanha = consultaDao.getConsultas(diaPosterior.getTime());

			}
		} catch (NullPointerException e) {
			e.printStackTrace();
		}
		finally {
			sessao.close();
		}
		
		request.getSession().setAttribute("ontem", ontem);
		request.getSession().setAttribute("hoje", hoje);
		request.getSession().setAttribute("amanha", amanha);
		
		request.getRequestDispatcher("listaConsultas.jsp").forward(request, response);;
	}

}
