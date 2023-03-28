<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<title>Login index Page</title>
<meta charset="ISO-8859-1">
<meta name="viewport" content="width=device-width,initial-scale=1">
<link rel="stylesheet" type="text/css"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css" />
<script
	src="https://cdn.jsdelivr.net/npm/jquery@3.6.0/dist/jquery.slim.min.js"></script>
<script
	src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js"></script>
<style>
 body{
            background-image:url(
"https://media.istockphoto.com/id/1283633817/photo/communication-network-concept-in-cyberspace-abstract-background.jpg?b=1&s=170667a&w=0&k=20&c=m2xGn1TZQFdkjMv0GYYxL11Co9IO7RTPP8dBu23QBTo=");
            background-repeat: no-repeat;
            background-attachment: fixed; 
            background-size: 100% 100%;
        }
        a{
        color:white;
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



		<a class="active" href="index.jsp">Home</a> <a href="Signin.jsp">Signin</a>
		<a href="SignUp.jsp">SignUp</a> <a href="findById.jsp">findById</a><a
			href="findByName.jsp">findByName</a><a href="findByEmail.jsp">findByEmail</a>
		<a href="findAll.jsp">findAll</a>
	</nav>
	<footer>
		<p style="font-size:small; ;padding-top: 0.7%;color: white;">
			© Created By : Suhas Banakar &nbsp; <a
				href="#" style="font-size: small;color: blue;"><u> suhasnb.xworkz@gmail.com</u></a>
		</p>
	</footer>
</body>
</html>