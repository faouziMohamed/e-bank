function useMainMenu() {
  const mainMenu = document.querySelector('.main-menu') || null;
  const input = document.querySelector('.checkbox-input') || null;
  if (mainMenu === null || input === null) {
    return;
  }

  const toggleOpenCloseMenu = () => {
    mainMenu.classList.toggle('closed');
  };

  const closeMenu = () => {
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

  const media = matchMedia('(max-width: 779px)');
  media.addEventListener('change', setMediaQuery);
  setMediaQuery(media);
}

export default useMainMenu;
