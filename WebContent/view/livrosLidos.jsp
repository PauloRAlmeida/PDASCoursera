<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %><!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<% String login = (String) session.getAttribute("login"); %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1">

<title>Livros Coursera/ITA</title>
<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet"
	type="text/css" />
<script src="bootstrap/js/bootstrap.min.js" /></script>

</head>
<body>
	<jsp:include page="menuSuperiorInterno.jspf"></jsp:include>
	<div class="container">
		<div class="row">
			<div class="col-sm-12 text-center">
				<h3>Lista de Livros lidos</h3>
			</div>
			<div class="col-sm-3">
				<jsp:include page="menuLateral.jspf"></jsp:include>
			</div>
			<div class="col-sm-8 text-left">
				<div class="Side-by-side">
					<ul class="list-group">
						<c:forEach var="item" items="${livrosLidos}" >
							<li class="list-group-item"><div>Nome: ${item.nome}</div>  <div>Estilo: ${item.estilo}</div></li>	
						</c:forEach>
					</ul>
				</div>
			</div>
		</div>
		<script
			src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
		<script src="bootstrap/js/bootstrap.min.js"></script>
</body>
</html>