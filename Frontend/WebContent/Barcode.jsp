<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Code barre</title>
<script src="https://cdn.jsdelivr.net/jsbarcode/3.3.7/JsBarcode.all.min.js"></script>
<script type="text/javascript">
function generate() {
	var value = document.getElementById("value").value;
	var type = getRadioChecked("barcodeOpt");
	document.getElementById("printBtn").disabled = true;
	switch(type) {
	    case "UPC":
	    	if(value.length == 11){
	        	JsBarcode("#barcode", document.getElementById("value").value, {format: type});
	        	document.getElementById("printBtn").disabled = false;
			}
	        break;
	    case "ITF":
	    	if((value.length%2 == 0) && value.length <= 20){
	        	JsBarcode("#barcode", document.getElementById("value").value, {format: type});
	        	document.getElementById("printBtn").disabled = false;
			}
	        break;
	    case "CODE128":
	    	if(value.length <= 20){
	        	JsBarcode("#barcode", document.getElementById("value").value, {format: type});
	        	document.getElementById("printBtn").disabled = false;
			}
	    	break;
	    default:
	        break;
	}
}
function getRadioChecked(radioGrp){
    var elements = document.getElementsByName(radioGrp);
    for (var i = 0, l = elements.length; i < l; i++)
    {
        if (elements[i].checked)
        {
            return elements[i].value;
        }
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
      <label class="radio-inline"><input type="radio" name="barcodeOpt" value="UPC" checked="true">UPC</label>
      <label class="radio-inline"><input type="radio" name="barcodeOpt" value="CODE128">Code 128</label>
      <label class="radio-inline"><input type="radio" name="barcodeOpt" value="ITF">ITF</label>
    </div>
	<div>
		<h3> Entrez un code barre à générer </h3>
		<input type="text" id="value" oninput="generate()"/>
		 </br>
		<canvas id="barcode"></canvas>
	</div>
    <input type="button" id="printBtn" class="btn btn-success" onclick="print()" disabled="true" value="Imprimer le code bar" />
</div>
</body>
</html>