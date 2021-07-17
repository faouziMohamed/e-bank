'use strict';

void (function () {
  function redirectToLogin(selector) {
    let timeout = document.querySelector(selector);
    let interval = null;
    if (timeout) {
      let timer;
      interval = setInterval(() => {
        timer = timer > 0 ? Number(timeout.innerText) - 1 : 0;
        timeout.innerText = timer;
      }, 1000);
    }

    setTimeout(() => {
      if (interval) {
        clearInterval(interval);
      }
      location.replace('/login');
    }, 100);
  }

  redirectToLogin('#timeout');
})();
