console.log("js file is start...")
function validate() {
	console.log("js file is runnig...")
	var returnValue = true;
	/* var name=document.myForm.name.value;
	if(name=""){
	 alert("Please Enter Name");
	 document.myForm.name.focus();
	 return false;
	} */

	var name = document.getElementById("nam");
	if (name.value.trim() == "") {
		document.getElementById("nameError").innerHTML = 'Please enter valid name';
		return returnValue;
	} else {
		true;
	}

	var lastName = document.getElementById("lnam");
	if (lastName.value.trim() == "") {
		document.getElementById("lnameError").innerHTML = 'Please enter Last name';
		return returnValue;
	} else {
		true;
	}

	var email = document.getElementById("ema");
	if (email.value.trim() == "") {
		document.getElementById("email").innerHTML = 'Please enter valid email';
		return returnValue;
	} else {
		true;
	}
	var mobileNo = document.getElementById("mobile");
	if (mobileNo.value.trim() == "") {
		document.getElementById("mobileError").innerHTML = 'Please enter valid mobile number';
		return returnValue;
	} else {
		true;
	}
	var password = document.getElementById("pass");
	if (password.value.trim() == "") {
		document.getElementById("passError").innerHTML = 'Please enter valid password';
		return returnValue;
	} else {
		true;
	}
	return returnValue;
}