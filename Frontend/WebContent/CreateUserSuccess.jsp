<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<sql:setDataSource var="snapshot" driver="org.postgresql.Driver"
     url="jdbc:postgresql://elmer.db.elephantsql.com:5432/jmtntlek"
     user="jmtntlek"  password="vaYxsY1WBNr5gYMMd-74kLrc98gqNhqI"/>
     
<%
	int id = 1;
	String firstName = "", lastName = "", userType = "Employé",  userNum = "";
	try{
		userNum = request.getParameter("number");
		firstName = request.getParameter("first_name");
		lastName = request.getParameter("last_name");
		userType = request.getParameter("type");
	}
	catch(NullPointerException e)
	{ 
		e.printStackTrace();
	}
%>     

<sql:query dataSource="${snapshot}" var="user">
	SELECT * FROM "Users";
</sql:query>

<core:catch var="SQLException">
	<core:forEach var="row" items="${user.rows}">
		<% id++; %>
	</core:forEach>
</core:catch>
<core:set var="id" value="<%=id%>"/>
<core:set var="userNum" value="<%=userNum%>"/>
<core:set var="firstName" value="<%=firstName%>"/>
<core:set var="lastName" value="<%=lastName%>"/>
<core:set var="userType" value="<%=userType%>"/>

<sql:update dataSource="${snapshot}">
	INSERT INTO "Users" VALUES (?, ?, ?, ?, ?);
	<sql:param value="${id}" />
	<sql:param value="${firstName}" />
	<sql:param value="${lastName}" />
	<sql:param value="${userNum}" />
	<sql:param value="${userType}" />
</sql:update>

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
		<core:if test="${SQLException != null}">
			Une erreur s'est produite. Cliquez <a href="CreateUser.jsp">ici</a> pour recommencer.
		</core:if>
		<core:if test="${SQLException == null}">
		L'utilisateur :
			<ul>
				<li><core:out value="${firstName}"/></li>
				<li><core:out value="${lastName}"/></li>
				<li><core:out value="${userNum}"/></li>
				<li><core:out value="${userType}"/></li>
			</ul>
		<p>a été ajouté .</p>
		</core:if>
</body>
</html>