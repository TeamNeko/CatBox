<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Code barre</title>
<script src="https://cdn.jsdelivr.net/jsbarcode/3.5.1/barcodes/JsBarcode.ean-upc.min.js"></script>
<script type="text/javascript">
function generate() {
	var value = document.getElementById("value").value;
	if(value.length == 11)
		JsBarcode("#barcode", document.getElementById("value").value, {format: "UPC"});
}

</script>
</head>
<body>
<div class="container-fluid">
	<div>
		<jsp:include page="Header.jsp" />
	</div>
	<div>
		<h3> Entrez un code barre à générer </h3>
		<input type="text" id="value" oninput="generate()"/>
		<canvas id="barcode"></canvas>
	</div>
</div>
</body>
</html>