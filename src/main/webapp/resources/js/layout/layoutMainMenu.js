'use strict';

function useMainMenu() {
  let mainMenu = document.querySelector('.main-menu') || null;
  let input = document.querySelector('.checkbox-input') || null;
  if (mainMenu === null || input === null) {
    return;
  }

  let toggleOpenCloseMenu = () => {
    mainMenu.classList.toggle('closed');
  };

  let closeMenu = () => {
    mainMenu.classList.add('closed');
    input.checked = false;
  };

  function setMediaQuery(media) {
    mainMenu.classList.add('closed');
    input.checked = false;
    if (media.matches) {
      input.addEventListener('change', toggleOpenCloseMenu);
      mainMenu.addEventListener('click', closeMenu);
    } else {
      input.removeEventListener('change', toggleOpenCloseMenu);
      mainMenu.removeEventListener('click', closeMenu);
    }
  }

  let media = matchMedia('(max-width: 779px)');
  media.addEventListener('change', setMediaQuery);
  setMediaQuery(media);
}

export default useMainMenu;
