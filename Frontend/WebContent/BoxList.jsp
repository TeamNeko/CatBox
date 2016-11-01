<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>


<sql:setDataSource var="snapshot" driver="org.postgresql.Driver"
     url="jdbc:postgresql://elmer.db.elephantsql.com:5432/jmtntlek"
     user="jmtntlek"  password="vaYxsY1WBNr5gYMMd-74kLrc98gqNhqI"/>
     
<% 
	String urlSaver = ""; 
	int boxId;
	try{
		boxId = Integer.parseInt(request.getParameter("Recherche"));
		urlSaver += "&Recherche=" + boxId;
	}
	catch(NumberFormatException e)
	{
		boxId = -1;
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

<core:set var="keyWord" value="<%=boxId %>"/>
<core:set var="sortColumnOrder" value="<%=sortColumnOrder %>"/>
<core:set var="sortColumnName" value="<%=sortColumnName %>"/>

<core:if test="${keyWord == -1}">
	<sql:query dataSource="${snapshot}" var="box"> 
		SELECT * FROM "Boxes" ORDER BY "${sortColumnName}" ${sortColumnOrder} LIMIT 50;
	</sql:query>
</core:if>
<core:if test="${keyWord != -1}">
	<sql:query dataSource="${snapshot}" var="box">
		SELECT * FROM "Boxes" WHERE id = ? ORDER BY "${sortColumnName}" ${sortColumnOrder} LIMIT 50;
		<sql:param value="${keyWord}" />
	</sql:query>
</core:if>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Soprema - Liste de boite</title>
</head>
<body>
	<div id="header">
		<jsp:include page="Header.jsp" />
	</div>
	<div class="search">
	 	<form action="FrontendBoxServer">
			Recherche: <input type="text" name="Recherche">
			<button type="submit" id="search"></button>
			Ordre d'affichage:  
				<select name="Sort">
					<option value="Asc-id" <%=sortString.equals("Asc-id") ? "selected" : ""%>>Ordre croissant d'ID</option>
					<option value="Des-id" <%=sortString.equals("Des-id")? "selected" : ""%>>Ordre d�croissant d'ID</option>
					<option value="Asc-barcode" <%=sortString.equals("Asc-barcode") ? "selected" : ""%>>Ordre croissant de code barre</option>
					<option value="Des-barcode" <%=sortString.equals("Des-barcode")? "selected" : ""%>>Ordre d�croissant de code barre</option>
					<option value="Asc-weight" <%=sortString.equals("Asc-weight") ? "selected" : ""%>>Ordre croissant de poids</option>
					<option value="Des-weight" <%=sortString.equals("Des-weight") ? "selected" : ""%>>Ordre d�croissant de poids</option>
					<option value="Asc-size" <%=sortString.equals("Asc-size") ? "selected" : ""%>>Ordre croissant de taille</option>
					<option value="Des-size" <%=sortString.equals("Des-size") ? "selected" : ""%>>Ordre d�croissant de taille</option>
					<option value="Asc-creation_date" <%=sortString.equals("Asc-creation_date") ? "selected" : ""%>>Ordre croissant de cr�ation</option>
					<option value="Des-creation_date" <%=sortString.equals("Des-creation_date") ? "selected" : ""%>>Ordre d�croissant de cr�ation</option>
					<option value="Asc-last_modified" <%=sortString.equals("Asc-last_modified") ? "selected" : ""%>>Ordre croissant de retrait</option>
					<option value="Des-last_modified" <%=sortString.equals("Des-last_modified") ? "selected" : ""%>>Ordre d�croissant de retrait</option>
				</select>
		</form>
	</div>
	<div class="list">
		<table width="59%" border="1">
			<tr>
				<th>ID</th>
				<th>Code barre</th>
				<th>Poids</th>
				<th>Taille</th>
				<th>Date de cr�ation</th>
				<th>Date de modification</th>
			</tr>
			<core:set var="total" scope="session" value="${fn:length(box.rows)}"/>
			<core:set var="perPage" scope="session"  value="10"/>
			<core:set var="pageStart" value="${param.start}"/>
			<core:if test="${empty pageStart or pageStart < 0}">
			    <core:set var="pageStart" value="0"/>
			</core:if>
			<core:if test="${total <= pageStart}">
			    <core:set var="pageStart" value="${total}"/>
			</core:if>			
			<core:forEach var="row" items="${box.rows}" begin="${pageStart}" end="${pageStart + perPage - 1}">
				<tr>
					<td><a href="FrontendBoxDetail?Box=${row.id}"><core:out value="${row.id}"/></a></td>
					<td><core:out value="${row.barcode}"/></td>
					<td><core:out value="${row.weight}"/></td>
					<td><core:out value="${row.size}"/></td>
					<td><core:out value="${row.creation_date}"/></td>
					<td><core:out value="${row.last_modified}"/></td>
				</tr>
			</core:forEach>
		</table>
		<a href="?start=${pageStart - perPage}<%=urlSaver%>">Previous</a>${pageStart +1} - ${pageStart + perPage}
		<a href="?start=${pageStart + perPage}<%=urlSaver%>">Next</a><br/>
	</div>
</body>
</html>