<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %><!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<% String login = (String) session.getAttribute("login"); %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1">

<title>Forum com gamification</title>
<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet"
	type="text/css" />
<script src="bootstrap/js/bootstrap.min.js" /></script>

</head>
<body>
	<jsp:include page="menuSuperiorInterno.jspf"></jsp:include>
	<div class="container">
		<div class="row">
			<div class="col-sm-12 text-center">
				<h3>Visualizar Tópico</h3>
			</div>
			<div class="col-sm-3">
				<jsp:include page="menuLateral.jspf"></jsp:include>
			</div>
			<div class="col-sm-8 text-left">
				
				<form class="form form-horizontal" action="controllerTopico" method="POST">
					<div class="input-group col-sm-12">
	                	<h3>Título: ${topico.titulo}</h3>
	                	<div class="panel">
	                		${topico.conteudo}
	                	</div>
	                </div>
					<h4 class="text-right" id="autorTopico">Autor: ${topico.login}</h4>
	                
				</form>
				<hr>
				<h3>Comentários</h3>
				<c:forEach var="item" items="${comentarios}" >
					<div class="panel panel-dafault">
						<h4>Autor: ${item.login} </h4>
						<p>${item.comentario}</p>
					</div>
				</c:forEach>
				<form action="controllerComentario" method="POST">
				<input type="hidden" name="idTopico" value="${topico.idTopico}">
				<input type="hidden" name="autor" value="${topico.login}">
				<input type="hidden" name="login" value="<%=login %>">
				<label class="control-label">Inserir comentário</label>
					<textarea name="comentario" class="form-control" id="comentarioTopico">
					</textarea>
					<br>
	                <input type="submit" class="btn btn-success btn-block" name="formAction" value="Inserir comentario">	
				</form>
							
				
			</div>
		</div>
		<script
			src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
		<script src="bootstrap/js/bootstrap.min.js"></script>
</body>
</html>