<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Cr�ation d'utilisateur</title>
</head>
<body>
	<div id="header">
		<jsp:include page="Header.jsp" />
	</div>
	<div class="creationList">
		<form action="CreateUserSuccess.jsp">
			<ul>
				<li>Pr�nom: <input type="text" name="first_name"></li>
				<li>Nom: <input type="text" name="last_name"></li>
				<li>Num�ro d'employer: <input type="text" name="number"></li>
				<li>Type d'utilisateur: 
				<select name="type">
					<option value="Employ�" Selected>Employ�</option>
					<option value="Gestionnaire">Gestionnaire</option>
				</select></li>
			</ul>
			<input type="submit" value="Ajouter"/>
		</form>
	</div>
</body>
</html>