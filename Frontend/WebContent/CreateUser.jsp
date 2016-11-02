<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Création d'utilisateur</title>
</head>
<body>
	<div id="header">
		<jsp:include page="Header.jsp" />
	</div>
	<div class="creationList">
		<ul>
			<li>Prénom: <input type="text"></li>
			<li>Nom: <input type="text"></li>
			<li>Numéro d'employé: <input type="text"></li>
			<li>Type d'utilisateur: 
			<select name="Type">
				<option value="Gestionnaire">Gestionnaire</option>
				<option value="Employé">Employé</option>
			</select></li>
		</ul>
	</div>
</body>
</html>