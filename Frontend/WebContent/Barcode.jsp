<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="frontend.catbox.teamneko.BarCodeGenerator" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Code barre</title>
</head>
<body>
	<div id="header">
		<jsp:include page="Header.jsp" />
	</div>
	<div>
		<h3> Entrez un code barre à générer </h3>
			<form action="BarCodeGenerator" method="get">
				Code: <input type="text" name="Code">
				<input type="submit" value="Générer"/>
		</form>         
	</div>
</body>
</html>