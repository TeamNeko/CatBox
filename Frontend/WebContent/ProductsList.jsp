<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<% 
	String urlSaver = ""; 
	String keyWord = request.getParameter("search");
	if (keyWord != null)
	{
		if(!keyWord.isEmpty())
		{
			urlSaver += "&search=" + keyWord;
		}
	}
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
	String addName="";
	String addBarcode="";
	String addDescription="";
	float addWeight=0;
	boolean insertTrue = false;
	try
	{
		addName = request.getParameter("addName");
		addBarcode = request.getParameter("addBarcode");
		addDescription = request.getParameter("addDescription");
		addWeight = Float.parseFloat(request.getParameter("addWeight"));
		if(!addName.isEmpty() && !addBarcode.isEmpty())
		{
			insertTrue = true;
		}
	}
	catch(NullPointerException e)
	{
		e.printStackTrace();
	}
%>

<core:set var="keyWord" value="<%=keyWord %>"/>
<core:set var="sortColumnOrder" value="<%=sortColumnOrder %>"/>
<core:set var="sortColumnName" value="<%=sortColumnName %>"/>

<sql:setDataSource var="snapshot" driver="org.postgresql.Driver"
     url="jdbc:postgresql://elmer.db.elephantsql.com:5432/jmtntlek"
     user="jmtntlek"  password="vaYxsY1WBNr5gYMMd-74kLrc98gqNhqI"/>

<sql:query dataSource="${snapshot}" var="products">
	SELECT * FROM products 
	<core:if test="${not empty keyWord}">
		WHERE name = ?
		<sql:param value="${keyWord}" />
	</core:if> 
	ORDER BY "${sortColumnName}" ${sortColumnOrder} LIMIT 50;
</sql:query>

<sql:query dataSource="${snapshot}" var="alerts">
	  SELECT * FROM alerts INNER JOIN alert_messages ON (alerts.id_message = alert_messages.id) INNER JOIN products ON (alerts.id_product = products.id)
</sql:query>
<core:set var="total" value="${fn:length(products.rows)}"/>

<sql:query dataSource="${snapshot}" var="user">
	SELECT * FROM users;
</sql:query>

<core:set var="addName" value="<%=addName%>"/>
<core:set var="addBarcode" value="<%=addBarcode%>"/>
<core:set var="addDescription" value="<%=addDescription%>"/>
<core:set var="addWeight" value="<%=addWeight%>"/>

<core:if test="<%=insertTrue%>">
	<sql:update dataSource="${snapshot}">
		INSERT INTO products (name,barcode,description,weight) VALUES (?, ?, ?, ?);
		<sql:param value="${addName}" />
		<sql:param value="${addBarcode}" />
		<sql:param value="${addDescription}" />
		<sql:param value="${addWeight}" />
	</sql:update>
</core:if>

