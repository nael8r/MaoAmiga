<!--
	Página de Receituário Médico gerada pelo Médico
-->

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1.0" />
<title>Receituário Médico</title>
<%
	pageContext.setAttribute("medicamentos", session.getAttribute("medicamentos"));
	session.removeAttribute("medicamentos");
%>
</head>
<body>
	<h1>Associação Mão Amiga</h1>
	<p>
		Associação no combate ao câncer em Formiga.<br /> Rua Lassance Cunha,
		39 - Centro - Formiga MG. <br /> Telefone: (37) 3322-3291.
	</p>

	<h2>RECEITUÁRIO MÉDICO</h2>

	<table class="bordered hoverable">
		<thead>
			<tr>
				<td><b>Nome do Medicamento</b></td>
				<td><b>Forma de uso</b></td>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${medicamentos}" var="medicamento">
				<tr>
					<td>${medicamento.produtos.nome}</td>
					<td>${medicamento.medicamentos}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

</body>
</html>
