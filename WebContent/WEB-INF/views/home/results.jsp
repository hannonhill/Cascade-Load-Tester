<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title></title>
<link href="style.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="http://code.jquery.com/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="./js/jquery.ba-dotimeout.min.js"></script>
<script type="text/javascript">
	function pollMessages() {
		$.get("./CheckMessages", function(data) {
			$('#results').append(data);
		});		
	}

	$(document).ready(
			function(){
				$.doTimeout(500, function(){
					pollMessages();
					return true;
					});
			});
</script>
</head>
<body>
<div id="header">
			<img src="logo.png" />
			Cascade Server Load Tester Results
		</div>
<div id="results"></div>
</body>
</html>