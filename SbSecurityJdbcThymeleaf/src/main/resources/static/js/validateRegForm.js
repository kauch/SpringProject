function validate_registration()
{
  var loginInput = document.f.login;
  var passwordInput = document.f.password;
  var emailInput = document.f.userEmail;
  var firstNameInput = document.f.firstName;
  var lastNameInput = document.f.lastName;
  var mGender = document.f.exampleRadios1;
  var wGender = document.f.exampleRadios2;
  if(login_validation(loginInput, 4, 12))
  {
	if(password_validation(passwordInput, 3, 12))
	{	
	  if(ValidateEmail(emailInput))
	  {
	    if(ValidateFirstName(firstNameInput))
	    {
	      if(ValidateLastName(lastNameInput))
          {  
			if(ValidateGender(mGender,wGender))
			{
				return true;
			}
          }
        }  
	  } 
	}
  }
  return false;
}

function validate_edit_user()
{
  var loginInput = document.f.login;
  var emailInput = document.f.userEmail;
  var firstNameInput = document.f.firstName;
  var lastNameInput = document.f.lastName;
  var mGender = document.f.exampleRadios1;
  var wGender = document.f.exampleRadios2;
  if(login_validation(loginInput, 4, 12))
  {
	
	if(ValidateEmail(emailInput))
	{
	  if(ValidateFirstName(firstNameInput))
	  {
	    if(ValidateLastName(lastNameInput))
        {  
		  if(ValidateGender(mGender,wGender))
		  {
			return true;
		  }
        }
      }  
	} 
  }
  return false;
}

function validate_logon()
{
  var loginInput = document.f.login;
  var passwordInput = document.f.password;
  if(login_validation(loginInput, 4, 12))
  {
	if(password_validation(passwordInput, 3, 12))
	{
	  return true;  
	}
  }
  return false;
}

function login_validation(loginInput, mx, my)
{
	var login_len = loginInput.value.length;
	if (login_len == 0)
	{
		document.getElementById('loginf').innerHTML = "login should not be empty";
		loginInput.focus();
		return false;
	}
	if (login_len >= my || login_len < mx)
	{
		document.getElementById('loginf').innerHTML = "length be between " + mx + " to " + my;
		loginInput.focus();
		return false;
	}
	document.getElementById('loginf').innerHTML = "";
	return true;
}

function password_validation(passwordInput, mx, my)
{
	var password_len = passwordInput.value.length;
	if (password_len == 0)
	{
		document.getElementById('passwordf').innerHTML = "password should not be empty";
		passwordInput.focus();
		return false;
	}
	if (password_len >= my || password_len < mx)
	{
		document.getElementById('passwordf').innerHTML="length be between " + mx + " to " + my;
		passwordInput.focus();
		return false;
	}
	document.getElementById('passwordf').innerHTML = "";
	return true;
}

function ValidateEmail(emailInput)
{
	var mailformat = /\S+@\S+\.\S+/;
	if(emailInput.value.match(mailformat))
	{
		document.getElementById('emailf').innerHTML = "";
		return true;
	}
	else
	{
		document.getElementById('emailf').innerHTML="you have entered an invalid email address!";
		emailInput.focus();
		return false;
	}
}

function ValidateFirstName(firstNameInput)
{
	var fName_len = firstNameInput.value.length;
	if (fName_len == 0)
	{
		document.getElementById('firstNamef').innerHTML = "first name should not be empty";
		firstNameInput.focus();
		return false;
	}
	document.getElementById('firstNamef').innerHTML = "";
	return true;
}

function ValidateLastName(lastNameInput)
{
	var login_len = lastNameInput.value.length;
	if (login_len == 0)
	{
		document.getElementById('lastNamef').innerHTML = "last name should not be empty";
		lastNameInput.focus();
		return false;
	}
	document.getElementById('lastNamef').innerHTML = "";
	return true;
}

function ValidateGender(mGender, wGender)
{
	x = 0;
	if(mGender.checked) 
	{
		x++;
	}
	if(wGender.checked)
	{
		x++; 
	}
	if(x == 0)
	{
		document.getElementById('radButtonf').innerHTML = "select Male / Female";
		return false;
	}
	document.getElementById('radButtonf').innerHTML = "";
	return true;
}