<%@page import="java.util.List"%>
<%@page import="modelo.Usuario"%>
<%@page import="java.util.ArrayList"%>
<%@page import="conexao.HibernateUtil"%>
<%@page import="controle.UsuarioDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<%

	if(session.getAttribute("usuarios") == null)
	{
		UsuarioDAO usuDAO = new UsuarioDAO(HibernateUtil.getSessionFactory().openSession());
		List<Usuario> usuarios = usuDAO.getUsuarios();
		pageContext.setAttribute("usuarios", usuarios);
	}
	else
	{
		pageContext.setAttribute("usuarios", session.getAttribute("usuarios"));
		session.removeAttribute("usuarios");
	}
%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1.0" />
<title>Gerencia Usuários - Mão Amiga</title>

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
				<b>Gerencia Usuários</b>
			</h1>

		</div>

	</div>

	<div class="section no-pad-bot" id="index-banner">
		<div class="container">
			<h2 class="center">Formulário de Gerenciamento de Usuários</h2>

			<form action="consultarUsuariosServlet" method="post">
				<div class="row">
					<div class="input-field col s12 m9">
						<i class="material-icons prefix">account_circle</i> <input
							type="text" id="nome" name="nome"> <label for="nome">Nome
							Usuário</label>
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
							<td><b>Nome</b></td>
							<td><b>Login</b></td>
							<td><b>Tipo</b></td>
							<td><b>Excluir Usuário</b></td>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${usuarios}" var="usuario">
							<tr>
								<td>${usuario.codigo }</td>
								<td>${usuario.nome }</td>
								<td>${usuario.login }</td>
								<td>${usuario.tipo }</td>
								<td><a href="removeUsuarioServlet?cod=${usuario.codigo}">
											<i class="material-icons right">send</i>Exluir
								</a></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>

				<br>
				<br>

				<div class="right">
					<a class="btn waves-effect waves-light" href="cadastraUsuario.jsp">
						Criar novo usuário <i class="material-icons">person_add</i>
					</a>

					<a class="btn waves-effect waves-light" href="index.jsp">
						Voltar <i class="material-icons right">cancel</i>
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
