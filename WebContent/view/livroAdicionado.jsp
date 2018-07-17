<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Livros ITA/COURSERA</title>
<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet"
	type="text/css" />
<script src="bootstrap/js/bootstrap.min.js" /></script>
</head>
<body>
<jsp:include page="../menuSuperior.jspf"></jsp:include>
	<div class="container">
		<div class="row">
			<div class="alert alert-success">
				<h3>${msg}</h3>
			</div>
		</div>
	</div>
	<meta http-equiv="refresh" content="2;http://localhost:8080/PDASCoursera/controllerUsuario?action=listaDeLivros">
</body>
</html>