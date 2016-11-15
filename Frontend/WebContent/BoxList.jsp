<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
     
<% 
	String barcode = "";
	String urlSaver = ""; 
	String keyWord = request.getParameter("search");
	if (keyWord != null)
	{
		if(!keyWord.isEmpty())
		{
			urlSaver += "&search=" + keyWord;
		}
	}
	urlSaver += "&search=" + barcode;
	String sortString = request.getParameter("sort");
	String sortColumnOrder = "ASC";
	String sortColumnName = "id";
	
	if(sortString != null)
	{
		String[] sortArguments = sortString.split("-");
		sortColumnOrder = sortArguments[0];
		sortColumnName = sortArguments[1];
		urlSaver += "&sort=" + sortColumnOrder + "-" + sortColumnName;
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

<core:set var="keyWord" value="<%=barcode%>"/>
<core:set var="sortColumnOrder" value="<%=sortColumnOrder %>"/>
<core:set var="sortColumnName" value="<%=sortColumnName %>"/>

<sql:setDataSource var="snapshot" driver="org.postgresql.Driver"
     url="jdbc:postgresql://elmer.db.elephantsql.com:5432/jmtntlek"
     user="jmtntlek"  password="vaYxsY1WBNr5gYMMd-74kLrc98gqNhqI"/>
     
<core:if test="${empty keyWord}">
	<sql:query dataSource="${snapshot}" var="box"> 
		SELECT * FROM boxes ORDER BY "${sortColumnName}" ${sortColumnOrder} LIMIT 50;
	</sql:query>
</core:if>
<core:if test="${not empty keyWord}">
	<sql:query dataSource="${snapshot}" var="box">
		SELECT * FROM boxes WHERE barcode = ? ORDER BY "${sortColumnName}" ${sortColumnOrder} LIMIT 50;
		<sql:param value="${keyWord}" />
	</sql:query>
</core:if>
    
<core:set var="total" value="${fn:length(box.rows)}"/>
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
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Soprema - Liste de boite</title>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
	<!-- Latest compiled and minified CSS -->
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
	
	<!-- Latest compiled and minified JavaScript -->
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
	<script type="text/javascript">
	// Rend les lignes cliquables
		jQuery(document).ready(function($) {
		    $(".clickable-row").click(function() {
		        window.location = $(this).data("href");
		    });
		});
	</script>
</head>
<body>
<div class="container-fluid">
	<div>
		<jsp:include page="Header.jsp" />
	</div>
	<div class="search">
	 	<form>
			<div class="form-group">
				<label for="search">Recherche: </label>
				<input type="text" id="search" name="search">
				<button type="submit" class="glyphicon glyphicon-search"></button>
			</div>
			<div class="form-group">
				<label for="sort">Ordre d'affichage: </label> 
				<select name="sort" class="form-control">
					<option value="Asc-barcode" <%=sortString.equals("Asc-barcode") ? "selected" : ""%>>Ordre croissant de code barre</option>
					<option value="Des-barcode" <%=sortString.equals("Des-barcode")? "selected" : ""%>>Ordre décroissant de code barre</option>
					<option value="Asc-weight" <%=sortString.equals("Asc-weight") ? "selected" : ""%>>Ordre croissant de poids</option>
					<option value="Des-weight" <%=sortString.equals("Des-weight") ? "selected" : ""%>>Ordre décroissant de poids</option>
					<option value="Asc-size" <%=sortString.equals("Asc-size") ? "selected" : ""%>>Ordre croissant de taille</option>
					<option value="Des-size" <%=sortString.equals("Des-size") ? "selected" : ""%>>Ordre décroissant de taille</option>
					<option value="Asc-creation_date" <%=sortString.equals("Asc-creation_date") ? "selected" : ""%>>Ordre croissant de création</option>
					<option value="Des-creation_date" <%=sortString.equals("Des-creation_date") ? "selected" : ""%>>Ordre décroissant de création</option>
					<option value="Asc-last_modified" <%=sortString.equals("Asc-last_modified") ? "selected" : ""%>>Ordre croissant de retrait</option>
					<option value="Des-last_modified" <%=sortString.equals("Des-last_modified") ? "selected" : ""%>>Ordre décroissant de retrait</option>
				</select>
			</div>
		</form>
	</div>
	<div>	
		<table class="table table-striped>">
		<thead>
			<tr>
				<th>Code barre</th>
				<th>Poids</th>
				<th>Taille</th>
				<th>Date de création</th>
				<th>Date de modification</th>
			</tr>
		</thead>
		<tbody>
			<core:set var="total" scope="session" value="${fn:length(box.rows)}"/>
			<core:set var="perPage" scope="session"  value="10"/>
			<core:set var="pageStart" value="${param.start}"/>
			<core:if test="${empty pageStart or pageStart < 0}">
			    <core:set var="pageStart" value="0"/>
			</core:if>
			<core:if test="${total <= pageStart}">
			    <core:set var="pageStart" value="${total}"/>
			</core:if>			
			<core:forEach var="row" items="${box.rows}" begin="<%=currentPage*perPage%>" end="<%=perPage*(currentPage+1)-1 %>">
				<tr class="clickable-row"  data-href="BoxDetail.jsp?box=${row.id}">
					<td><core:out value="${row.barcode}"/></td>
					<td><core:out value="${row.weight}"/></td>
					<td><core:out value="${row.size}"/></td>
					<td><core:out value="${row.creation_date}"/></td>
					<td><core:out value="${row.last_modified}"/></td>
				</tr>
			</core:forEach>
		</tbody>
		</table>
		<a href="?start=<%=(currentPage-1)+urlSaver%>">Précédent</a>
		<%=currentPage*perPage+1 %> - <%=perPage*(currentPage+1) %>
		<a href="?start=<%=(currentPage+1)+urlSaver%>">Suivant</a><br/>
	</div>
</div>
</body>
</html>