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
	int productId;
	String urlSaver = "";
	try{
			productId = Integer.parseInt(request.getParameter("item"));
			urlSaver = "&item=" + productId; 
	}
	catch(NumberFormatException e)
	{
		productId = -1;
	}
	urlSaver.trim();	
%>
<core:set var="productId" value="<%=productId%>"/>

<sql:query dataSource="${snapshot}" var="inventory">
		SELECT * FROM "Inventory" INNER JOIN "Boxes" ON ("Inventory"."idBox" = "Boxes".id) WHERE "idProduct" = ? LIMIT 10;
		<sql:param value="${productId}" />
</sql:query>

<core:set var="total" value="${fn:length(inventory.rows)}"/>
<% 
	int perPage = 5;
	int totalRecords = 0, totalPage = 0, lastPageItem = 0; 
	int currentPage = 0;
	try 
	{
		totalRecords =  (int)pageContext.getAttribute("total");
		totalPage = totalRecords/perPage;
		lastPageItem = totalRecords%perPage;
		currentPage = Integer.parseInt(request.getParameter("start"));
		if(currentPage > totalPage)
		{
			currentPage = totalPage;
		}
		else if (currentPage < 0)
		{
			currentPage = 0;
		}
	}
	catch (NumberFormatException e)
	{
		e.printStackTrace();
	}
%>  

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<link rel="stylesheet" type="text/css" href="Intranet.css">
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Soprema - Produit <core:out value="${productId}"/></title>
</head>
<body>
	<div id="header">
		<jsp:include page="Header.jsp" />
	</div>
	Information du produit <core:out value="${productId}"/>
	<div class="list">
		<core:set var="total" scope="session" value="${fn:length(inventory.rows)}"/>
		<core:set var="perPage" scope="session"  value="10"/>
		<core:set var="pageStart" value="${param.start}"/>
		<core:if test="${empty pageStart or pageStart < 0}">
		    <core:set var="pageStart" value="0"/>
		</core:if>
		<core:if test="${total <= pageStart}">
		    <core:set var="pageStart" value="${total}"/>
		</core:if>	
		<table width="59%" border="1">
			<tr>
				<td>ID</td>
			 	<td>Quantité</td>
			 	<td>Poids</td>
			 	<td>Taille</td>
			 	<td>Date de création</td>
			 	<td>Date de modification</td>
				<td>Code barre</td>
			</tr>
			<core:forEach var="row" items="${inventory.rows}" begin="<%=currentPage*perPage%>" end="<%=perPage*(currentPage+1)-1 %>">
				<tr>
				 	<td><a href="FrontendBoxDetail?Box=${row.id}"><core:out value="${row.idBox}"/></a></td>
				 	<td><core:out value="${row.quantity}"/></td>
				 	<td><core:out value="${row.weight}"/></td>
				 	<td><core:out value="${row.size}"/></td>
				 	<td><core:out value="${row.creation_date}"/></td>
				 	<td><core:out value="${row.last_modified}"/></td>
					<td><core:out value="${row.barcode}"/></td>
				</tr>
			</core:forEach>
		</table>
		<a href="?start=<%=(currentPage-1)+urlSaver%>">Previous</a>
		<%=currentPage*perPage+1 %> - <%=perPage*(currentPage+1) %>
		<a href="?start=<%=(currentPage+1)+urlSaver%>">Next</a><br/>
	</div>
</body>
</html>