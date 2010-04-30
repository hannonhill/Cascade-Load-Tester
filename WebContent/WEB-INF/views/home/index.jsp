<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
	<head>
		<link href="style.css" rel="stylesheet" type="text/css">
		<script type="text/javascript" src="http://code.jquery.com/jquery-1.4.2.min.js"></script>
		<script type="text/javascript">
			$(document).ready(function () {
				$("input").focus(function () {
					switch(this.name)
					{
					case "host":
						$("#feedback").text("The hostname of the server running Cascade Server.");
						break;
					case "port":
						$("#feedback").text("Port number cascade server is running on.");
						break;
					case "username":
						$("#feedback").text("This is the username for the user that will create test pages and test users in Cascade Server. This user must be an administrator.");
						break;
					case "password":
						$("#feedback").text("Password for the admin user specified above.");
						break;
					case "pageID":
						$("#feedback").text("Asset ID of the page in Cascade that will be used for the test.  If the clone asset option is chosen, clones of this page will be created and tested against.");
						break;
					case "groups":
						$("#feedback").text("Cascade Server user groups for the test users to be assigned to.");
						break;
					case "numUsers" :
						$("#feedback").text("Number of test users to create.  The more users, the heavier the load.");
						break;
					default:
						$("#feedback").clear();	
					}
				});
			});
		</script>
	</head>

	<body>
		<div id="header">
			<img src="logo.png" />
			Cascade Server Load Tester
		</div>
		<div id="content">
			<div id="form">
				<form:form commandName="testConfiguration">
					<label for="host">Cascade Server Host:</label><br/>
					<input type="text" name="host" /><br/><br/>
					<label for="port">Cascade Server Port:</label><br/>
					<input type="text" name="port" /><br/><br/>
					<label for="username">Cascade Server Admin Username:</label><br/>
					<input type="text" name="username" /><br/><br/>
					<label for="password">Cascade Server Admin Password:</label><br/>
					<input type="password" name="password" /><br/><br/>
					<label for="pageID">ID of Page:</label><br/>
					<input type="text" name="pageID"/><br/><br/>
					<label for="groups">Groups to Add New Users To (comma seperated):</label><br/>
					<form:input path="groups"/><br/><br/>
					<label for="numUsers">Number of Test User to Simulate:</label><br/>
					<input type="text" name="numUsers"/><br/><br/>
					<label for="cloneAsset">Clone asset for each user?</label>
					<form:checkbox path="cloneAsset" id="cloneAsset"/><br/><br/>
					<input type="submit" value="Run Test"/>
				</form:form>
			</div>
			<div id="feedback">
			</div>
		</div>
	</body>
</html>