<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1.0"/>
	<title>Template - Mão Amiga</title>

	<!-- CSS  -->
	<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
	<link href="css/materialize.css" type="text/css" rel="stylesheet" media="screen,projection"/>
	<link href="css/style.css" type="text/css" rel="stylesheet" media="screen,projection"/>
</head>

<body>
	
	<nav class="light-blue lighten-1" role="navigation">
		<div class="nav-wrapper container">
			<a id="logo-container" href="index.html" class="brand-logo center"><b>Ambulatório Amigo Online</b></a>
		</div>
	</nav>
	
	<div class="section no-pad-bot" id="index-banner">
		<div class="container">
			<h1 class="header center orange-text"><b>Agenda Online para o Ambulatório Amigo de Formiga</b></h1>
			<div class="row center">
				<a href="http://maoamigaformigamg.blogspot.com.br" id="download-button" class="btn-large waves-effect waves-light orange">Conheça o Blog</a>
			</div>
		</div>
	</div>

	<div class="container">
		<div class="section">
			<!--   Icon Section   -->
			<div class="row"> 
				<c:choose>
				 	<c:when test="${sessionScope.usuarioAutenticado.tipo == 'm'.charAt(0)}">		    
						<div class="col s12 m6">
							<div class="icon-block">
								<h2 class="center light-blue-text"><i class="material-icons">flash_on</i></h2>
								<h5 class="center">Ambulatório</h5>
	
								<ul class="collection with-header">
									<li class="collection-item"><a class="collection-item"> <i class="material-icons left">send</i> Agendar Consultas </a> </li>
									<li class="collection-item"><a class="collection-item"> <i class="material-icons left">send</i> Lista de Espera</a></li>
									<li class="collection-item"><a class="collection-item"> <i class="material-icons left">send</i> Agenda de Consultas</a></li>
								</ul>
							</div>
						</div>
				    
						<div class="col s12 m6">
							<div class="icon-block">
								<h2 class="center light-blue-text"><i class="material-icons">settings</i></h2>
								<h5 class="center">Consultório Médico</h5>
		
								<ul class="collection with-header">
									<li class="collection-item"><a href="listaPacientes.html" class="collection-item"> <i class="material-icons left">send</i>Lista de Pacientes</a> </li>
									<li class="collection-item"><a href="prontuarioMedico.html" class="collection-item"> <i class="material-icons left">send</i>Prontuário Médico</a> </li>
									<li class="collection-item"><a href="consultarMedicamentos.html" class="collection-item"> <i class="material-icons left">send</i>Consultar Medicamentos</a> </li>
								</ul>
							</div>
						</div>
				 	</c:when>
				 	<c:otherwise>
				    	    
						<div class="col s12 m6">
							<div class="icon-block">
								<h2 class="center light-blue-text"><i class="material-icons">flash_on</i></h2>
								<h5 class="center">Ambulatório</h5>
	
								<ul class="collection with-header">
									<li class="collection-item"><a href="agendarConsulta.html" class="collection-item"> <i class="material-icons left">send</i> Agendar Consultas </a> </li>
									<li class="collection-item"><a href="listaEspera.html" class="collection-item"> <i class="material-icons left">send</i> Lista de Espera</a></li>
									<li class="collection-item"><a href="agendaConsultas.html" class="collection-item"> <i class="material-icons left">send</i> Agenda de Consultas</a></li>
								</ul>
							</div>
						</div>
				    
						<div class="col s12 m6">
							<div class="icon-block">
								<h2 class="center light-blue-text"><i class="material-icons">settings</i></h2>
								<h5 class="center">Consultório Médico</h5>
		
								<ul class="collection with-header">
									<li class="collection-item"><a class="collection-item"> <i class="material-icons left">send</i>Lista de Pacientes</a> </li>
									<li class="collection-item"><a class="collection-item"> <i class="material-icons left">send</i>Prontuário Médico</a> </li>
									<li class="collection-item"><a class="collection-item"> <i class="material-icons left">send</i>Consultar Medicamentos</a> </li>
								</ul>
							</div>
						</div>
				 	</c:otherwise>
				</c:choose>
				
				
			</div>
		</div>

		<div class="section">

		</div>
	</div>

	<footer class="page-footer orange">
		<div class="container">
			<div class="row">
				<div class="col s12">
					<h5 class="white-text"><b>Associação Mão Amiga</b></h5>
					<p class="grey-text text-lighten-4">
						Associação no combate ao câncer em Formiga.<br />
						Rua Lassance Cunha, 39 - Centro - Formiga MG. <br/>
						 Telefone: (37) 3322-3291.
					</p>


				</div>
				<!--
				<div class="col l3 s12">
					<h5 class="white-text">Settings</h5>
					<ul>
						<li><a class="white-text" href="#!">Link 1</a></li>
						<li><a class="white-text" href="#!">Link 2</a></li>
						<li><a class="white-text" href="#!">Link 3</a></li>
						<li><a class="white-text" href="#!">Link 4</a></li>
					</ul>
				</div>
				<div class="col l3 s12">
					<h5 class="white-text">Connect</h5>
					<ul>
						<li><a class="white-text" href="#!">Link 1</a></li>
						<li><a class="white-text" href="#!">Link 2</a></li>
						<li><a class="white-text" href="#!">Link 3</a></li>
						<li><a class="white-text" href="#!">Link 4</a></li>
					</ul>
				</div>
				-->
			</div>
		</div>
		<div class="footer-copyright">
			<div class="container">
			Made by <a class="orange-text text-lighten-3" href="http://materializecss.com">Materialize</a>
			</div>
		</div>
	</footer>


	<!--  Scripts-->
	<script src="js/jquery.min.js"></script>
	<script src="js/materialize.js"></script>
	<script src="js/init.js"></script>

	</body>
</html>
