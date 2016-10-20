<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!--
<sql:setDataSource var="snapshot" driver="org.postgresql.Driver"
     url="jdbc:postgresql://localhost/catbox"
     user="jaune"  password="yolo"/>
-->

<sql:setDataSource var="snapshot" driver="org.postgresql.Driver"
     url="jdbc:postgresql://elmer.db.elephantsql.com:5432/jmtntlek"
     user="jmtntlek"  password="vaYxsY1WBNr5gYMMd-74kLrc98gqNhqI"/>

<core:if test="${empty param.Recherche}">
	<sql:query dataSource="${snapshot}" var="products">
		SELECT * FROM "Products" LIMIT 50;
	</sql:query>
</core:if>
<core:if test="${not empty param.Recherche}">
	<sql:query dataSource="${snapshot}" var="products">
		SELECT * FROM "Products" LIMIT 50;
	</sql:query>
</core:if>


     
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
	 	<form action="FrontendServer">
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
				<th>Description</th>
				<th>Date d'ajout</th>
				<th>Date de retrait</th>
				<th>Poids (kg)</th>
			</tr>
			<core:set var="total" scope="session" value="fn:length(products)"/>
			<core:set var="perPage" scope="session"  value="1"/>
			<core:set var="pageStart" value="${param.start}"/>
			<core:if test="${empty pageStart or pageStart < 0}">
			    <core:set var="pageStart" value="0"/>
			</core:if>
			<core:if test="${total <= pageStart}">
			    <core:set var="pageStart" value="${total}"/>
			</core:if>			
			<core:forEach var="row" items="${products.rows}" begin="${pageStart}" end="${pageStart + perPage - 1}">
				<tr>
					<td><core:out value="${row.idProduct}"/></td>
					<td><core:out value="${row.name}"/></td>
					<td><core:out value="${row.description}"/></td>
					<td><core:out value="${row.date_added}"/></td>
					<td><core:out value="${row.date_retired}"/></td>
					<td><core:out value="${row.weight}"/></td>
				</tr>
			</core:forEach>
		</table>
			<a href="?start=${pageStart - perPage}">Previous</a>${pageStart +1} - ${pageStart + perPage} 
			<a href="?start=${pageStart + perPage}">Next</a><br/>
	</div>
	<div id="footer">
		<jsp:include page="Footer.jsp" />
	</div>
</body>
</html>