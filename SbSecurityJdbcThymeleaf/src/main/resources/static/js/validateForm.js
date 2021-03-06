var oValidation = {	
		isValidated: function () {
			var loginInput = document.getElementById('inputLogin');
			var passwordInput = document.getElementById('inputPassword');
			var emailInput = document.getElementById('inputEmail');
			var firstNameInput = document.getElementById('inputFirstName');
			var lastNameInput = document.getElementById('inputLastName');
			var mGender = document.getElementById('exampleRadios1');
			var wGender = document.getElementById('exampleRadios2');
			  
			var result = true;
			
			this.clearError();
			
			if (loginInput != null && !this.validateLogin(loginInput, 4, 12)) {
				result = false;
			}
			
			if (passwordInput != null && !this.validatePassword(passwordInput, 3, 12)) {
				result = false;
			}
	
			if (emailInput != null && !this.validateEmail(emailInput)) {
				result = false;
			}
			
			if (firstNameInput != null && !this.validateFirstName(firstNameInput)) {
				result = false;
			}
			
			if (lastNameInput != null && !this.validateLastName(lastNameInput)) {
				result = false;
			}
			
			if (mGender != null && wGender != null && !this.validateGender(mGender, wGender)) {
				result = false;
			}
			
			return result;
		},

		validateLogin: function (loginInput, mx, my) {
			var login_len = loginInput.value.length;
			if (login_len == 0)
			{
				this.highlightField(loginInput, 'loginf', "login should not be empty");
				return false;
			}
			if (login_len >= my || login_len < mx)
			{
				this.highlightField(loginInput, 'loginf', "length be between " + mx + " to " + my);
				return false;
			}	
			return true;
		},
		
		validatePassword: function (passwordInput, mx, my) {
			var password_len = passwordInput.value.length;
			if (password_len == 0)
			{
				this.highlightField(passwordInput, 'passwordf', "password should not be empty");
				return false;
			}
			if (password_len >= my || password_len < mx)
			{
				this.highlightField(passwordInput, 'passwordf', "length be between " + mx + " to " + my);
				return false;
			}
			return true;
		},
		
		validateEmail: function (emailInput) {
			var mailformat = /\S+@\S+\.\S+/;
			if(emailInput.value.match(mailformat))
			{
				return true;
			}
			else
			{
				this.highlightField(emailInput, 'emailf', "you have entered an invalid email address!");
				return false;
			}
		},
		
		validateFirstName: function (firstNameInput) {
			var fName_len = firstNameInput.value.length;
			if (fName_len == 0)
			{
				this.highlightField(firstNameInput, 'firstNamef', "first name should not be empty");
				return false;
			}
			return true;
		},
		
		validateLastName: function (lastNameInput) {
			var lName_len = lastNameInput.value.length;
			if (lName_len == 0)
			{
				this.highlightField(lastNameInput, 'lastNamef', "last name should not be empty");
				return false;
			}
			return true;
		},
		
		validateGender: function(mGender, wGender) {
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
			return true;
		},
		
		highlightField: function (inputField, spanSelector, text){
			document.getElementById(spanSelector).innerHTML = text;
			inputField.style.borderColor ="red";
		},
		
		clearError: function (){
			var elems = document.querySelectorAll('.error-span');
			for (var i = 0; i < elems.length; i++) {
				elems[i].innerHTML = "";
			}
			var inputs = document.querySelectorAll('.form-control');
			for (var j = 0; j < inputs.length; j++) {
				inputs[j].style.borderColor ="#ced4da";
			}
		}
		
}