<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Création d'utilisateur</title>
</head>
<body>
<div class="container-fluid">
	<div>
		<jsp:include page="Header.jsp" />
	</div>
	<div class="creationList">
		<form action="CreateUserSuccess.jsp">
			<div class="form-group">
				<label for="first_name">Prénom: </label>
				<input id="first_name" class="form-control" type="text" name="first_name">
				
				<label for="last_name">Nom: </label>
				<input id="last_name" class="form-control" type="text" name="last_name">
				
				<label for="number">Numéro d'employer: </label>
				<input id="number" class="form-control" type="text" name="number">
				
				<label for="type">Type d'utilisateur: </label>
				<select id="type" class="form-control" name="type">
					<option value="Employe" Selected>Employé</option>
					<option value="Gestionnaire">Gestionnaire</option>
				</select>
			</div>
			<input type="submit" class="btn btn-success" value="Ajouter"/>
		</form>
	</div>
</div>
</body>
</html>