<% 
	int perPage = 5;
	int totalRecords = 0, totalPage = 0, lastPageItem = 0; 
	int currentPage = 0;
	try 
	{
		totalRecords =  (int)pageContext.getAttribute("total");
		totalPage = totalRecords/perPage-1;
		lastPageItem = totalRecords%perPage;
		currentPage = Integer.parseInt(request.getParameter("start"));
		if(currentPage > totalPage)
		{
			if(lastPageItem == 0)
				currentPage = totalPage;
			else
				currentPage = totalPage + 1;
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
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
	<script>
	// Rend les lignes cliquables
		jQuery(document).ready(function($) {
		    $(".clickable-row").click(function() {
		        window.location = $(this).data("href");
		    });
		});
	</script>
	<title>Soprema - Liste de produits</title>
</head>
<body>
<div class="container-fluid">
	<div>
		<jsp:include page="Header.jsp" />
	</div> 
	<core:if test="${alerts.rowCount > 0}">
		<div id="alerts" class="list-group">
 	    	<button class="list-group-item list-group-item-warning" data-toggle="collapse" data-target="#collapseAlerts">
				Afficher/Cacher les alertes (<core:out value="${alerts.rowCount}" />)
			</button>
      		<div class="panel-collapse collapse " id="collapseAlerts">
				<core:forEach var="row" items="${alerts.rows}">
					<core:set var="message" value="${row.message}" />
					<core:set var="message" value="${fn:replace(message,\"{productName}\", row.name)}" />
					
					<p class="list-group-item list-group-item-warning">
						<b><core:out value="${message}" /></b><br/>
						at <core:out value="${row.time}" />
					</p>
				</core:forEach>
			</div>
		</div>
	</core:if>
	<div>
	 	<form>
			<div class="form-group">
				<label for="search">Recherche: </label>
				<input type="text" id="search" name="search">
				<button type="submit" class="glyphicon glyphicon-search"></button>
			</div>
			<div class="form-group">
				<label for="sort">Ordre d'affichage: </label>
				<select name="sort" id="sort" class="form-control">
					<option value="Asc-id" <%=sortString.equals("Asc-id") ? "selected" : ""%>>Ordre croissant d'ID</option>
					<option value="Des-id" <%=sortString.equals("Des-id")? "selected" : ""%>>Ordre dï¿½croissant d'ID</option>
					<option value="Asc-barcode" <%=sortString.equals("Asc-barcode") ? "selected" : ""%>>Ordre croissant de code barre</option>
					<option value="Des-barcode" <%=sortString.equals("Des-barcode") ? "selected" : ""%>>Ordre dï¿½croissant de code barre</option>
					<option value="Asc-name" <%=sortString.equals("Asc-name") ? "selected" : ""%>>Ordre croissant de nom de produit</option>
					<option value="Des-name" <%=sortString.equals("Des-name")? "selected" : ""%>>Ordre dï¿½croissant de nom de produit</option>
					<option value="Asc-weight" <%=sortString.equals("Asc-weight") ? "selected" : ""%>>Ordre croissant de poids</option>
					<option value="Des-weight" <%=sortString.equals("Des-weight") ? "selected" : ""%>>Ordre dï¿½croissant de poids</option>
					<option value="Asc-date_added" <%=sortString.equals("Asc-date_added") ? "selected" : ""%>>Ordre croissant de crï¿½ation</option>
					<option value="Des-date_added" <%=sortString.equals("Des-date_added") ? "selected" : ""%>>Ordre dï¿½croissant de crï¿½ation</option>
					<option value="Asc-date_retired" <%=sortString.equals("Asc-date_retired") ? "selected" : ""%>>Ordre croissant de retrait</option>
					<option value="Des-date_retired" <%=sortString.equals("Des-date_retired") ? "selected" : ""%>>Ordre dï¿½croissant de retrait</option>
				</select>
			</div>
		</form>
	</div>
	<div>
	<table class="table">
		<thead>
			<tr>
				<th>Name</th>
				<th>Barcode</th>
				<th>Description</th>
				<th>Poids (kg)</th>
				<th>Date d'ajout</th>
				<th>Date de retrait</th>
			</tr>
		</thead>
		<tbody>
			<core:forEach var="row" items="${products.rows}" begin="<%=currentPage*perPage%>" end="<%=perPage*(currentPage+1)-1 %>">
				<tr class='clickable-row table-hover' data-href="ProductDetail.jsp?item=${row.id}">
					<td><core:out value="${row.name}"/></td>
					<td><core:out value="${row.barcode}"/></td>
					<td><core:out value="${row.description}"/></td>
					<td><core:out value="${row.weight}"/></td>
					<td><core:out value="${row.date_added}"/></td>
					<td><core:out value="${row.date_retired}"/></td>
				</tr>
			</core:forEach>
			</tbody>
		</table>
		<a href="?start=<%=(currentPage-1)+urlSaver%>">Précédent</a>
		<%=currentPage*perPage+1 %> - <%=perPage*(currentPage+1) %>
		<a href="?start=<%=(currentPage+1)+urlSaver%>">Suivant</a><br/>
	</div>
	</div>
	<button type="button" class="btn btn-info btn-md pull-right" data-toggle="modal" data-target="#addWindow">Ajouter un produit</button>
	<div id="addWindow" class="modal fade" role="dialog">
    <div class="modal-dialog">
	    <div class="modal-content">
	      	<div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal">&times;</button>
	        <h4 class="modal-title">Ajouter un produit</h4>
	     	</div>
	    	<div class="modal-body">
		    	<form role="form">
			    	<div class="form-group">
						<label for="addName">Nom: </label>
						<input id="addName" class="form-control" type="text" name="addName">
						
						<label for="addBarcode">Code bar: </label>
						<input id="addBarcode" class="form-control" type="text" name="addBarcode">
						
						<label for="addDescription">Description: </label>
						<input id="addDescription" class="form-control" type="text" name="addDescription">
						
						<label for="addWeight">Poids: </label>
						<input id="addWeight" class="form-control" type="text" name="addWeight">
					</div>
					<input type="submit" class="btn btn-success" value="Ajouter"/>
				</form>
	      	</div>
	      	<div class="modal-footer">
	        	<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
	     	 </div>
    	</div>
  	</div>
</div>
</body>
</html>