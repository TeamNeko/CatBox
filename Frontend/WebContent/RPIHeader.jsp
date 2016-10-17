<head>
	<link rel="stylesheet" type="text/css" href="Intranet.css">
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<script src="js/jquery-3.1.1.min.js"></script>
	<title>Soprema - Liste de produits</title>
</head>
<body>
	<div id	="logo">
		<img src="img/logo.png" alt="Soprema logo" height="20px" width="40px">
	</div>
	<header>
	  <button class="hamburger" id="opener">&#9776;</button>
	  <button class="hamburger" id="closer">&#9776;</button>
	</header>
	
	<script type="text/javascript">
	$(document).ready(function () {
		$( "#closer" ).hide();
		$( ".menu" ).hide();
		$( "#opener" ).click(function() {
			$( ".menu" ).slideToggle( "slow", function() {
				$( "#opener" ).hide();
				$( "#closer" ).show();
			});
		});

		$( "#closer" ).click(function() {
			$( ".menu" ).slideToggle( "slow", function() {
				$( "#closer" ).hide();
				$( "#opener" ).show();
			});
		});
	});
	</script>
	
	<div class="menu">
		 <ul>
		   <a href="#"><li>TEST ONE</li></a>
		   <a href="#"><li>TEST TWO</li></a>
		 </ul>
	</div> 
</body>

