<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<sql:setDataSource var="snapshot" driver="org.postgresql.Driver"
     url="jdbc:postgresql://elmer.db.elephantsql.com:5432/jmtntlek"
     user="jmtntlek"  password="vaYxsY1WBNr5gYMMd-74kLrc98gqNhqI"/>

<%
	int productId = -1;
	boolean needUpdate = false;
	String urlSaver = "";
	String name = "";
	String barcode = ""; 
	String description = "";
	float weight = 0; 
	int threshold = 0;
	try{
		productId = Integer.parseInt(request.getParameter("item"));
		urlSaver = "?item=" + productId; 
		name = request.getParameter("name");
		barcode = request.getParameter("barcode");
		description = request.getParameter("description");
		weight = Float.parseFloat(request.getParameter("weight"));
		threshold = Integer.parseInt(request.getParameter("threshold"));
		needUpdate = true;
	}
	catch(NumberFormatException e)
	{
		productId = -1;
	}
	catch(NullPointerException e)
	{
		needUpdate = false;
	}
	urlSaver.trim();	
	//if (name != null && barcode != null && description != null && weight != 0 && threshold != 0)
	//{
	//	needUpdate = true;
	//}
	
	String sortString = request.getParameter("sort");
	String sortColumnOrder = "ASC";
	String sortColumnName = "id";
	
	if(sortString != null)
	{
		String[] sortArguments = sortString.split("-");
		sortColumnOrder = sortArguments[0];
		sortColumnName = sortArguments[1];
		urlSaver += "&sort=" + sortColumnOrder + "-" + sortColumnName;
		if(sortColumnOrder.equals("Des"))
		{
			sortColumnOrder = "DESC";
		}
		else 
		{
			sortColumnOrder = "ASC";
		}
	}
	else
	{
		sortString = "";
	}
	urlSaver.trim();
%>

<core:set var="name" value="<%=name%>"/>
<core:set var="barcode" value="<%=barcode%>"/>
<core:set var="description" value="<%=description%>"/>
<core:set var="weight" value="<%=weight%>"/>
<core:set var="threshold" value="<%=threshold%>"/>
<core:set var="productId" value="<%=productId%>"/>

<core:if test="<%=needUpdate%>">
	<sql:update dataSource="${snapshot}">
		UPDATE products SET name = ?, barcode = ?, description = ?, weight = ?, threshold = ? WHERE id = ?;
		<sql:param value="${name}" />
		<sql:param value="${barcode}" />
		<sql:param value="${description}" />
		<sql:param value="${weight}" />
		<sql:param value="${threshold}" />
		<sql:param value="${productId}" />
	</sql:update>
</core:if>
  
  
<sql:query dataSource="${snapshot}" var="products">
	SELECT * FROM products WHERE id = ? LIMIT 1
	<sql:param value="${productId}" />
</sql:query>
  
<core:if test="${products.rowCount ==  0}">
	<% response.sendError(404, "Produit non trouvé"); %>
</core:if>

<core:forEach var="row" items="${products.rows}">
	<core:set var="productName" value="${row.name}" />
	<core:set var="threshold" value="${row.threshold}" />
</core:forEach>

<sql:query dataSource="${snapshot}" var="products">
		SELECT * FROM products WHERE id = ?;
		<sql:param value="${productId}" />
</sql:query>
 
<sql:query dataSource="${snapshot}" var="inventory">
	SELECT * FROM inventory INNER JOIN boxes ON (inventory.id_box = boxes.id) WHERE id_product = ? LIMIT 10;
	<sql:param value="${productId}" />
</sql:query>

<sql:query dataSource="${snapshot}" var="alerts">
	SELECT * FROM alerts INNER JOIN alert_messages ON (alerts.id_message = alert_messages.id) WHERE id_product=? LIMIT 1
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
		currentPage = 0;
	}
%>  

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<link rel="stylesheet" type="text/css" href="Intranet.css">
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.16.0/moment.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.4.0/Chart.js"></script>
	<script src="js/graph.js"></script>
	<script src="js/util.js"></script>
	<title>Soprema - Produit <core:out value="${productName}"/></title>
	<script>
		var productId = <core:out value="${productId}"/>
		var productName = "<core:out value="${productName}"/>"
		stockDataset.label = productName;
		threshold = "<core:out value="${threshold}"/>"
		
		jQuery(document).ready(function($) {
			updateGraph();
		});
	</script>
</head>
<body>
<div class="container-fluid">
	<div>
		<jsp:include page="Header.jsp" />
	</div>
	<core:if test="${alerts.rowCount > 0}"> 
		<div id="alerts" class="alert alert-warning"> 
      		<core:forEach var="row" items="${alerts.rows}">
				<core:set var="message" value="${row.message}" />
				<core:set var="message" value="${fn:replace(message,\"{productName}\", productName)}" />
				<core:out value="${message}" />
			</core:forEach>
		</div>
	</core:if>
	<h1><core:out value="${productName}"/></h1>
		<core:forEach var="row" items="${products.rows}">
			<form action="?item=${productId}" method="Post">
				<label for="name">Name: </label>
				<input type="text" id="name" name="name" value="${row.name}">
				<label for="barcode">Code barre: </label>
				<input type="text" id="barcode" name="barcode" value="${row.barcode}">
				<label for="description">Description: </label>
				<input type="text" id="description" name="description" value="${row.description}">
				<label for="weight">Poids (kg): </label>
				<input type="text" id="weight" name="weight" value="${row.weight}">
				<label for="threshold">Seuil d'alerte: </label>
				<input type="text" id="threshold" name="threshold" value="${row.threshold}">
			<input type="submit" value="Modifier"/>
		</form> 
	</core:forEach>	
	<h2>Boîtes</h2>
		<table class="table">
			<thead>
			<tr>
			 	<th>Quantité</th>
			 	<th>Poids</th>
			 	<th>Taille</th>
			 	<th>Date de création</th>
			 	<th>Date de modification</th>
				<th>Code barre</th>
			</tr>
			</thead>
			<tbody>
			<core:forEach var="row" items="${inventory.rows}" begin="<%=currentPage*perPage%>" end="<%=perPage*(currentPage+1)-1 %>">
				<tr class='clickable-row table-hover' data-href="BoxDetail.jsp?box=${row.id}">
				 	<td><core:out value="${row.quantity}"/></td>
				 	<td><core:out value="${row.weight}"/></td>
				 	<td><core:out value="${row.size}"/></td>
				 	<td><core:out value="${row.creation_date}"/></td>
				 	<td><core:out value="${row.last_modified}"/></td>
					<td><core:out value="${row.barcode}"/></td>
				</tr>
			</core:forEach>
			</tbody>
		</table>
		<div id="stockchart" class="hidden">
	    <button class="list-group-item list-group-item-info" data-toggle="collapse" data-target="#collapseChart">
			Graphique d'inventaire
		</button>
     	<!--  <div class="panel-collapse collapse " id="collapseChart"> -->
     	<div>
			<canvas id="canvas"></canvas>
		</div>
	</div>
	</div>
	</body>
</html>