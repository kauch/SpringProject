function validate()
{
	var loginInput = document.f.login;
	var passwordInput = document.f.password;
//	var uemail = document.f.email;
//	var umsex = document.f.msex;
//	var ufsex = document.f.fsex;
	if(login_validation(loginInput, 4, 12))
	{
		if(password_validation(passwordInput, 3, 12))
		{
			
//			if(ValidateEmail(uemail))
//			{
//				if(validsex(umsex,ufsex))
//				{
//				}
//			} 
				
		}
	}
	return false;
}

function login_validation(loginInput, mx, my)
{
	var login_len = loginInput.value.length;
	if (login_len == 0)
	{
		document.getElementById('loginf').innerHTML = "*login should not be empty";
		loginInput.focus();
		return false;
	}
	if (login_len >= my || login_len < mx)
	{
		document.getElementById('loginf').innerHTML = "*length be between " + mx + " to " + my;
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
		document.getElementById('passwordf').innerHTML = "*password should not be empty";
		passwordInput.focus();
		return false;
	}
	if (password_len >= my || password_len < mx)
	{
		document.getElementById('passwordf').innerHTML="*length be between " + mx + " to " + my;
		passwordInput.focus();
		return false;
	}
	document.getElementById('passwordf').innerHTML = "";
	return true;
}

//function ValidateEmail(uemail)
//{
//	var mailformat = /^w+([.-]?w+)*@w+([.-]?w+)*(.w{2,3})+$/;
//	if(uemail.value.match(mailformat))
//	{
//		return true;
//	}
//	else
//	{
//		alert("You have entered an invalid email address!");
//		uemail.focus();
//		return false;
//	}
//}
//
//function validsex(umsex,ufsex)
//{
//	x=0;
//	if(umsex.checked) 
//	{
//		x++;
//	}
//	if(ufsex.checked)
//	{
//		x++; 
//	}
//	if(x==0)
//	{
//		alert('Select Male/Female');
//		umsex.focus();
//		return false;
//	}
//	else
//	{
//		alert('Form SuccesfullySubmitted');
//		window.location.reload()
//		return true;
//	}
//}
