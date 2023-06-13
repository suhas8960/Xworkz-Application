<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>UpdateProfile</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD"
	crossorigin="anonymous">
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
				</a>
				<div>
					<a href="LoginSucess.jsp" class="btn btn-info">Home</a> <span
						style="color: white;">Welcome,${userName} ${lastName}</span> <span>
						<img src="download?fileName=${dtoPic}" height="70" width="80">
					</span>
				</div>
		</div>
	</nav>
	<div align="center">
		<form action="upload" method="post" enctype="multipart/form-data" onsubmit="return validateForm(event)" class="needs-validation" novalidate>
			<div align="center">
				<div class="sign">
					<div class="container">
						<form id="myForm" class="needs-validation" novalidate>
							<div class="row justify-content-md-center">
								<div class="col col-sm-5">

							<div class="form-group">
									<input type="email" class="form-control" id="emai" value="${udto.email}" 
										name="email" aria-describedby="emailHelp"
										placeholder="Enter email"  readonly="readonly"> 
								</div>
									<div class="form-group">
										<input type="text" class="form-control" id="name"
											value="${udto.name}" placeholder="Enter Name" name="name"
											required>
									</div>

									<div class="form-group">
										<input type="text" class="form-control" id="lastName"
											value="${udto.lastName}" placeholder="Enter lastName"
											name="lastName" required>
									</div>
									<div class="form-group">
										<input type="file" name="infinity"> <span
											style="color: red">${error}</span>
									</div>

								</div>
							</div>
						</form>
						<div>
							<button type="submit" class="btn btn-success">Update</button>
						</div>

					</div>
				</div>
			</div>

		</form>

	</div>
	<footer>
		<p style="font-size: small;; padding-top: 0.7%; color: white;">
			© Created By : Suhas Banakar &nbsp; <a href="#"
				style="font-size: small; color: blue;"><u>
					suhasnb.xworkz@gmail.com</u></a> <span style="color: white;">LastLogin
				: ${lastLoginTime}</span>
		</p>
	</footer>
</body>
</html>