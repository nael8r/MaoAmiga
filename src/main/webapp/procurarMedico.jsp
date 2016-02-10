<%@page import="conexao.HibernateUtil"%>
<%@page import="org.hibernate.Session"%>
<%@page import="controle.MedicoDAO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="modelo.Medico"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">

<%
	List<Medico> medicos = new ArrayList<Medico>();

	if (!request.getParameter("cod").isEmpty() && request.getParameter("nome").isEmpty()) {

		MedicoDAO medicoDAO = (MedicoDAO) session.getAttribute("medicoDAO");

		Integer codigo = Integer.parseInt(request.getParameter("cod"));

		Medico medico = medicoDAO.getMedico(codigo);

		if (medico != null)
			medicos.add(medico);

	} else if (request.getParameter("cod").isEmpty() && !request.getParameter("nome").isEmpty()) {

		MedicoDAO medicoDAO = (MedicoDAO) session.getAttribute("medicoDAO");

		Medico medico = medicoDAO.getMedico(request.getParameter("nome"));

		if (medico != null)
			medicos.add(medico);

	} else {

		// Instancia os objetos para operação de cadastramento
		Session sessao = HibernateUtil.getSessionFactory().openSession();

		//Medicos medico = new Medicos();
		MedicoDAO medicoDAO = new MedicoDAO(sessao);

		// Define cada um na sessão para uso posteriori
		session.setAttribute("medicoDAO", medicoDAO);
		session.setAttribute("sessao", sessao);

		medicos = medicoDAO.getMedicos();
	}

	pageContext.setAttribute("medicos", medicos);
%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1.0" />
<title>Procurar Médico - Mão Amiga</title>

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
					Amigo Online<b></a>
		</div>
	</nav>

	<div class="section no-pad-bot" id="index-banner">
		<div class="container">
			<h1 class="header center orange-text">
				<b>Procurar Médico</b>
			</h1>

		</div>

	</div>

	<div class="section no-pad-bot" id="index-banner">
		<div class="container">
			<h2 class="center">Formulário de Procura</h2>

			<form action="procurarMedico.jsp" method="get">
				<div class="row">
					<div class="input-field col s12 m9">
						<i class="material-icons prefix">account_circle</i> <input
							type="text" id="nome" name="nome"> <label for="nome">Nome
							Médico</label>
					</div>

					<button class="btn waves-effect waves-light m3" type="submit"
						name="action">
						Procurar <i class="material-icons right">search</i>
					</button>

				</div>

				<h5>Ou</h5>
				<br>

				<div class="row">
					<div class="input-field col s12 m9">
						<i class="material-icons prefix">account_circle</i> <input
							type="text" id="codigo" name="cod"> <label for="codigo">Código</label>
					</div>

					<button class="btn waves-effect waves-light m3" type="submit"
						name="action">
						Procurar <i class="material-icons right">search</i>
					</button>

				</div>
				<br>
				<br>
				<h5>Resultado da procura:</h5>
				<br>

				<table class="bordered hoverable">
					<thead>
						<tr>
							<td><b>Código</b></td>
							<td><b>CRM</b></td>
							<td><b>Nome Completo</b></td>
							<td><b>Endereço</b></td>
							<td><b>Telefone</b></td>
							<td><b>Especialidades</b></td>
							<td><b>Selecinonar</b></td>

						</tr>
					</thead>
					<tbody>
						<c:forEach items="${medicos}" var="medico">
							<tr>
								<td>${medico.codigo}</td>
								<td>${medico.crm}</td>
								<td>${medico.nome}</td>
								<td>${medico.endereco}</td>
								<td>${medico.telefone}</td>
								<td>${medico.especialidade}</td>
								<td><a
									href="agendarConsulta?acao=agendarConsulta&cod=${medico.codigo}">
										<div>
											<i class="material-icons right">send</i>Selecionar
										</div>
								</a></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>

				<br>
				<br>
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
								<li><a href="listaMedicos"
									class="collection-item white-text"> <i
										class="material-icons">send</i>Lista de Medicos
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
								<li><a href="listaConsultas.jsp"
									class="collection-item white-text"> <i
										class="material-icons">send</i> Agenda de Consultas
								</a></li>
							</ul>
						</div>
						<div class="col l3 s12">
							<h5 class="center  white-text">Consultório Médico</h5>
							<ul>
								<li><a class="collection-item white-text"> <i
										class="material-icons">send</i>Lista de Medicos
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
