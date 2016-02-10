<%@page import="conexao.HibernateUtil"%>
<%@page import="controle.ProdutosDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<%
	ProdutosDAO pDAO = new ProdutosDAO(HibernateUtil.getSessionFactory().openSession());
	pageContext.setAttribute("produtos", pDAO.getProdutos());
	pDAO.getSessao().close();
%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1.0" />
<title>Seleção de Medicamentos</title>

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
			<h2 class="header center orange-text">
				<b>Selecione os medicamentos para incluir no Receituário Médico</b>
			</h2>
		</div>
	</div>

	<div class="section no-pad-bot" id="index-banner">
		<div class="container">

			<form action="selecionaMedicamentosServlet" method="post">
				<table class="bordered hoverable">
					<thead>
						<tr>
							<td><b>Código</b></td>
							<td><b>Nome do Medicamento</b></td>
							<td>Selecione</td>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${produtos}" var="produto">
							<tr>
								<td>
									<input type="checkbox" name="produtosGrupo" value="${produto.codigo}" id="${produto.codigo}">
									<label for="${produto.codigo}">${produto.codigo}</label>
								</td>
								<td>${produto.nome}</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				<br><br>
				<div class="right">
					<button class="btn waves-effect waves-light" type="submit" name="action">Ok
						<i class="material-icons right">send</i>
					</button>
				</div> 
			</form>
		</div>
	</div>

</body>
</html>
