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
	if(session.getAttribute("consultas") == null)
	{
	  //ConsultaDAO consultasDAO = new ConsultaDAO(HibernateUtil.getSessionFactory().openSession());
	  //List<Consulta> consultas = consultasDAO.getConsultas();
	  
	  MedicoDAO medicoDAO = new MedicoDAO(HibernateUtil.getSessionFactory().openSession());
	  Medico medico = (Medico)request.getSession().getAttribute("medicoAutenticado");
	  List<Consulta> consultas = medicoDAO.getConsultas(medico.getCodigo()); 
	  pageContext.setAttribute("consultas", consultas);
	}
	else
	{
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
			<a id="logo-container" href="index.html" class="brand-logo center"><b>Ambulatório Amigo Online</b></a>
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
						name="acao" value="preConsulta">
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
								<li><a href="consultarMedicamentos.jsp" class="collection-item white-text"> <i class="material-icons">send</i>Consultar Medicamentos</a> </li>
							</ul>
						</div>
				 	</c:when>
				 	
				 	<c:otherwise>
						<div class="col l3 s12">
							<h5 class="center  white-text">Ambulatório</h5>
							<ul>
								<li><a href="agendarConsulta.jsp" class="collection-item white-text"> <i class="material-icons">send</i> Agendar Consultas </a> </li>
								<li><a href="listaEspera.jsp" class="collection-item white-text"> <i class="material-icons">send</i> Lista de Espera</a></li>
								<li><a href="agendaConsultas.jsp" class="collection-item white-text"> <i class="material-icons">send</i> Agenda de Consultas</a></li>
							</ul>
						</div>
						<div class="col l3 s12">
							<h5 class="center  white-text">Consultório Médico</h5>
							<ul>
								<li><a class="collection-item white-text"> <i class="material-icons">send</i>Lista de Pacientes</a> </li>
								<li><a class="collection-item white-text"> <i class="material-icons">send</i>Prontuário Médico</a> </li>
								<li><a class="collection-item white-text"> <i class="material-icons">send</i>Consultar Medicamentos</a> </li>
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
