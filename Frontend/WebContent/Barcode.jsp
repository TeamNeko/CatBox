<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script src="https://cdn.jsdelivr.net/jsbarcode/3.5.1/barcodes/JsBarcode.ean-upc.min.js"></script>
<title>Code barre</title>
<script type="text/javascript">
function generate() {
	var value = document.getElementById("value").value;
	if(value.length == 11)
	{
		JsBarcode("#barcode", document.getElementById("value").value, {format: "UPC"});
		document.getElementById("printBtn").disabled = false;
	}
	else 
	{
		document.getElementById("printBtn").disabled = true;
	}
}
function print(){

	 var canvas=document.getElementById("barcode");

     var win=window.open();
     win.document.write("<br><img src='"+canvas.toDataURL()+"'/>");
     win.print();
     win.close();
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
		</br>
		<div id="printableArea">
      		<canvas id="barcode"></canvas>
		</div>
		<input type="button" id="printBtn" class="btn btn-success" onclick="print()" disabled="true" value="Imprimer le code bar" />
	</div>
</div>
</body>
</html>