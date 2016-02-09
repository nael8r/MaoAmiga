<%@page import="modelo.Paciente"%>
<%@page import="modelo.Medico"%>
<%@page import="controle.MedicoDAO"%>
<%@page import="controle.PacienteDAO"%>
<%@page import="java.util.Calendar"%>
<%@page import="conexao.HibernateUtil"%>
<%@page import="org.hibernate.Session"%>
<%@page import="java.util.ArrayList"%>
<%@page import="modelo.Consulta"%>
<%@page import="java.util.List"%>
<%@page import="controle.ConsultaDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">


<%
	List<Consulta> ontem = new ArrayList<>();
	List<Consulta> hoje = new ArrayList<>();
	List<Consulta> amanha = new ArrayList<>();

	// Instancia os objetos para operação de cadastramento
	Session sessao = HibernateUtil.getSessionFactory().openSession();

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

		if (data.equals("Jan")) {
			mes = 1;
		} else if (data.equals("Feb")) {
			mes = 2;
		} else if (data.equals("Mar")) {
			mes = 3;
		} else if (data.equals("Apr")) {
			mes = 4;
		} else if (data.equals("May")) {
			mes = 5;
		} else if (data.equals("Jun")) {
			mes = 6;
		} else if (data.equals("Jul")) {
			mes = 7;
		} else if (data.equals("Aug")) {
			mes = 8;
		} else if (data.equals("Sep")) {
			mes = 9;
		} else if (data.equals("Oct")) {
			mes = 10;
		} else if (data.equals("Nov")) {
			mes = 11;
		} else if (data.equals("Dez")) {
			mes = 12;
		} else
			mes = 1;

		dt.set(ano, mes, dia);

		diaAnterior.setTimeInMillis(dt.getTimeInMillis() - 24 * 60 * 60 * 1000);
		diaPosterior.setTimeInMillis(dt.getTimeInMillis() + 24 * 60 * 60 * 1000);

		ontem = consultaDao.getConsultas(diaAnterior.getTime());
		hoje = consultaDao.getConsultas(dt.getTime());
		amanha = consultaDao.getConsultas(diaPosterior.getTime());

	} else {

		//Pacientes paciente = new Pacientes();
		ConsultaDAO cDAO = new ConsultaDAO(sessao);

		// Define cada um na sessão para uso posteriori
		session.setAttribute("cDAO", cDAO);
		session.setAttribute("sessao", sessao);
	}
	PacienteDAO pDAO = new PacienteDAO(sessao);
	MedicoDAO mDAO = new MedicoDAO(sessao);

	pageContext.setAttribute("pDAO", pDAO);
	pageContext.setAttribute("mDAO", mDAO);
	pageContext.setAttribute("ontem", ontem);
	pageContext.setAttribute("hoje", hoje);
	pageContext.setAttribute("amanha", amanha);
%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1.0" />
<title>Agenda Consultas - Mão Amiga</title>

<!-- CSS  -->
<link href="https://fonts.googleapis.com/icon?family=Material+Icons"
	rel="stylesheet">
<link href="css/materialize.css" type="text/css" rel="stylesheet"
	media="screen,projection" />
<link href="css/style.css" type="text/css" rel="stylesheet"
	media="screen,projection" />
