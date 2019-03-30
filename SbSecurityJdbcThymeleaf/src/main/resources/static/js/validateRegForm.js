
function validate() {
    if (document.f.login.value == "" && document.f.password.value == "") {
        alert("Username and password are required");
        document.f.login.focus();
        return false;
    }
    if (document.f.login.value == "") {
        alert("Username is required");
        document.f.login.focus();
        return false;
    }
    if (document.f.password.value == "") {
    alert("Password is required");
    document.f.password.focus();
        return false;
    }
}