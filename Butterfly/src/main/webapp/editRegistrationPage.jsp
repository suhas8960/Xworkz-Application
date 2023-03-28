<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Edit Registration Page</title>
<style>
body {
	background-image:
		url(
"https://media.istockphoto.com/id/1283633817/photo/communication-network-concept-in-cyberspace-abstract-background.jpg?b=1&s=170667a&w=0&k=20&c=m2xGn1TZQFdkjMv0GYYxL11Co9IO7RTPP8dBu23QBTo=");
	background-repeat: no-repeat;
	background-attachment: fixed;
	background-size: 100% 100%;
}

tr {
	color: red;
}

td {
	color: white;
}

input {
	color: green;
}

footer {
	position: fixed;
	left: 0;
	bottom: 0;
	width: 100%;
	height: 7%;
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
		<li class="nav-item-active"><a style="color: white"
			class="nav-link" href="index.jsp">Home</a></li>
	</nav>
	<h4 style="color: red">${errors}</h4>
	<form action="update" method="post">
		<h1 style="color: green">All_Details</h1>

		<div align="center">
			<h2 style="color: red">Edit Registartion Page</h2>
			<table border="6" cellpadding="10">
				<tr>

					<th>ID</th>
					<!-- <th>Photo</th> -->
					<th>Name</th>
					<th>LastName</th>
					<th>Email</th>
					<th>MobileNo</th>
					<th>Password</th>
					<th>update</th>

				</tr>
				<tr>
				<td><input type="number" name="id" value="${dtos.id}" readonly="readonly" ></td>
	<%-- <td><input type="number" name="id" value="${dtos.id}" readonly="readonly" ></td> --%>
	<td><input type="text" name="name" value="${dtos.name}"  ></td>
	<td><input type="text" name="lastName" value="${dtos.lastName}"  ></td>
	<td><input type="email" name="email" value="${dtos.email}" readonly="readonly" ></td>
	<td><input type="text" name="mobileNo" value="${dtos.mobileNo}" ></td>
	<td><input type="password" name="password" value="${dtos.password}" ></td>
	<td><input type="submit" value="update" ></td>
	
	
	
	</tr>
	</table>
	</div>
	</form>



	<footer>
		<p style="font-size: small;; padding-top: 0.7%; color: white;">
			© Created By : Suhas Banakar &nbsp; <a href="#"
				style="font-size: small; color: blue;"><u>
					suhasnb.xworkz@gmail.com</u></a>
		</p>
	</footer>
</body>
</html>