<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>ConfirmPassword</title>
<link rel="stylesheet" type="text/css"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css" />
<script
	src="https://cdn.jsdelivr.net/npm/jquery@3.6.0/dist/jquery.slim.min.js"></script>
<script
	src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js"></script>
<style>
body {
	background-image:
		url(
"https://media.istockphoto.com/id/1283633817/photo/communication-network-concept-in-cyberspace-abstract-background.jpg?b=1&s=170667a&w=0&k=20&c=m2xGn1TZQFdkjMv0GYYxL11Co9IO7RTPP8dBu23QBTo=");
	background-repeat: no-repeat;
	background-attachment: fixed;
	background-size: 100% 100%;
}

td {
	color: white;
}

h1 {
	text-align: center;
}

a {
	color: white;
}
</style>
<script type="text/javascript">


</script>
</head>
<body>
<nav class="navbar navbar-expand-lg-navbar-Light bg-dark">
		<div class="continer-fluid">
			<a class="navbar-brand" href="#"> <img alt=""
				src="https://www.x-workz.in/Logo.png" width="80" height="50"
				class="d-inline-block align text-top">
			</a>
		</div>
		<li class="nav-item-active"><a style="color:white" class="nav-link" href="index.jsp">Home</a></li>
	</nav>

	<form action="confirm" method="post">
		<div align="center">
			<div class="container">
				<div class="row justify-content-md-center">
					<div class="col col-sm-5">
						<h1 style="color: red">ConfirmPassword</h1>
						<h3 style="color: green">${msg}</h3>
						<table style="with: 50%">

							<tr>
								<td>Email</td>
								<td><input type="email" id="emai" placeholder="Enter Email"
									name="email" /></td>
							</tr>
							<tr>
								<td>Password</td>
								<td><input type="text" id="pass" placeholder="NewPassword"
									name="password" /></td>
							</tr>
							<tr>
								<td>OTP</td>
								<td><input type="text" id="otp" placeholder="otp" name="otp" /></td>
							</tr>

						</table>
						<input type="Submit" name="Signin.jsp" value="Submit" />
					</div>
				</div>
			</div>
		</div>

	</form>

</body>
</html>