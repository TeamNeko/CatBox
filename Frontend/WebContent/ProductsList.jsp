<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>

<!--
<sql:setDataSource var="snapshot" driver="org.postgresql.Driver"
     url="jdbc:postgresql://localhost/catbox"
     user="jaune"  password="yolo"/>
-->

<sql:setDataSource var="snapshot" driver="org.postgresql.Driver"
     url="jdbc:postgresql://elmer.db.elephantsql.com:5432/jmtntlek"
     user="jmtntlek"  password="vaYxsY1WBNr5gYMMd-74kLrc98gqNhqI"/>

<sql:query dataSource="${snapshot}" var="products">
SELECT * FROM "Products" LIMIT 50;
</sql:query>
     
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<link rel="stylesheet" type="text/css" href="Intranet.css">
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Soprema - Liste de produits</title>
</head>
<body>
	<div id="header">
		<jsp:include page="Header.jsp" />
	</div>
	<div class="search">
	 	<form action="Backend">
			Recherche: <input type="text" name="Recherche">
			<button type="submit" id="search"></button>
			Some Search:  
				<select name="Events">
					<option value="0" selected>(Catégorie:)</option>
					<option value="Tuiles">1</option>
					<option value="Some other stuff">2</option>
					<option value="Some other stuff">3</option>
					<option value="Some other stuff">4</option>
				</select>
		</form>
	</div>
	<div class="list">
		<table width="59%" border="1">
			<tr>
				<th>ID</th>
				<th>Name</th>
			</tr>
			<core:forEach var="row" items="${products.rows}">
				<tr>
					<td><core:out value="${row.idProduct}"/></td>
					<td><core:out value="${row.name}"/></td>
				</tr>
			</core:forEach>
		</table>
	</div>
	<div id="footer">
		<jsp:include page="Footer.jsp" />
	</div>
</body>
</html>