</head>
<body>

	<nav class="light-blue lighten-1" role="navigation">
		<div class="nav-wrapper container">
			<a id="logo-container" href="index.html" class="brand-logo center"><b>Ambulatório
					Amigo Online</b></a>
		</div>
	</nav>

	<div class="section no-pad-bot" id="index-banner">
		<div class="container">
			<h1 class="header center orange-text">
				<b>Agendar Consulta</b>
			</h1>

		</div>

	</div>

	<div class="section no-pad-bot" id="index-banner">
		<div class="container">
			<h2 class="center">Formulário de Procura</h2>

			<form action="agendaConsultas.jsp">
				<div class="row">

					<div class="input-field col s12 m9">
						<i class="material-icons prefix">account_circle</i> <input
							type="date" class="datepicker" name="data">
					</div>

					<button class="btn waves-effect waves-light m3" type="submit"
						name="action">
						Procurar <i class="material-icons right">search</i>
					</button>
				</div>
			</form>


			<br> <br>
			<h5>Resultado da procura:</h5>
			<br>

			<form action="operaConsulta" method="post">
				<div class="row">
					<div class="col s12 m4">
						Um dia antes
						<table class="bordered hoverable">
							<thead>
								<tr>
									<td><b>Código</b></td>
									<td><b>Paciente</b></td>
									<td><b>Médico</b></td>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${ontem}" var="onte">
									<tr>
										<td><input class="with-gap" type="radio" name="cod"
											id="${onte.codigo }" value="${onte.codigo }" /> <label
											for="${onte.codigo }">${onte.codigo }</label></td>
										<td>${onte.paciente.nome}</td>
										<td>${onte.medico.nome }</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>

					<div class="col s12 m4">
						No dia
						<table class="bordered hoverable">
							<thead>
								<tr>
									<td><b>Código</b></td>
									<td><b>Paciente</b></td>
									<td><b>Médico</b></td>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${hoje}" var="today">
									<tr>
										<td><input class="with-gap" type="radio" name="cod"
											id="${today.codigo }" value="${today.codigo }"
											checked="checked" /> <label for="${today.codigo }">${today.codigo }</label>
										</td>
										<td>${today.paciente.nome }</td>
										<td>${today.medico.nome }</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>

					<div class="col s12 m4">
						Um dia depois
						<table class="bordered hoverable">
							<thead>
								<tr>
									<td><b>Código</b></td>
									<td><b>Paciente</b></td>
									<td><b>Médico</b></td>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${amanha}" var="manha">
									<tr>
										<td><input class="with-gap" type="radio" name="cod"
											id="${manha.codigo }" value="${manha.codigo }" /> <label
											for="${manha.codigo }">${manha.codigo }</label></td>
										<td>${manha.paciente.nome }</td>
										<td>${manha.medico.nome }</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
				</div>

				<br> <br>

				<div class="right">
					<button class="btn waves-effect waves-light" type="submit"
						name="acao" value="preConsulta">
						Pré Consulta <i class="material-icons right">assignment_ind</i>
					</button>

					<button class="btn waves-effect waves-light" type="submit"
						name="acao" value="excluir">
						Excluir <i class="material-icons right">delete</i>
					</button>

					<button class="btn waves-effect waves-light" type="submit"
						name="acao" value="cancelar">
						Cancelar <i class="material-icons right">cancel</i>
					</button>

				</div>
			</form>
		</div>
	</div>

	<br>
	<br>

	<footer class="page-footer orange">
		<div class="container">
			<div class="row">
				<div class="col l6 s12">
					<h5 class="white-text">
						<b>Associação Mão Amiga</b>
					</h5>
					<p class="grey-text text-lighten-4">
						Associação no combate ao câncer em Formiga.<br /> Rua Lassance
						Cunha, 39 - Centro - Formiga MG. <br /> Telefone: (37) 3322-3291.
					</p>
				</div>

				<c:choose>
					<c:when
						test="${sessionScope.usuarioAutenticado.tipo == 'm'.charAt(0)}">
						<div class="col l3 s12">
							<h5 class="center  white-text">Ambulatório</h5>
							<ul>
								<li><a class="collection-item white-text"> <i
										class="material-icons">send</i> Agendar Consultas
								</a></li>
								<li><a class="collection-item white-text"> <i
										class="material-icons">send</i> Lista de Espera
								</a></li>
								<li><a class="collection-item white-text"> <i
										class="material-icons">send</i> Agenda de Consultas
								</a></li>
							</ul>
						</div>
						<div class="col l3 s12">
							<h5 class="center  white-text">Consultório Médico</h5>
							<ul>
								<li><a href="listaPacientes"
									class="collection-item white-text"> <i
										class="material-icons">send</i>Lista de Pacientes
								</a></li>
								<li><a href="prontuarioMedico.jsp"
									class="collection-item white-text"> <i
										class="material-icons">send</i>Prontuário Médico
								</a></li>
								<li><a href="consultarMedicamentos.jsp"
									class="collection-item white-text"> <i
										class="material-icons">send</i>Consultar Medicamentos
								</a></li>
							</ul>
						</div>
					</c:when>

					<c:otherwise>
						<div class="col l3 s12">
							<h5 class="center  white-text">Ambulatório</h5>
							<ul>
								<li><a href="agendarConsulta.jsp"
									class="collection-item white-text"> <i
										class="material-icons">send</i> Agendar Consultas
								</a></li>
								<li><a href="listaEspera.jsp"
									class="collection-item white-text"> <i
										class="material-icons">send</i> Lista de Espera
								</a></li>
								<li><a href="agendaConsultas.jsp"
									class="collection-item white-text"> <i
										class="material-icons">send</i> Agenda de Consultas
								</a></li>
							</ul>
						</div>
						<div class="col l3 s12">
							<h5 class="center  white-text">Consultório Médico</h5>
							<ul>
								<li><a class="collection-item white-text"> <i
										class="material-icons">send</i>Lista de Pacientes
								</a></li>
								<li><a class="collection-item white-text"> <i
										class="material-icons">send</i>Prontuá¡rio Médico
								</a></li>
								<li><a class="collection-item white-text"> <i
										class="material-icons">send</i>Consultar Medicamentos
								</a></li>
							</ul>
						</div>
					</c:otherwise>
				</c:choose>
			</div>
		</div>
		<div class="footer-copyright">
			<div class="container">
				Made by <a class="orange-text text-lighten-3"
					href="http://materializecss.com">Materialize</a>
			</div>
		</div>
	</footer>


	<!--  Scripts-->
	<script src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
	<script src="js/materialize.js"></script>
	<script src="js/init.js"></script>

</body>
</html>
