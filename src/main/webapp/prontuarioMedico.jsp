<%@page import="conexao.HibernateUtil"%>
<%@page import="controle.ProdutosDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<%
	pageContext.setAttribute("consulta", request.getAttribute("consulta"));
								
	// medicamentos
	ProdutosDAO pDAO = new ProdutosDAO(HibernateUtil.getSessionFactory().openSession());
	pageContext.setAttribute("produtos", pDAO.getProdutos());
	pDAO.getSessao().close();
%>
								    
<html lang="en">
<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
		<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1.0"/>
		<title>Prontuário Médico - Mão Amiga</title>

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
				<b>Prontuário Médico</b>
			</h1>

		</div>

	</div>

	<div class="section no-pad-bot" id="index-banner">
		<div class="container">
			<h2 class="center">Formulário de Dados</h2>

			<form action="prontuarioMedico" method="post">
				<div class="row">

					<div class="input-field col s12 m6">
						<i class="material-icons prefix">account_circle</i>
						<input disabled value="${consulta.paciente.nome }" id="paciente" type="text" class="validate">
									<label for="paciente">Paciente</label>
					</div>

					<div class="input-field col s12 m6">
						<i class="material-icons prefix">account_circle</i>
						<input disabled value="${consulta.medico.nome }" id="medico" type="text" class="validate">
									<label for="medico">Médico</label>
					</div>
				</div>  

				<div class="row">
					<div class="input-field col s12 m6">
						<i class="material-icons prefix">account_circle</i>
						<textarea id="queixa" class="materialize-textarea" name="queixa">${consulta.queixa }</textarea>
						<label for="queixa">Queixa Principal</label>

					</div>

					<div class="input-field  col s12 m6">
						<div class="col s12 m12" id="medicamento">
							Medicamentos:
							<!-- 
							<p>
								<input name="medicamentos" type="radio" class="medicamentos" id="medicamentos_nao" />
								<label for="medicamentos_nao">Não</label>
							</p>
								
								<input name="medicamentos" type="radio" class="medicamentos" id="medicamento_sim" />
								<label for="medicamento_sim">Sim</label>
								
								<button class="btn waves-effect waves-light right" id="bt_receituario_medico" type="submit" name="acao" value="receituario">Receituário Médico
									<i class="material-icons right">library_books</i>
								</button>
							 -->
								
								 <!-- Modal Trigger -->
								  <a class="modal-trigger waves-effect waves-light btn" href="#modal1">Medicamentos</a>
								
								
								  <!-- Modal Structure -->
								  <div id="modal1" class="modal modal-fixed-footer">
								    <div class="modal-content">
								      <h4>Escolha dos Medicamentos</h4>
								      Selecione o medicamento a ser receitado e escreva a descrição de uso:
										<table class="bordered hoverable">
											<thead>
												<tr>
													<td><b>Código</b></td>
													<td><b>Nome Medicamento</b></td>
													<td><b>Descrição  do Medicamento</b></td>
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
														<td>
															<input type="text" class="validate" name="medicamentos_descricao" />
														</td>
													</tr>
												</c:forEach>
											</tbody>
										</table>
								    </div>
								    <div class="modal-footer">
								    
								      <a href="#!" class="modal-action modal-close waves-effect waves-green btn-flat "><b>Pronto!</b></a>
								    </div>
								  </div>
								
								
						</div>  
						<div class="col s12 m12">
								
							<br><br>Pessoal:
							<p>
								<input name="pessoal" type="checkbox" id="alergia" value="alergia" />
								<label for="alergia">Alergia&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>
								
								<input name="pessoal" type="checkbox" id="febre" value="febre" />
								<label for="febre">Febre</label>
							</p>
						</div>
								<br><br><br><br><br><br><br><br><br><br><br>
						<div class="input-field s12 m12">
							<i class="material-icons prefix">account_circle</i>
							<textarea id="anotacoes" name="anotacoesFinais" class="materialize-textarea">${consulta.anotacoesFinais }</textarea>
							<label for="anotacoes">Anotações Finais</label>
						</div>
					</div>
			

				</div>  
							<br><br>

				<div class="left">

					<button class="btn waves-effect waves-light" type="submit" name="acao" value="atestado">Atestado Médico
						<i class="material-icons right">book</i>
					</button>

					<button class="btn waves-effect waves-light" type="submit" name="acao" value="receituario">Receituário Médico
						<i class="material-icons right">library_books</i>
					</button>

				</div>
				<div class="right">

					<button class="btn waves-effect waves-light" type="submit" name="acao" value="enviar">Enviar
						<i class="material-icons right">send</i>
					</button>

					<button class="btn waves-effect waves-light" type="submit" name="acao" value="cancelar">Cancelar
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
								<li><a href="listaConsultas.jsp" class="collection-item white-text"> <i class="material-icons">send</i> Agenda de Consultas</a></li>
							</ul>
						</div>
						<div class="col l3 s12">
							<h5 class="center  white-text">Consultório Médico</h5>
							<ul>
								<li><a class="collection-item white-text"> <i class="material-icons">send</i>Lista de Pacientes</a> </li>
								<li><a class="collection-item white-text"> <i class="material-icons">send</i>Prontuá¡rio Médico</a> </li>
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
	<script> 
	 $(document).ready(function(){
		    // the "href" attribute of .modal-trigger must specify the modal ID that wants to be triggered
		    $('.modal-trigger').leanModal();
		  });
	</script> 

	</body>
</html>
