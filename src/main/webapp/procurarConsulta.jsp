<!--
	Página de procura de consultas
-->

<%@page import="modelo.Medico"%>
<%@page import="controle.MedicoDAO"%>
<%@page import="modelo.Consulta"%>
<%@page import="controle.ConsultaDAO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="modelo.Produtos"%>
<%@page import="java.util.List"%>
<%@page import="controle.ProdutosDAO"%>
<%@page import="conexao.HibernateUtil"%>
<%@page import="org.hibernate.Session"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<%
	// Verifica se as consultas já foram definidas em escopo de sessao
	if(session.getAttribute("consultas") == null)
	{
		// Se não, define novo escopo com todas as consultas
	  MedicoDAO medicoDAO = new MedicoDAO(HibernateUtil.getSessionFactory().openSession());
	  Medico medico = (Medico)request.getSession().getAttribute("medicoAutenticado");
	  List<Consulta> consultas = medicoDAO.getConsultas(medico.getCodigo()); 
	  pageContext.setAttribute("consultas", consultas);
	}
	else
	{
		// Caso já, utiliza-as
	  pageContext.setAttribute("consultas", session.getAttribute("consultas"));
	  session.removeAttribute("consultas");
	}
%>
<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
		<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1.0"/>
		<title>Procurar Consultas - Mão Amiga</title>

		<!-- CSS  -->
		<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
		<link href="css/materialize.css" type="text/css" rel="stylesheet" media="screen,projection"/>
		<link href="css/style.css" type="text/css" rel="stylesheet" media="screen,projection"/>
</head>
<body>
	
	<nav class="light-blue lighten-1" role="navigation">
		<div class="nav-wrapper container">
			<a id="logo-container" href="index.jsp" class="brand-logo center"><b>Ambulatório Amigo Online</b></a>
		</div>
	</nav>
	
	<div class="section no-pad-bot" id="index-banner">
		<div class="container">
			<h1 class="header center orange-text">
				<b>Procurar Consultas</b>
			</h1>

		</div>

	</div>



	<div class="section no-pad-bot" id="index-banner">
		<div class="container">
			<h2 class="center">Formulário de Procura</h2>

			<form action="procurarConsultaServlet" method="post">
				<div class="row">
					<div class="input-field col s12 m9">
						<i class="material-icons prefix">account_circle</i>
						<input type="text" id="paciente" name="nome">
						<label for="paciene">Nome Paciente</label>
					</div>
					
					<button class="btn waves-effect waves-light m3" type="submit" name="acao" value="procurar">Procurar
						<i class="material-icons right">search</i>
					</button>

				</div>  

				<h5>Ou</h5>
				<br>

				<div class="row">
					<div class="input-field col s12 m9">
						<i class="material-icons prefix">account_circle</i>
						<input type="text" id="codigo" name="cod">
						<label for="codigo">Código</label>
					</div>
					
					<button class="btn waves-effect waves-light m3" type="submit" name="acao" value="procurar">Procurar
						<i class="material-icons right">search</i>
					</button>

				</div>  

				<h5>Ou</h5>
				<br>
				
				<div class="row">

					<div class="input-field col s12 m9">
						<i class="material-icons prefix">account_circle</i> <input
							type="date" class="datepicker" name="data" value="data">
					</div>

					<button class="btn waves-effect waves-light m3" type="submit"
						name="acao" value="procurar">
						Procurar <i class="material-icons right">search</i>
					</button>
				</div>
				<br><br>
				<h5>Resultado da procura:</h5>
				<br>

				<table class="bordered hoverable">
					<thead>
						<tr>
							<td><b>Código</b></td>
							<td><b>Nome Paciente</b></td>
							<td><b>Tipo Consulta</b></td>
							<td><b>Data</b></td>
							<td><b>Hora</b></td>
							<td><b>Idade</b></td>
							<td><b>Sexo</b></td>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${consultas}" var="consulta" >
							<tr>
								<td>
									<input type="radio" id="${consulta.codigo}" name="codigo_selecionado" value="${consulta.codigo}" class="with-gap" />
									<label for="${consulta.codigo}">${consulta.codigo}</label>
								</td>
								<td>${consulta.paciente.nome}</td>
								<td>
									<c:choose>
									    <c:when test="${consulta.tipoConsulta eq '1'.charAt(0)}">
									    	Consulta em Geral
									    </c:when>
									    <c:when test="${consulta.tipoConsulta eq '2'.charAt(0)}">
									    	Curativo
									    </c:when>
									    <c:when test="${consulta.tipoConsulta eq '3'.charAt(0)}">
									    	Entrega de Examos
									    </c:when>
									    <c:otherwise>
									    	Não especificado pela secretária
									    </c:otherwise>
									</c:choose>
								</td>
								<td>${consulta.data}</td>	
								<td>${consulta.hora}</td>
								<td>${consulta.idade}</td>
								<td>${consulta.sexo}</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>

							<br><br>

				<div class="right">				
					<button class="btn waves-effect waves-light" type="submit"
						name="acao" value="prontuarioMedico">
						Prontuário Médico <i class="material-icons right">assignment_ind</i>
					</button>
					
					<button class="btn waves-effect waves-light" type="submit"
						name="acao" value="imprimir">
						Imprimir <i class="material-icons right">print</i>
					</button>

					<button class="btn waves-effect waves-light" type="submit"
						name="acao" value="cancelar">
						Cancelar <i class="material-icons right">cancel</i>
					</button>
				</div>
			</form>
		</div>
	</div>

				<br><br>

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
