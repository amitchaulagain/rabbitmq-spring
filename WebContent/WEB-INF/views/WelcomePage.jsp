<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<link href="<c:url value="/allCss/bootstrap.css" />" rel="stylesheet">


<script type="text/javascript" src="<c:url value="/allJs/jquery.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="/allJs/bootstrap.min.js"/>"></script>

</head>
<body>
	<script type="text/javascript">
	function createUser() {

		$.ajax({
			headers: { 
		        'Accept': 'application/json',
		        'Content-Type': 'application/json' 
		    },
			type : 'POST',
			url : "http://localhost:8085/HeroApp/createUser",
			dataType : "json",
			data:userToJson(),

			success : function(data) {
				alert(data);
				renderAllUsers(data);
			}
		});

	}
	
	function findAllUsers(){
		$.ajax({
			headers: { 
		        'Accept': 'application/json',
		        'Content-Type': 'application/json' 
		    },
			type : 'GET',
			url : "http://localhost:8085/HeroApp/allusers",
			dataType : "json" ,

			success : function(data) {
				alert(data);
				renderAllUsers(data);
			}
		});
	}
	
	function userToJson() {

		var username = $('#username').val();
		var password = $('#password').val();
		var isEnabled=true;
		return JSON.stringify({
			"username" : username,
			"password" : password
			 

		});
	}
	// :::::::::::::::::::::::::::::::::::::::::::::::::::::::RENDER ALL
 
	function renderAllUsers( alldata) {
	 
	}

	// :::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::DOCUMENT>READY>FUNCTION
	$(document).ready(function() {
		findAllUsers();
		$("#submit").on("click" , function(){
			 
			createUser();
			
		});

	});
</script>

	<!-- Navigation -->
	<!-- Navigation -->

	<!-- /.row -->

	<div class="row">
		<div class="col-lg-6">

			 

				<div class="form-group">
					<label>Username</label> <input id ="username" class="form-control">
					<p class="help-block">Example block-level help text here.</p>
				</div>

				<div class="form-group">
					<label>Password</label> <input id ="password" class="form-control"
						placeholder="Enter password">
				</div>




				<div class="form-group">
					<label>Radio Buttons</label>
					<div class="radio">
						<label> <input type="radio" name="optionsRadios"
							id="optionsRadios1" value="true" checked>Enabled
						</label>
					</div>
					<div class="radio">
						<label> <input type="radio" name="optionsRadios"
							id="optionsRadios2" value="false">Disabled
						</label>
					</div>

				</div>



				<button id ="submit"  class="btn btn-default" style="color: blue">Submit
					Button</button>



		</div>
	</div>


	Hello First Page !!!!
	<img alt="hero" src='<c:url value="/allImages/images/anno.jpg" />'>
</body>
</html>