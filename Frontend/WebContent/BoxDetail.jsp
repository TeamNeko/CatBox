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
	
	String urlSaver = "&box="+boxId;
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

<sql:query dataSource="${snapshot}" var="inventory">
		SELECT * FROM inventory INNER JOIN products ON (inventory.id_product = products.id) WHERE id_box = ? LIMIT 10;
		<sql:param value="${boxId}" />
</sql:query>

<sql:query dataSource="${snapshot}" var="box">
		SELECT * FROM boxes WHERE id = ?;
		<sql:param value="${boxId}" />
</sql:query>

<core:set var="total" value="${fn:length(inventory.rows)}"/>
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
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Soprema - Boite <core:out value="${boxId}"/></title>
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
	<div>
	 	<form>
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
			<core:forEach var="row" items="${inventory.rows}" begin="<%=currentPage*perPage%>" end="<%=perPage*(currentPage+1)-1 %>">
				<tr class="clickable-row"  data-href="ProductDetail.jsp?item=${row.id}">
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