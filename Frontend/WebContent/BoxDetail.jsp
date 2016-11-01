<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="org.teamneko.schrodinger.frontend.FrontendBoxDetail" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<sql:setDataSource var="snapshot" driver="org.postgresql.Driver"
     url="jdbc:postgresql://elmer.db.elephantsql.com:5432/jmtntlek"
     user="jmtntlek"  password="vaYxsY1WBNr5gYMMd-74kLrc98gqNhqI"/>

<%
	int boxId;
	try{
		boxId = Integer.parseInt(request.getParameter("Box"));
	}
	catch(NumberFormatException e)
	{
		boxId = -1;
	}
%>

<core:set var="boxId" value="<%=boxId%>"/>

<sql:query dataSource="${snapshot}" var="box">
		SELECT * FROM "Boxes" WHERE id = ?;
		<sql:param value="${boxId}" />
</sql:query>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Soprema - Boite <core:out value="${boxId}"/></title>
</head>
<body>
	<div id="header">
		<jsp:include page="Header.jsp" />
	</div>
	<core:if test="${boxId != -1}">
		<core:forEach var="row" items="${box.rows}">
			<form action="FrontendBoxDetail" method="Post">
				ID: <input type="text" value="${row.id}">
				Poids: <input type="text" value="${row.weight}">
				Taille: <input type="text" value="${row.size}">
				Code Barre: <input type="text" value="${row.barcode}">
			<input type="submit" value="Modifier"/>
		</form> 
		</core:forEach>  
	</core:if> 
	<core:if test="${boxId == -1}">
		<form action="FrontendBoxDetail" method="Post">
			ID: <input type="text" value="${row.id}">
			Poids: <input type="text" value="${row.weight}">
			Taille: <input type="text" value="${row.size}">
			Code Barre: <input type="text" value="${row.barcode}">
			<input type="submit" value="Ajouter"/>
		</form>
	</core:if>
</body>
</html>