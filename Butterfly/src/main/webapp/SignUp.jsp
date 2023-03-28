<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Sign up</title>

<link rel="stylesheet" type="text/css"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css" />
<script
	src="https://cdn.jsdelivr.net/npm/jquery@3.6.0/dist/jquery.slim.min.js"></script>
<script
	src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js"></script>
<script src="js/validation.js"></script>
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

sign {
	margin-bottom: 200px;
}

a {
	color: white;
}
footer {
	position: fixed;
	left: 0;
	bottom: 0;
	width: 100%;
	height:7%;
	background-color: #34495E;
	color: black;
	text-align: center;
}
</style>
</head>
<body>
	<nav class="navbar navbar-expand-lg-navbar-Light bg-dark">
		<div class="continer-fluid">
			<a class="navbar-brand" href="#"> <img alt=""
				src="https://www.x-workz.in/Logo.png" width="80" height="50"
				class="d-inline-block align text-top">
			</a>
		</div>
		<li class="nav-item-active"><a class="nav-link" href="index.jsp">Home</a></li>
	</nav>




	<form action="singUp" onsubmit="return validate()" method="post">
		<div align="center">
			<div class="sign">
				<div class="container">
					<div class="row justify-content-md-center">
						<div class="col col-sm-5">

							<h1 style="color: red">SignUp</h1>
							<h3 style="color: red">${msg}</h3>
							<h3 style="color: green">${msgs}</h3>
							<table style="with: 50%">
								<tr>
									<td>First Name</td>
									<td><input type="text" id="nam" placeholder="Enter Name"
										name="name" />
										<span id="nameError" style="color: red"></span></td>
								</tr>
								
								<tr>
									<td>Last Name</td>
									<td><input type="text" id="lnam" placeholder="lastName"
										name="lastName" />
										<span id="lnameError" style="color: red"></span></td>
										
								</tr>
								<tr>
									<td>Email</td>
									<td><input type="email" id="ema" placeholder="Enter Email"
										name="email" />
										<span id="email" style="color: red"></span></td>
								</tr>
								<tr>
									<td>MobileNo</td>
									<td><input type="text" id="mobile"
										placeholder="Enter mobileNo" name="mobileNo" />
										<span id="mobileError" style="color: red"></span></td>
								</tr>
								<tr>
									<td>Password</td>
									<td><input type="password" id="pass"
										placeholder="Enter Password" name="password" />
										<span id="passError" style="color: red"></span></td>
								</tr>
							</table>
							<input align="left" type="submit" value="Register" />
							<button type="submit" value=""><a  align="left" href="editRegistrationPage.jsp">Edit Registration page</a></button>
						</div>
						
					</div>
				</div>
			</div>
		</div>


	</form>
	<footer>
		<p style="font-size:small; ;padding-top: 0.7%;color: white;">
			© Created By : Suhas Banakar &nbsp; <a
				href="#" style="font-size: small;color: blue;"><u> suhasnb.xworkz@gmail.com</u></a>
		</p>
	</footer>
</body>
</html>