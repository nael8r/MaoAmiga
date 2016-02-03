<!DOCTYPE html>
<html lang="en">
<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
		<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1.0"/>
		<title>Lista Pacientes - Mão Amiga</title>

		<!-- CSS  -->
		<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
		<link href="css/materialize.css" type="text/css" rel="stylesheet" media="screen,projection"/>
		<link href="css/style.css" type="text/css" rel="stylesheet" media="screen,projection"/>
</head>
<body>
	
	<nav class="light-blue lighten-1" role="navigation">
		<div class="nav-wrapper container">
			<a id="logo-container" href="index.html" class="brand-logo center"><b>Ambulatório Amigo Online<b></a>
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
							<td><b>Paciente</b></td>
							<td><b>Realizado</b></td>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td>01</td>
							<td>Asdf</td>
							<td>V</td>
						</tr>
						<tr>
							<td>01</td>
							<td>Asdf</td>
							<td>X</td>
						</tr>
						<tr>
							<td>01</td>
							<td>Asdf</td>
							<td>X</td>
						</tr>
					</tbody>
				</table>

							<br><br>

				<div class="right">
					<button class="btn waves-effect waves-light" type="submit" name="action">Protuário Médico
						<i class="material-icons right">send</i>
					</button>

					<button class="btn waves-effect waves-light" type="submit" name="action">Cancelar
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
				<div class="col l3 s12">
					<h5 class="center  white-text">Ambulatório</h5>
					<ul>
						<li><a href="agendarConsulta.html" class="collection-item white-text"> <i class="material-icons">send</i> Agendar Consultas </a> </li>
						<li><a href="listaEspera.html" class="collection-item white-text"> <i class="material-icons">send</i> Lista de Espera</a></li>
						<li><a href="agendaConsultas.html" class="collection-item white-text"> <i class="material-icons">send</i> Agenda de Consultas</a></li>
					</ul>
				</div>
				<div class="col l3 s12">
					<h5 class="center  white-text">Consultório Médico</h5>
					<ul>
						<li><a href="listaPacientes.html" class="collection-item white-text"> <i class="material-icons">send</i>Lista de Pacientes</a> </li>
						<li><a href="prontuarioMedico.html" class="collection-item white-text"> <i class="material-icons">send</i>Prontuário Médico</a> </li>
						<li><a href="consultarMedicamentos.html" class="collection-item white-text"> <i class="material-icons">send</i>Consultar Medicamentos</a> </li>
					</ul>
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
