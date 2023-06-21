<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>X-Workz</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD"
	crossorigin="anonymous">
<style>
             
footer {
	position: fixed;
	left: 0;
	bottom: 0;
	width: 100%;
	height: 7%;
	background-color: #FF8C00;
	color: black;
	text-align: center;
}
</style>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-w76AqPfDkMBDXo30jS1Sgez6pr3x5MlQ1ZAGC+nuZB+EYdgRZgiwxhTBTkF7CXvN"
	crossorigin="anonymous"></script>
</head>
<body>
	<nav class="navbar navbar-expand-lg-navbar-Light bg-dark">
		<div class="container-fluid">
			<a class="navbar-brand" href="#"> <img
				src=" https://x-workz.in/static/media/Logo.cf195593dc1b3f921369.png"
				alt="" width="80" height="48" class="d-inline-block align text-top">
				<div>
      <a href="Signin.jsp" class="btn btn-info">Logout</a>
     <span style="color: white;">Welcome,${udto.name} ${udto.lastName}</span>
      <span> <img src="download?fileName=${dtoPic}" height="70" width="80"></span>
      </div>
		</div>
	</nav>
	<a href="UpdateProfile.jsp" class="btn btn-info">Update Profile</a>
	<a href="AddCustomer.jsp" class="btn btn-info">AddOrUpdateCustomer</a>
	<a href="Customer.jsp" class="btn btn-info">Customer</a>
	
	<footer>
		<p style="font-size: small;; padding-top: 0.7%; color: white;">
			© Created By : Suhas Banakar &nbsp; <a href="#"
				style="font-size: small; color: blue;"><u>
					suhasnb.xworkz@gmail.com</u></a>
					<span style="color: white;">LastLogin : ${udto.loginTime}</span>
		</p>
		
	</footer>
</body>
</html>