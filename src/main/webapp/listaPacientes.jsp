<!--
	Página de listagem de pacientes de acordo com o médico
-->

<%@page import="modelo.Medico"%>
<%@page import="modelo.Paciente"%>
<%@page import="java.util.List"%>
<%@page import="modelo.Usuario"%>
<%@page import="controle.MedicoDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">

<%
	// Cria nova lista de pacientes do respectivo médico responsavel
	MedicoDAO mdcDAO = (MedicoDAO) session.getAttribute("mdcDAO");
	Usuario usuarioAutenticado = (Usuario)session.getAttribute("usuarioAutenticado");
	Medico m = mdcDAO.getMedico(usuarioAutenticado.getNome());
	List<Paciente> pacientes = mdcDAO.getPacientes(m.getCodigo());
	
	pageContext.setAttribute("pacientes", pacientes);
%>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1.0" />
<title>Lista Pacientes - Mão Amiga</title>

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
				<b>Lista Pacientes</b>
			</h1>

		</div>

	</div>

	<div class="section no-pad-bot" id="index-banner">
		<div class="container">
			<h5>Pacientes:</h5>
			<br>
				
			<table class="bordered hoverable">
				<thead>
					<tr>
						<td><b>Código</b></td>
						<td><b>Nome</b></td>
						<td><b>CPF</b></td>
						<td><b>RG</b></td>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${pacientes}" var="paciente" >
						<tr>
							<td>${paciente.codigo }</td>
							<td>${paciente.nome }</td>
							<td>${paciente.cpf }</td>
							<td>${paciente.rg }</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>

			<br> <br>

			<div class="right">
				<a class="btn waves-effect waves-light" href="index.jsp">
					Voltar <i class="material-icons right">keyboard_backspace</i>
				</a>

			</div>
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
