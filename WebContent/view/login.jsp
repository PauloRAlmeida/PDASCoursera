<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Criar Conta</title>
<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css"  />
<script src="bootstrap/js/bootstrap.min.js" /></script>

</head>
	<body>
		<jsp:include page="../menuSuperior.jspf"></jsp:include>
		<!-- Page Content -->
    <div class="container">
        <div class="row">
        	<div class="alert alert-success text-center">
        		<h3>${msg}</h3>
        	</div>
        	<div class="col-sm-4"></div>
            <div class="col-sm-4 text-left">
                <h1>Login</h1>
                <form class="form-horizontal" action="controllerUsuario" method="POST">
	                <div class="input-group col-sm-12">
	                	<label class="control-label">Login</label>
	                	<input type="text" class="form-control input-sm" name="email" id="loginUsuario" />
	                </div>
	                <div class="input-group col-sm-12">
	                	<label class="control-label">Senha</label>
	                	<input type="password" class="form-control input-sm" name="senha" id="senhaUsuario" />
	                </div>
	                <br>
	                <input type="submit" class="btn btn-success btn-block" name="formAction" value="Entrar">
	            </form>
            </div>
        	<div class="col-sm-4"></div>
        </div>
        <!-- /.row -->
    </div>
    <!-- /.container -->
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
		<script src="bootstrap/js/bootstrap.min.js"></script>
		</body>
</html>