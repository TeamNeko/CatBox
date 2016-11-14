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
<core:set var="total" value="${fn:length(products.rows)}"/>

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
	<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">

<!-- Latest compiled and minified JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
	<title>Soprema - Liste de produits</title>
</head>
<body>
<div class="container-fluid">
	<div>
		<jsp:include page="Header.jsp" />
	</div>
	<div>
	 	<form class="form-horizontal">
			<div class="form-group">
				<label for="search">Recherche: </label>
				<input type="text" id="search" name="search">
				<button type="submit" class="glyphicon glyphicon-search"></button>
			</div>
			<div class="form-group">
				<label for="sort">Ordre d'affichage: </label>
				<select name="sort" id="sort" class="form-control">
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
			</div>
		</form>
	</div>
	<div>
		<table class="table table-striped>">
		<thead>
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
		</thead>
		<tbody>
			<core:forEach var="row" items="${products.rows}" begin="<%=currentPage*perPage%>" end="<%=perPage*(currentPage+1)-1 %>">
				<tr>
					<td><a href="ProductDetail.jsp?item=${row.id}"><core:out value="${row.id}"/></a></td>
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
		<a href="?start=<%=(currentPage-1)+urlSaver%>">Previous</a>
		<%=currentPage*perPage+1 %> - <%=perPage*(currentPage+1) %>
		<a href="?start=<%=(currentPage+1)+urlSaver%>">Next</a><br/>
	</div>
	</div>
</body>
</html>