<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>ForgetPassword</title>
<link rel="stylesheet" type="text/css"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css" />
<script
	src="https://cdn.jsdelivr.net/npm/jquery@3.6.0/dist/jquery.slim.min.js"></script>
<script
	src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js"></script>
<script>
    function validateForm(event) {
        var form = event.target;
        if (!form.checkValidity()) {
            event.preventDefault();
            event.stopPropagation();
        }
        form.classList.add("was-validated");
    }
</script>
<style>
footer {
	position: fixed;
	left: 0;
	bottom: 0;
	width: 100%;
	height:7%;
	background-color: #FF8C00;
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
		<a href="index.jsp" class="btn btn-info">Home</a>
	</nav>
	
	<form action="forget" method="get" onsubmit="return validateForm(event)" class="needs-validation" novalidate>
		<div align="center">
			<div class="container">
				<div class="row justify-content-md-center">
					<div class="col col-sm-5">
						<h1 style="color: DarkGreen">Forget</h1>
						<h3 style="color: powderblue">${msg}</h3>
						<h3 style="color: v">${msgs}</h3>
						<div class="form-group">
									<input type="email" class="form-control" id="emai"
										name="email" aria-describedby="emailHelp"
										placeholder="Enter email" class="form-control" required> 
										<div class="invalid-feedback" style="color: red">Please enter a email.</div>
								</div>
						<input class="btn btn-info" type="Submit" name="Signin.jsp" value="SendOTP" />
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