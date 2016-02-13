<!--
	Página de login
-->


<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<%
	// Toda vez que for acessada, invalidará a sessão a fim de forçar uma nova autenticacao
	session.invalidate();
%>
<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
		<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1.0"/>
		<title>Login - Mão Amiga</title>

		<!-- CSS  -->
		<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
		<link href="css/materialize.css" type="text/css" rel="stylesheet" media="screen,projection"/>
		<link href="css/style.css" type="text/css" rel="stylesheet" media="screen,projection"/>
</head>
<body>
	
	<nav class="light-blue lighten-1" role="navigation">
		<div class="nav-wrapper container">
			<a id="logo-container" href="login.jsp" class="brand-logo center"><b>Ambulatório Amigo Online</b></a>
		</div>
	</nav>
	
	<div class="section no-pad-bot" id="index-banner">
		<div class="container">
			<h1 class="header center orange-text">
				<b>Login</b>
			</h1>

		</div>

	</div>

	<div class="section no-pad-bot" id="index-banner">
		<div class="container">
			<h2 class="center s6">Formulário de Acesso</h2>

			<form action="AutenticaLogin" method="post">
				<div class="row s6">
					<div class="input-field col s6">
						<i class="material-icons prefix">account_circle</i>
						<input id="login" type="text" class="validate" name="login" tabindex="1">
						<label for="codigo">Login</label>
					</div>
					<div class="input-field col s6">
						<i class="material-icons prefix">account_circle</i>
						<input id="senha" type="password" class="validate" name="senha" tabindex="2">
						<label for="senha">Senha</label>
					</div>
				</div>	
							<br><br>

				<div class="right">
					<button class="btn waves-effect waves-light" type="submit">Login
						<i class="material-icons">fingerprint</i>
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
			</div>
		</div>
		<div class="footer-copyright">
			<div class="container">
			Made by <a class="orange-text text-lighten-3" href="http://materializecss.com">Materialize</a>
			</div>
		</div>
	</footer>


	<!--  Scripts-->
	<!-- <script src="https://code.jquery.com/jquery-2.1.1.min.js"></script> -->

	<script src="js/jquery.min.js"></script>
	<script src="js/materialize.js"></script>
	<script src="js/init.js"></script>

	</body>
</html>
