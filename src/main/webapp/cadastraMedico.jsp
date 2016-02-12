<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
		<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1.0"/>
		<title>Cadastra Médico - Mão Amiga</title>

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
			<h1 class="header center orange-text">
				<b>Cadastra Médico</b>
			</h1>

		</div>

	</div>

	<div class="section no-pad-bot" id="index-banner">
		<div class="container">
			<h2 class="center">Formulário de Cadastro de Médicos</h2>

			<form action="cadastraMedico" method="post">
				<div class="row">
					<div class="input-field col s12 m4">
						<i class="material-icons prefix">account_circle</i>
						<input disabled ="text" id="codigo" name="cod">
						<label for="codigo">Codigo</label>
					</div>
					<div class="input-field col s12 m8">
						<i class="material-icons prefix">account_circle</i>
						<input type="text" id="nome" name="nome" length="50">
						<label for="nome">Nome do Médico</label>
					</div>
				</div>	



				<div class="row">

					<div class="input-field col s12 m4">
						<i class="material-icons prefix">account_circle</i>
						<input id="CRM" type="text" class="validate" name="crm" length="20">
									<label for="CRM">CRM</label>
					</div>

					<div class="input-field col s12 m4">
						<i class="material-icons prefix">account_circle</i>
						<input id="endereco" type="text" class="validate" name="endereco" length="50">
									<label for="endereco">Endereço</label>
					</div>

					<div class="input-field col s12 m4">
						<i class="material-icons prefix">account_circle</i>
						<input id="telefone" type="text" class="validate" name="telefone" length="20">
									<label for="telefone">Telefone</label>
					</div>
				</div>  

				<div class="row">
					<div class="input-field col s12">
						<i class="material-icons prefix">account_circle</i>
						<textarea id="especialidades" class="materialize-textarea" name="especialidades" length="50"></textarea>
						<label for="especialidades">Especialidades</label>

					</div>

				</div>  

							<br><br>

				<div class="right">
					<button class="btn waves-effect waves-light" type="submit" name="action">Adicionar
						<i class="material-icons right">send</i>
					</button>

					<button class="btn waves-effect waves-light" type="submit" name="action" form="index.jsp">Cancelar
						<i class="material-icons right">cancel</i>
					</button>

				</div>	
			</form>
		</div>
	</div>

				<br><br>

	<footer class="page-footer orange">
		<div class="container">
			<div class="row">
				<div class="col l6 s12">
					<h5 class="white-text"><b>Associação Mão Amiga</b></h5>
					<p class="grey-text text-lighten-4">
						Associação no combate ao câncer em Formiga.<br />
						Rua Lassance Cunha, 39 - Centro - Formiga MG. <br/>
						Telefone: (37) 3322-3291.
					</p>
				</div>
				
				<c:choose>
				 	<c:when test="${sessionScope.usuarioAutenticado.tipo == 'm'.charAt(0)}">	
						<div class="col l3 s12">
							<h5 class="center  white-text">Ambulatório</h5>
							<ul>
								<li><a class="collection-item white-text"> <i class="material-icons">send</i> Agendar Consultas </a> </li>
								<li><a class="collection-item white-text"> <i class="material-icons">send</i> Lista de Espera</a></li>
								<li><a class="collection-item white-text"> <i class="material-icons">send</i> Agenda de Consultas</a></li>
							</ul>
						</div>
						<div class="col l3 s12">
							<h5 class="center  white-text">Consultório Médico</h5>
							<ul>
								<li><a href="listaPacientes" class="collection-item white-text"> <i class="material-icons">send</i>Lista de Pacientes</a> </li>
								<li><a href="prontuarioMedico.jsp" class="collection-item white-text"> <i class="material-icons">send</i>Prontuário Médico</a> </li>
								<li><a href="consultarMédicos.jsp" class="collection-item white-text"> <i class="material-icons">send</i>Consultar Médicos</a> </li>
							</ul>
						</div>
				 	</c:when>
				 	
				 	<c:otherwise>
						<div class="col l3 s12">
							<h5 class="center  white-text">Ambulatório</h5>
							<ul>
								<li><a href="agendarConsulta.jsp" class="collection-item white-text"> <i class="material-icons">send</i> Agendar Consultas </a> </li>
								<li><a href="listaEspera.jsp" class="collection-item white-text"> <i class="material-icons">send</i> Lista de Espera</a></li>
								<li><a href="listaConsultas.jsp" class="collection-item white-text"> <i class="material-icons">send</i> Agenda de Consultas</a></li>
							</ul>
						</div>
						<div class="col l3 s12">
							<h5 class="center  white-text">Consultório Médico</h5>
							<ul>
								<li><a class="collection-item white-text"> <i class="material-icons">send</i>Lista de Pacientes</a> </li>
								<li><a class="collection-item white-text"> <i class="material-icons">send</i>Prontuário Médico</a> </li>
								<li><a class="collection-item white-text"> <i class="material-icons">send</i>Consultar Médicos</a> </li>
							</ul>
						</div>
				 	</c:otherwise>
				</c:choose>
			</div>
		</div>
		<div class="footer-copyright">
			<div class="container">
			Made by <a class="orange-text text-lighten-3" href="http://materializecss.com">Materialize</a>
			</div>
		</div>
	</footer>


	<!--  Scripts-->
	<script src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
	<script src="js/materialize.js"></script>
	<script src="js/init.js"></script>

	</body>
</html>
