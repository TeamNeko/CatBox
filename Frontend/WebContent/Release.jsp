<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="frontend.catbox.teamneko.org.Hexadecimal" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>RPi Hexadecimal LED Test</title>
<script type="text/javascript">
	function desactiverBoutons(etat){
		var boutons = document.getElementsByTagName("button");
		
		for(var i = 0; i < boutons.length; i++) {
			if(etat == "false") {
					boutons[i].removeAttribute("disabled");
			}
			else {
					boutons[i].setAttribute("disabled", "");
			}
		}
	}

	function envoyer(valeur) {
		document.getElementById("etat").innerHTML = "En attente de confirmation";
		desactiverBoutons("true");
		
		var params = "valeur=" + valeur;
		var xhttp = new XMLHttpRequest();
		
		xhttp.onreadystatechange = function () {
			if(this.readyState == 4 && this.status == 200) {
				document.getElementById("etat").innerHTML = this.responseText;
				desactiverBoutons("false");
			}
		}
		
		xhttp.open("POST", "${pageContext.request.contextPath}/Hexadecimal", true);
		
		xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
		xhttp.setRequestHeader("Content-length", params.length);
		xhttp.setRequestHeader("Connection", "close");
		
		xhttp.send(params);
	}
</script>
</head>
<body>
		<%
		  for(int i=0; i<16; i++) {
		    out.print("<button onClick=\"envoyer('" + Integer.toHexString(i) + "')\"/>" + Integer.toHexString(i) + "</button>");	    
		  }
		%>
		<br/>
		<p>État de la réception: <span id="etat"><%= Hexadecimal.getEtat() %></span></p>
		<br/>
		<br/>
	 	<jsp:include page="footer.jsp" />
</body> 
</html>