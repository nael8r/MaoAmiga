<%@page import="modelo.ReceituarioExames"%>
<%@page import="controle.ReceituarioExamesDAO"%>
<%@page import="modelo.Paciente"%>
<%@page import="modelo.Medico"%>
<%@page import="controle.MedicoDAO"%>
<%@page import="controle.PacienteDAO"%>
<%@page import="java.util.Calendar"%>
<%@page import="conexao.HibernateUtil"%>
<%@page import="org.hibernate.Session"%>
<%@page import="java.util.ArrayList"%>
<%@page import="modelo.Consulta"%>
<%@page import="java.util.List"%>
<%@page import="controle.ConsultaDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<%

	List<ReceituarioExames> exames = new ArrayList<ReceituarioExames>();
	ReceituarioExamesDAO reDAO = new ReceituarioExamesDAO(HibernateUtil.getSessionFactory().openSession());

	Consulta consulta = (Consulta)session.getAttribute("consulta");
	
	if (consulta != null) {
		
		if (request.getParameter("acao") != null) {
				
			if (request.getParameter("acao").equals("add")  && !request.getParameter("descricao").isEmpty()) {
			
				ReceituarioExames re = new ReceituarioExames();
				
				re.setProntuario(consulta);
				re.setExames(request.getParameter("descricao"));
			
				reDAO.salvar(re);	
				
			
			} else if (request.getParameter("acao").equals("excluir")  && !request.getParameter("codigo_exame").isEmpty()) {
			
				String codigo_string = request.getParameter("codigo_exame");
				
				ReceituarioExames re = reDAO.getReceituario(Integer.parseInt(codigo_string));
				
				reDAO.excluir(re);
			
			}
		}

		exames = reDAO.getReceituariosDaConsulta(consulta.getCodigo());
		
	}
	
	pageContext.setAttribute("exames", exames);
%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1.0" />
<title>Lista de Exames - Mão Amiga</title>

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
				<b>Lista de Exames</b>
			</h1>

		</div>

	</div>

	<div class="section no-pad-bot" id="index-banner">
		<div class="container">
			<form action="listaExames.jsp" method="get">
				<div class="row">
					<div class="col s12 m12">
						<table class="bordered hoverable">
							<thead>
								<tr>
									<td><b>Código</b></td>
									<td><b>Exame</b></td>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${exames}" var="exame">
									<tr>
										<td><input class="with-gap" type="radio" name="codigo_exame"
											id="${exame.codigo }" value="${exame.codigo }" /> <label
											for="${exame.codigo }">${exame.codigo }</label></td>
										<td>${exame.exames}</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
				</div>

				<br> <br>

				<div class="right">

					 <!-- Modal Trigger -->
					   <button data-target="modal1" class="btn modal-trigger">
					   	Adicionar
					   	<i class="material-icons right">plus_one</i>
					   </button>
					
					  <!-- Modal Structure -->
					  <div id="modal1" class="modal modal-fixed-footer">
					    <div class="modal-content">
					      <h4>Novo Exame</h4>
					      <p>Adicione o exame abaixo</p>
					      <textarea name="descricao"></textarea>
					    </div>
					    <div class="modal-footer">					      
							<button class="modal-action modal-close btn waves-effect waves-light" type="submit"
								name="acao" value="add">
								Adicionar novo Exame <i class="material-icons right">playlist_add_check</i>
							</button>
							
							<button class="modal-action modal-close btn waves-effect waves-light" form="#">
								Cancelar <i class="material-icons right">delete</i>
							</button>
					    </div>
					  </div>

					<button class="btn waves-effect waves-light" type="submit"
						name="acao" value="excluir">
						Excluir <i class="material-icons right">delete</i>
					</button>

					<a class="btn waves-effect waves-light" href="index.jsp">
						Cancelar <i class="material-icons right">cancel</i>
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
	<script>
	 $(document).ready(function(){
		    // the "href" attribute of .modal-trigger must specify the modal ID that wants to be triggered
		    $('.modal-trigger').leanModal();
		  });
	</script>

</body>
</html>
