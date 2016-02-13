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
	// Listas a serem atribuidas no escopo da página
	pageContext.setAttribute("ontem", request.getAttribute("ontem"));
	pageContext.setAttribute("hoje", request.getAttribute("hoje"));
	pageContext.setAttribute("amanha", request.getAttribute("amanha"));
	request.removeAttribute("ontem");
	request.removeAttribute("hoje");
	request.removeAttribute("amanha");
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
			<a id="logo-container" href="index.jsp" class="brand-logo center"><b>Ambulatório
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

			<form action="agendaConsultasServlet" method="post">
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
						name="acao" value="imprimir">
						Imprimir <i class="material-icons right">print</i>
					</button>

					<button class="btn waves-effect waves-light" type="submit"
						name="acao" value="excluir">
						Excluir <i class="material-icons right">delete</i>
					</button>

					<a class="btn waves-effect waves-light" href="index.jsp">
						Cancelar <i class="material-icons right">cancel</i>
					</a>
				</div>
			</form>
		</div>
	</div>

	<br>
	<br>

	<footer class="page-footer orange">
		<div class="container">
			<div class="row">
				<div class="col s12 m6">
					<h5 class="white-text">
						<a href="index.jsp" class="white-text"><b>Associação Mão Amiga</b></a>
					</h5>
					<p class="grey-text text-lighten-4">
						Associação no combate ao câncer em Formiga.<br /> Rua Lassance
						Cunha, 39 - Centro - Formiga MG. <br /> Telefone: (37) 3322-3291.
					</p>


				</div>
				
				<div class="col s12 m6">
					<div align="right">
						
						<a class="white-text" href="index.jsp"><b>Home</b></a><br/><br/>
						<a class="white-text" href="login.jsp"><b>Logout</b></a><br/>
					</div>
				</div>
				
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
