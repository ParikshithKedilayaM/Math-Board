var role;
var grade;
function checkForStudent(selectedVal) {
	role = selectedVal.value;
    if (selectedVal.value === "student") {
        document.getElementById("labelGrade").style.display = "block";
        document.getElementById("selectGrade").style.display = "block";
		//document.getElementById("selectGrade").required = true;
    } else {
        document.getElementById("labelGrade").style.display = "none";
        document.getElementById("selectGrade").style.display = "none";
		//document.getElementById("selectGrade").required = false;
    }
}
function setGrade(gradeVal) {
	grade = gradeVal.value;
}
function register() {
	var reqFields = ["userid","name","psw","psw-repeat","selectRole"];
	var nonNullFields = 0;
	for (nonNullFields=0; nonNullFields<reqFields.length; nonNullFields++) {
		if (document.getElementById(reqFields[nonNullFields]).value === '' || document.getElementById(reqFields[nonNullFields]).value === null) {
			return false;
		}
	}
	if (document.getElementById("email").value !== '' && document.getElementById("email").value !== null) {
		var isValidEmail = validateEmail(document.getElementById("email").value);
		if (!isValidEmail) {
			alert("Invalid e-mail id entered");
			return false;
		}
	}
	if (!checkPassword(document.getElementById("psw").value, document.getElementById("psw-repeat").value)) {
		return false;
	}
	if (nonNullFields===reqFields.length) {
		var params = {
			'userid' : document.getElementById('userid').value,
			'name' : document.getElementById('name').value,
			'role' : role,
			'grade' : grade,
			'email' : document.getElementById('email').value,
			'password' : document.getElementById('psw').value
		};
		regUserAPICall(params);
	} 
}
function regUserAPICall(params) {
	
	var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function() {
         if (this.readyState == 4 && this.status == 200) {
             alert(this.responseText);
         }
    };
	xhttp.open("POST", "http://127.0.0.1:8080/RegisterUser", false);
    xhttp.setRequestHeader("Content-type", "application/json");
    xhttp.send(JSON.stringify(params));
}
function validateEmail(email) {
    var re = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
    return re.test(String(email).toLowerCase());
}
function checkPassword(pwd, repeatpwd) {
	if (pwd !== repeatpwd) {
		alert("Passwords do not match");
		return false;
	}else {
		return true;
	}
}
function cancelreg() {
    window.location.href="../";
}