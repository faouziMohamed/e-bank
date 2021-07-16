// import { drawUserData } from './userData.js';

'use strict';

// function fetchUserData() {
//   fetch('/userdata')
//     .then((response) => response.json())
//     .then((data) => drawUserData({
//       data: data,
//       className: 'chart-item-wrapper',
//       elClassNameToRemove: 'loading-wrapper'
//     }));
// }

let input = document.querySelector('.checkbox-input');
let mainMenu = document.querySelector('.main-menu');
mainMenu.classList.add('closed');
input.checked = false;
input.addEventListener('change', (e) => {
  mainMenu.classList.toggle('closed');
});

//fetchUserData();
