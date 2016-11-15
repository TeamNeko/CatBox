<head>
	<link rel="stylesheet" type="text/css" href="Intranet.css">
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">

<!-- Latest compiled and minified JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
	
	<title>Soprema</title>
</head>
<body>
	<nav class="navbar navbar-default">
	<div class="container-fluid" >
		<div class="navbar-header">
			<img src="img/logo.png" class="img-responsive" alt="Soprema logo" height="60px" width="120px">
		</div>
		<div>
			<ul class="nav navbar-nav">
				<li <%=request.getRequestURI().equals("/Frontend/ProductsList.jsp")  ? "class='active'" : ""  %> ><a href="ProductsList.jsp">Liste des produits</a></li>
				<li <%=request.getRequestURI().equals("/Frontend/BoxList.jsp")   	 ? "class='active'" : ""  %> ><a href="BoxList.jsp">Liste des boîtes</a></li>
				<li <%=request.getRequestURI().equals("/Frontend/Barcode.jsp")   	 ? "class='active'" : ""  %> ><a href="Barcode.jsp">Générer un code barre</a></li>
				<li <%=request.getRequestURI().equals("/Frontend/CreateUser.jsp")	 ? "class='active'" : ""  %> ><a href="CreateUser.jsp">Créer un utilisateur</a></li>
			</ul>
		</div>
	</div>
	</nav>
</body>

