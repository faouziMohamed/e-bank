import { drawUserData } from './userData.js';

'use strict';
void (function () {
  function fetchUserData() {
    fetch('/userdata')
      .then((response) => response.json())
      .then((data) => drawUserData({
        data: data,
        className: 'chart-item-wrapper',
        elClassNameToRemove: 'loading-wrapper'
      }));
  }

  fetchUserData();
})();
