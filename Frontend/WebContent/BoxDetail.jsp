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
	String barcode = "";
	float weight = 0;
	boolean needUpdate = false;
	try{
		boxId = Integer.parseInt(request.getParameter("box"));
		barcode = request.getParameter("barcode");
		weight = Float.parseFloat(request.getParameter("weight"));
		size = Integer.parseInt(request.getParameter("size"));
	}
	catch(NumberFormatException e)
	{
		boxId = -1;
		size = 0;
		weight = 0;
	}
	catch(NullPointerException e)
	{ 
		barcode = "";
		size = 0;
		weight = 0;
	}
	if(weight != 0 || size != 0 || barcode != "")
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
<core:set var="barcode" value="<%=barcode%>"/>
<core:set var="weight" value="<%=weight%>"/>
<core:set var="size" value="<%=size%>"/>

<core:if test="<%=needUpdate %>">
	<sql:update dataSource="${snapshot}">
		UPDATE boxes SET barcode = ?, weight = ?, size = ? WHERE id = ?;
		<sql:param value="${barcode}" />
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
		currentPage = 0;
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
				<label for="barcode">Code Barre: </label>
				<input type="text" id="barcode" name="barcode" value="${row.barcode}">
				<label for="barcode">Poids: </label>
				<input type="text" id="weight" name="weight" value="${row.weight}">
				<label for="barcode">Taille: </label>
				<input type="text" id="size" name="size" value="${row.size}">
			<input type="submit" value="Modifier"/>
		</form> 
		</core:forEach>  
	</core:if> 
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
					<td><core:out value="${row.quantity}"/></td>
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