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
     
<% 
	String urlSaver = ""; 
	String keyWord = request.getParameter("Recherche");
	if (keyWord != null)
	{
		if(!keyWord.isEmpty())
		{
			urlSaver += "&Recherche=" + keyWord;
		}
	}
	String sortString = request.getParameter("Sort");
	String sortColumnOrder = "ASC";
	String sortColumnName = "id";
	
	if(sortString != null)
	{
		String[] sortArguments = sortString.split("-");
		sortColumnOrder = sortArguments[0];
		sortColumnName = sortArguments[1];
		urlSaver += "&Sort=" + sortColumnOrder + "-" + sortColumnName;
		if(sortColumnOrder.equals("Des")){
			sortColumnOrder = "DESC";
		}
		else {
			sortColumnOrder = "ASC";
		}
	}
	else
	{
		sortString = "";
	}
	urlSaver.trim();
%>

<core:set var="keyWord" value="<%=keyWord %>"/>
<core:set var="sortColumnOrder" value="<%=sortColumnOrder %>"/>
<core:set var="sortColumnName" value="<%=sortColumnName %>"/>

<core:if test="${empty keyWord}">
	<sql:query dataSource="${snapshot}" var="products"> 
		SELECT * FROM "Products" LEFT OUTER JOIN (SELECT "idProduct", coalesce(sum(quantity),0) AS total FROM "Inventory" GROUP BY id) t ON "Products".id = t."idProduct" ORDER BY "${sortColumnName}" ${sortColumnOrder} LIMIT 50;
	</sql:query>
</core:if>
<core:if test="${not empty keyWord}">
	<sql:query dataSource="${snapshot}" var="products">
		SELECT * FROM "Products" WHERE name = ? ORDER BY "${sortColumnName}" ${sortColumnOrder} LIMIT 50;
		<sql:param value="${keyWord}" />
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
			Ordre d'affichage:  
				<select name="Sort">
					<option value="Asc-id" <%=sortString.equals("Asc-id") ? "selected" : ""%>>Ordre croissant d'ID</option>
					<option value="Des-id" <%=sortString.equals("Des-id")? "selected" : ""%>>Ordre décroissant d'ID</option>
					<option value="Asc-barcode" <%=sortString.equals("Asc-barcode") ? "selected" : ""%>>Ordre croissant de code barre</option>
					<option value="Des-barcode" <%=sortString.equals("Des-barcode") ? "selected" : ""%>>Ordre décroissant de code barre</option>
					<option value="Asc-name" <%=sortString.equals("Asc-name") ? "selected" : ""%>>Ordre croissant de nom de produit</option>
					<option value="Des-name" <%=sortString.equals("Des-name")? "selected" : ""%>>Ordre décroissant de nom de produit</option>
					<option value="Asc-weight" <%=sortString.equals("Asc-weight") ? "selected" : ""%>>Ordre croissant de poids</option>
					<option value="Des-weight" <%=sortString.equals("Des-weight") ? "selected" : ""%>>Ordre décroissant de poids</option>
					<option value="Asc-date_added" <%=sortString.equals("Asc-date_added") ? "selected" : ""%>>Ordre croissant de création</option>
					<option value="Des-date_added" <%=sortString.equals("Des-date_added") ? "selected" : ""%>>Ordre décroissant de création</option>
					<option value="Asc-date_retired" <%=sortString.equals("Asc-date_retired") ? "selected" : ""%>>Ordre croissant de retrait</option>
					<option value="Des-date_retired" <%=sortString.equals("Des-date_retired") ? "selected" : ""%>>Ordre décroissant de retrait</option>
				</select>
		</form>
	</div>
	<div class="list">
		<table width="59%" border="1">
			<tr>
				<th>ID</th>
				<th>Name</th>
				<th>Barcode</th>
				<th>Description</th>
				<th>Poids (kg)</th>
				<th>Date d'ajout</th>
				<th>Date de retrait</th>
				<th>Total</th>
			</tr>
			<core:set var="total" scope="session" value="${fn:length(products.rows)}"/>
			<core:set var="perPage" scope="session"  value="10"/>
			<core:set var="pageStart" value="${param.start}"/>
			<core:if test="${empty pageStart or pageStart < 0}">
			    <core:set var="pageStart" value="0"/>
			</core:if>
			<core:if test="${total <= pageStart}">
			    <core:set var="pageStart" value="${total}"/>
			</core:if>			
			<core:forEach var="row" items="${products.rows}" begin="${pageStart}" end="${pageStart + perPage - 1}">
				<tr>
					<td><a href="ProductDetail.jsp?Item=${row.id}"><core:out value="${row.id}"/></a></td>
					<td><core:out value="${row.name}"/></td>
					<td><core:out value="${row.barcode}"/></td>
					<td><core:out value="${row.description}"/></td>
					<td><core:out value="${row.weight}"/></td>
					<td><core:out value="${row.date_added}"/></td>
					<td><core:out value="${row.date_retired}"/></td>
					<td>
						<core:if test="${empty row.total}">0</core:if>
						<core:if test="${not empty row.total}"><core:out value="${row.total}"/></core:if>
					</td>
				</tr>
			</core:forEach>
		</table>
		<a href="?start=${pageStart - perPage}<%=urlSaver%>">Previous</a>${pageStart +1} - ${pageStart + perPage}
		<a href="?start=${pageStart + perPage}<%=urlSaver%>">Next</a><br/>
	</div>
</body>
</html>