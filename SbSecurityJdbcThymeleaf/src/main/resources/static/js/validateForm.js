    function showError(container, errorMessage) {
      container.className = 'error';
      var msgElem = document.createElement('span');
      msgElem.className = "error-message";
      msgElem.innerHTML = errorMessage;
      container.appendChild(msgElem);
    }

    function resetError(container) {
      container.className = '';
      if (container.lastChild.className == "error-message") {
        container.removeChild(container.lastChild);
      }
    }

    function validate(form) {
      var elems = form.elements;

      if (!elems.login.value) {
        showError(elems.login.parentNode, ' Укажите от кого.');
      }

      if (!elems.password.value) {
        showError(elems.password.parentNode, ' Укажите пароль.');
      }

      if (!elems.firstName.value) {
        showError(elems.firstName.parentNode, ' Укажите, куда.');
      }

      if (!elems.lastName.value) {
        showError(elems.lastName.parentNode, ' Отсутствует текст.');
      }

    }