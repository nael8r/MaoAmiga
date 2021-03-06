<!--
	Página de impressao de dados da consulta
-->

<%@page import="conexao.HibernateUtil"%>
<%@page import="controle.ConsultaDAO"%>
<%@page import="modelo.Consulta"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<%
	pageContext.setAttribute("consulta", request.getAttribute("consulta"));
%>

<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
		<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1.0"/>
		<title>Imprimir Consulta</title>
</head>
<body>
	<h1>Associação Mão Amiga</h1>
	<p>
		Associação no combate ao câncer em Formiga.<br />
		Rua Lassance Cunha, 39 - Centro - Formiga MG. <br/>
		Telefone: (37) 3322-3291.
	</p>

	<table>
		<tr>
			<td><b>Código Número:</b></td>
			<td>${consulta.codigo }</td>
		</tr>

		<tr>
			<td><b>Nome Paciente:</b></td>
			<td>${consulta.paciente.nome }</td>
		</tr>

		<tr>
			<td><b>Data da Consulta:</b></td>
			<td>${consulta.data }</td>
		</tr>
		
		<tr>
			<td><b>Horário da Consulta:</b></td>
			<td>${consulta.hora }</td>
		</tr>
		
		<tr>
			<td><b>Médico Responsável:</b></td>
			<td>${consulta.medico.nome }</td>
		</tr>
		
		<tr>		
			<td><b>Tipo da Consulta:</b></td>
			<td>
				<c:choose>
				    <c:when test="${consulta.tipoConsulta eq '1'.charAt(0) }">
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
		</tr>
		
	</table>

</body>
</html>
