import { drawUserData } from './userData.js';

(() => {
  function fetchUserData() {
    fetch('/userdata')
      .then((response) => response.json())
      .then((data) =>
        drawUserData({
          data,
          className: 'chart-item-wrapper',
          elClassNameToRemove: 'loading-wrapper',
        }),
      )
      // eslint-disable-next-line no-console
      .catch(console.log);
  }

  fetchUserData();
})();
