<!DOCTYPE html>
<html lang="en">
<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
		<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1.0"/>
		<title>Pré Consulta - Mão Amiga</title>

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
				<b>Pré Consulta</b>
			</h1>

		</div>

	</div>

	<div class="section no-pad-bot" id="index-banner">
		<div class="container">
			<h2 class="center">Formulário Pré Consulta</h2>

			<form action="#">
				<div class="row">
					<div class="input-field col s12 m6">
						<i class="material-icons prefix">account_circle</i>
						<input disabled value="000" id="codigo" type="text" class="validate">
						<label for="codigo">Código</label>
					</div>

					<div class="input-field col s12 m6">
						<i class="material-icons prefix">account_circle</i>
						<input disabled value="Asdf" id="paciente" type="text" class="validate">
						<label for="paciente">Paciente</label>
					</div>
				</div>	

				<div class="row">
					<div class="input-field col s12 m4">
						<i class="material-icons prefix">account_circle</i>
						<input type="text" id="idade">
						<label for="idade">Idade</label>
					</div>

					<div class="input-field  col s12 m4">
						<select>
							<option value="" disabled selected>Sexo</option>
							<option value="1">Masculino</option>
							<option value="2">Feminino</option>
							<option value="3">Outros</option>
						</select>

					</div>

					<div class="input-field col s12 m4">
						<i class="material-icons prefix">account_circle</i>
						<input type="text" id="peso">
						<label for="peso">Peso</label>
					</div>
				</div>	

				<div class="row">
					<div class="input-field col s12 m4">
						<i class="material-icons prefix">account_circle</i>
						<input type="text" id="altura">
						<label for="altura">Altura</label>
					</div>

					<div class="input-field col s12 m4">
						<i class="material-icons prefix">account_circle</i>
						<input type="text" id="temperatura">
						<label for="temperatura">Temperatura</label>
					</div>

					<div class="input-field col s12 m4">
						<i class="material-icons prefix">account_circle</i>
						<input type="text" id="pressao">
						<label for="pressao">Pressão Arterial</label>
					</div>
				</div>

							<br><br>

				<div class="right">
					<button class="btn waves-effect waves-light" type="submit" name="action">Enviar
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