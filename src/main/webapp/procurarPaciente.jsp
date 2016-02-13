<!--
	Página de procura de Pacientes cadastrados
-->

<%@page import="conexao.HibernateUtil"%>
<%@page import="org.hibernate.Session"%>
<%@page import="controle.PacienteDAO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="modelo.Paciente"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">

<%
	// Cria uma lista vazia para armazenar os novos pacientes a serem exibidos
	List<Paciente> pacientes = new ArrayList<Paciente>();

	// Verifica que tipo de busca está sendo feita
		// Por codigo
	if (!request.getParameter("cod").isEmpty() && request.getParameter("nome").isEmpty()) {

		// Instancia o controle
		PacienteDAO pacienteDAO = (PacienteDAO) session.getAttribute("pacienteDAO");

		// Recolhe o código 
		Integer codigo = Integer.parseInt(request.getParameter("cod"));

		// Pega o paciente e adiciona na lista
		Paciente paciente = pacienteDAO.getPaciente(codigo);

		if (paciente != null)
			pacientes.add(paciente);
		
		// Por nome
	} else if (request.getParameter("cod").isEmpty() && !request.getParameter("nome").isEmpty()) {

		// Instancia o controle
		PacienteDAO pacienteDAO = (PacienteDAO) session.getAttribute("pacienteDAO");

		// Pega todos os pacientes que possuem 'nome' no nome
		pacientes = pacienteDAO.getPacientes(request.getParameter("nome"));

	} else {

		// Caso nenhuma das opções, pega todos os pacientes

		// Instancia os objetos para operação de cadastramento
		Session sessao = HibernateUtil.getSessionFactory().openSession();

		PacienteDAO pacienteDAO = new PacienteDAO(sessao);

		// Define cada um na sessão para uso posteriori
		session.setAttribute("pacienteDAO", pacienteDAO);
		session.setAttribute("sessao", sessao);

		pacientes = pacienteDAO.getPacientes();
	}

	// Define uma cópia do escopo para a página
	pageContext.setAttribute("pacientes", pacientes);
%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1.0" />
<title>Procurar Paciente - Mão Amiga</title>

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
				<b>Procurar Paciente</b>
			</h1>

		</div>

	</div>

	<div class="section no-pad-bot" id="index-banner">
		<div class="container">
			<h2 class="center">Formulário de Procura</h2>

			<form action="procurarPaciente.jsp" method="get">
				<div class="row">
					<div class="input-field col s12 m9">
						<i class="material-icons prefix">account_circle</i> <input
							type="text" id="nome" name="nome"> <label for="nome">Nome
							Paciente</label>
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
							<td><b>Nome Completo</b></td>
							<td><b>CPF</b></td>
							<td><b>RG</b></td>
							<td><b>Selecionar</b></td>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${pacientes}" var="paciente">
							<tr>
								<td>${paciente.codigo}</td>
								<td>${paciente.nome}</td>
								<td>${paciente.cpf}</td>
								<td>${paciente.rg}</td>
								<td>
									<a href="agendarConsulta?acao=procurarMedico&cod=${paciente.codigo}">
											<i class="material-icons right">send</i>Selecionar
									</a>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>

				<br>
				<br>

				<!-- 
				<div class="right">
					<button class="btn waves-effect waves-light" type="submit" name="action">Ok
						<i class="material-icons right">send</i>
					</button>

					<button class="btn waves-effect waves-light" type="submit" name="action">Voltar
						<i class="material-icons right">cancel</i>
					</button>
				</div>  -->
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
