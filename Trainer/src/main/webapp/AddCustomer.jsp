<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>AddCustomer</title>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
	integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
	crossorigin="anonymous">
<script>
	function validEmail() {
		var userEmail = document.getElementById('emai');
		console.log(userEmail);
		var emailValue = userEmail.value;
		console.log(emailValue);
		if (emailValue != null && emailValue != "" && emailValue.length > 4
				&& emailValue.length < 40) {
			const xhttp = new XMLHttpRequest();
			xhttp.open("GET", "http://localhost:8080/Trainer/mail/"
					+ emailValue);
			xhttp.send();
			xhttp.onload = function() {
				console.log(this.responseText);
				document.getElementById("display").innerHTML = this.responseText
			}
			document.getElementById('emailError').innerHTML = '';
		} else {
			console.log("invalid email")
			document.getElementById('emailError').innerHTML = 'Plese enter the email';
		}
	}
	
	
	
	
	
</script>
<script>
	function toggleCompanyNameField(show) {
		var companyNameField = document.getElementById("companyNameField");
		companyNameField.style.display = show ? "block" : "none";
	}
	
	function toggleCompanyTable() {
        var companyTable = document.getElementById('companyNameField');

        if (hasCompany) {
            companyTable.style.display = 'table'; // Show the table
        } else {
            companyTable.style.display = 'none'; // Hide the table
        }
    }

    // Call the function initially to set the initial state of the company table
    toggleCompanyTable();

    // Add an event listener to the radio buttons to update the company table dynamically
    var radioButtons = document.getElementsByName('option');
    radioButtons.forEach(function (radioButton) {
        radioButton.addEventListener('change', toggleCompanyTable);
    });
    
    // Automatically select 'Yes' radio button if the company name is provided
    if (companyName && companyName.trim() !== '') {
        document.querySelector('input[value="yes"]').checked = true;
    }
</script>
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
</head>
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
	<form action="${pageContext.request.contextPath}/addOrUpdateCustomer"
		method="post" onsubmit="return validateForm(event)"
		class="needs-validation" novalidate>

		<div align="center">
			<h1 style="color: DarkGreen">${headers}</h1>
			<div class="sign">
				<div class="container">
					<div class="row justify-content-md-center">
						<div class="col col-sm-5">

							<h3 style="color: red">${msg}</h3>
							<h3 style="color: green">${msgs}</h3>

							<input type="hidden" name="coustomerId"
								value="${dtos.coustomerId}" readonly="readonly">
							<div class="form-group">
								<input type="text" class="form-control" id="name"
									value="${dtos.name}" placeholder="Enter Name" name="name"
									class="form-control" required>
								<div class="invalid-feedback" style="color: red">Please
									enter a name.</div>
							</div>

							<div class="form-group">
								<input type="text" class="form-control" id="add"
									value="${dtos.address}" placeholder="Enter Address"
									name="address" required>
								<div class="invalid-feedback" style="color: red">Please
									enter a Address.</div>
							</div>
							<div class="form-group">
								<input type="email" class="form-control" id="emai" name="email"
									value="${dtos.email}" aria-describedby="emailHelp"
									placeholder="Enter email" onchange="validEmail()" required>
								<span id="display" style="color: red"></span> <span
									id="emailError" style="color: red"></span>
							</div>
							<div class="form-group">
								<input type="text" class="form-control" id="mobile"
									value="${dtos.mobileNo}" placeholder="MobileNo" name="mobileNo"
									class="form-control" required>
								<div class="invalid-feedback" style="color: red">Please
									enter a MobileNo.</div>
							</div>
							<div class="form-check">
								<input class="form-check-input" type="radio" name="option"
									value="yes" onclick="toggleCompanyNameField(true)"> <label
									class="form-check-label">Yes</label>
							</div>
							<div class="form-check">
								<input class="form-check-input" type="radio" name="option"
									value="no" onclick="toggleCompanyNameField(false)"> <label
									class="form-check-label">No</label>
							</div>
							<div id="companyNameField" style="display: none;">
								<input type="text" placeholder="CompanyName" id="companyName"
									value="${dtos.companyName}" name="companyName"
									class="form-control" required>
								<div class="invalid-feedback" style="color: red">Please
									enter a companyName.</div>
							</div>

							<div class="form-group">

								<input type="text" class="form-control" id="salary"
									value="${dtos.salary}" placeholder="Salary" name="salary"
									class="form-control" required>
								<div class="invalid-feedback" style="color: red">Please
									enter a salary.</div>
							</div>

							<button class="btn btn-info" type="submit"
								onclick="validateForm()">Save</button>
						</div>

					</div>
				</div>
			</div>
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



