<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page import="frontend.catbox.teamneko.org.FrontendBoxDetail" %>
<!--
<sql:setDataSource var="snapshot" driver="org.postgresql.Driver"
     url="jdbc:postgresql://localhost/catbox"
     user="jaune"  password="yolo"/>
-->

<sql:setDataSource var="snapshot" driver="org.postgresql.Driver"
     url="jdbc:postgresql://elmer.db.elephantsql.com:5432/jmtntlek"
     user="jmtntlek"  password="vaYxsY1WBNr5gYMMd-74kLrc98gqNhqI"/>

<core:set var="KeyWord" value="<%=FrontendBoxDetail.Item%>"/>

<sql:query dataSource="${snapshot}" var="inventory">
		SELECT * FROM "Inventory" WHERE ? = ? LIMIT 1;
		<sql:param value="idProduct" />
		<sql:param value="${KeyWord}" />
</sql:query>

<sql:query dataSource="${snapshot}" var="boxes">
		SELECT * FROM "Boxes" LIMIT 50;
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
	<div class="detail">
		<core:if test="${fn:length(inventory.rows) > 0}">
			<core:set var="inventoryRow" value="${inventory[0].rows}"/>
			<ul>
				<li><core:out value="${inventoryRow.idInventory}"/></li>
				<li><core:out value="${inventoryRow.idProduct}"/></li>
				<li><core:out value="${inventoryRow.idBox}"/></li>
				<li><core:out value="${inventoryRow.quantity}"/></li>
			</ul>
		</core:if>
	</div>
	<table>
	<core:forEach var="row" items="${boxes.rows}">
		<tr>
			<td><core:out value="${row.id_box}"/></td>
			<td><core:out value="${row.weight}"/></td>
			<td><core:out value="${row.creation_date}"/></td>
			<td><core:out value="${row.last_modified}"/></td>
			<td><core:out value="${row.size}"/></td>
		</tr>
	</core:forEach>
	</table>
	<div id="footer">
		<jsp:include page="footer.jsp" />
	</div>
</body>
</html>