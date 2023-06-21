<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>AddCustomer</title>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
	integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
	crossorigin="anonymous">
</head>
<script>
	function filterByName() {
		var input = document.getElementById('letterFilter').value;
		console.log(input);
		if (input.length > 0) {
			var xhr = new XMLHttpRequest();
			xhr.onreadystatechange = function() {
				if (xhr.readyState === 3 && xhr.status === 200) {
					var suggestions = JSON.parse(xhr.responseText);
					populateDatalist(suggestions);
				}
			}
		};
		xhr.open('GET', 'http://localhost:8080/Trainer/search/' + input, true);
		xhr.send();
	}

	function populateDatalist(suggestions) {
		var datalist = document.getElementById("auto-suggestion");
		datalist.innerHTML = ""; // Clear existing options

		for (var i = 0; i < suggestions.length; i++) {
			var option = document.createElement("option");
			console.log(option);
			option.value = suggestions[i];
			/* console.log(option.value); */
			datalist.appendChild(option);
		}
	};
</script>
<style>
tr {
	color: red;
}

td {
	color: green;
}

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
<body>
	<nav class="navbar navbar-expand-lg-navbar-Light bg-dark">
		<div class="container-fluid">
			<a class="navbar-brand" href="#"> <img
				src=" https://x-workz.in/static/media/Logo.cf195593dc1b3f921369.png"
				alt="" width="80" height="48" class="d-inline-block align text-top">
			</a>
			<div>
				<a href="LoginSucess.jsp" class="btn btn-info">Home</a> <span
					style="color: white;">Welcome,${udto.name} ${udto.lastName}</span>
				<span> <img src="download?fileName=${dtoPic}" height="70"
					width="80"></span>
			</div>
		</div>
	</nav>
	<form action="customer">
		<div align="center">

			<h2 style="color: green">Customers</h2>
			<div>
				<input list="auto-suggestion" name="letters" id="letterFilter"
					placeholder="name search" onkeyup="filterByName()"> 
					<datalist id="auto-suggestion">
					</datalist>
			</div>
			<input type="submit" class="btn btn-info" name="submit"
				value="submit" />

			<table border="6" cellpadding="10" id="myTable">
				<tr>
					<th>ID</th>
					<th>Name</th>
					<th>address</th>
					<th>email</th>
					<th>mobileNo</th>
					<th>companyName</th>
					<th>salary</th>


				</tr>

				<c:forEach items="${customer}" var="allCustomer">
					<tr id="search">
						<td>${allCustomer.coustomerId}</td>
						<td>${allCustomer.name}</td>
						<td>${allCustomer.address}</td>
						<td>${allCustomer.email}</td>
						<td>${allCustomer.mobileNo}</td>
						<td>${allCustomer.companyName}</td>
						<td>${allCustomer.salary}</td>
						<td><a class="btn btn-info"
							href="customer/${allCustomer.coustomerId}">Edit</a></td>
						<td><a href="customer/byId/${allCustomer.coustomerId}"
							class="btn btn-info">Delete</a></td>
					</tr>
				</c:forEach>
			</table>
		</div>

	</form>



	<footer>
		<p style="font-size: small;; padding-top: 0.7%; color: white;">
			© Created By : Suhas Banakar &nbsp; <a href="#"
				style="font-size: small; color: blue;"><u>
					suhasnb.xworkz@gmail.com</u></a> <span style="color: white;">LastLogin
				: ${udto.loginTime}</span>
		</p>
	</footer>
</body>
</html>
