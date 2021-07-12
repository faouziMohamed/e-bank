void (function () {
    function redirectToLogin(selector) {
        let timeout = document.querySelector(selector);
        let interval = null;
        if (timeout) {
            let timer;
            interval = setInterval(() => {
                timer = Number(timeout.innerHTML) - 1;
                timeout.innerText = timer;
            }, 1000);
        }

        setTimeout(() => {
            if (interval) {
                clearInterval(interval);
            }
            location.replace("/login");
        }, 5000)
    }

    redirectToLogin("#timeout");
})();
