const card = document.querySelector('.wrapper');

function handleOpenAndClosingUserCard() {
  const arrowUser = document.querySelectorAll(
    '.sort-down, .user-profile-picture-scaled',
  );
  card.classList.add('hidden');
  arrowUser.forEach((el) => {
    el.addEventListener('click', () => {
      card.classList.toggle('hidden');
    });
  });
}

function handleClosingUserCard() {
  document.body.querySelector('.main-content').addEventListener(
    'click',
    (e) => {
      const { classList } = e.target;
      const hasClass =
        classList.contains('user-profile-picture-scaled') ||
        classList.contains('sort-down');
      if (hasClass) {
        return;
      }
      card.classList.add('hidden');
    },
    true,
  );
}

export default function useUserCard() {
  handleOpenAndClosingUserCard();
  handleClosingUserCard();
}
