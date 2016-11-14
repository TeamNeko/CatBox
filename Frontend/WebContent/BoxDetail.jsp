<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<sql:setDataSource var="snapshot" driver="org.postgresql.Driver"
     url="jdbc:postgresql://elmer.db.elephantsql.com:5432/jmtntlek"
     user="jmtntlek"  password="vaYxsY1WBNr5gYMMd-74kLrc98gqNhqI"/>

<%
	int boxId = -1, size = 0;
	float weight = 0;
	boolean needUpdate = false;
	try{
		boxId = Integer.parseInt(request.getParameter("box"));
		weight = Float.parseFloat(request.getParameter("weight"));
		size = Integer.parseInt(request.getParameter("size"));
	}
	catch(NumberFormatException e)
	{
		e.printStackTrace();
	}
	catch(NullPointerException e)
	{ 
		e.printStackTrace();
	}
	if(weight!=0 || size != 0)
	{
		needUpdate = true;
	}
%>

<core:set var="boxId" value="<%=boxId%>"/>
<core:set var="weight" value="<%=weight%>"/>
<core:set var="size" value="<%=size%>"/>

<core:if test="<%=needUpdate %>">
	<sql:update dataSource="${snapshot}">
		UPDATE boxes SET weight = ?, size = ? WHERE id = ?;
		<sql:param value="${weight}" />
		<sql:param value="${size}" />
		<sql:param value="${boxId}" />
	</sql:update>
</core:if>

<sql:query dataSource="${snapshot}" var="box">
		SELECT * FROM boxes WHERE id = ?;
		<sql:param value="${boxId}" />
</sql:query>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Soprema - Boite <core:out value="${boxId}"/></title>
</head>
<body>
<div class="container-fluid">
	<div>
		<jsp:include page="Header.jsp" />
	</div>
	<core:if test="${boxId != -1}">
		<core:forEach var="row" items="${box.rows}">
			<form action="?box=${boxId}" method="Post">
				ID: <input type="text" value="${row.id}" disabled>
				Poids: <input type="text" name="weight" value="${row.weight}">
				Taille: <input type="text" name="size" value="${row.size}">
				Code Barre: <input type="text" value="${row.barcode}">
			<input type="submit" value="Modifier"/>
		</form> 
		</core:forEach>  
	</core:if> 
	<core:if test="${boxId == -1}">
		<form>		
			ID: <input type="text" value="${row.id}" disabled>
			Poids: <input type="text" name="weight" value="${row.weight}">
			Taille: <input type="text" name="size" value="${row.size}">
			Code Barre: <input type="text" value="${row.barcode}">
			<input type="submit" value="Ajouter"/>
		</form>
	</core:if>
</div>
</body>
</html>