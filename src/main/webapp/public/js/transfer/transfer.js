import { modalTemplate } from '../utils/modal/basic.js';
import {
  emptyAndClose,
  getResultItem,
  handleEnterKeyPres,
  makeBold,
  parseHTML,
  postData,
  redirectTo,
  reloadPage,
  showElement,
} from '../utils/utils.js';

/* eslint-disable no-console */
export class TransferForm {
  constructor() {
    try {
      this.form = document.querySelector('#transfer-form');
      if (!this.form) {
        return;
      }

      this.receiverSearchRes = this.form.querySelector('#receiver-search-res');
      this.receiverResList = this.form.querySelector('#receiver-res-list');

      this.senderSearchRes = this.form.querySelector('#sender-search-res');
      this.senderResList = this.form.querySelector('#sender-res-list');

      const sections = this.form.querySelectorAll('.form-content');
      this.sections = [...sections];
      [this.currentSection] = this.sections;
      this.senderInput = this.form.senderID;
      this.receiverInput = this.form.receiverID;
      this.amountInput = this.form.amount;
      this.cancelBtn = this.form.querySelector('#form-btn-cancel');
      this.submitBtn = this.form.querySelector('#form-btn-submit');
      this.modalContainer = this.form.querySelector('#modal-container');
      this.data = [];

      this.handleSenderInputID = this.handleSenderInputID.bind(this);
      this.handleReceiverInputID = this.handleReceiverInputID.bind(this);
      this.handleAmountInput = this.handleAmountInput.bind(this);
      this.passToNextSection = this.passToNextSection.bind(this);
      this.handleSubmitBtn = this.handleSubmitBtn.bind(this);

      // Drop down
      this.dpAcctype = this.form.querySelector('#dp-acctype');
      this.dpAcctypeLabel = this.form.querySelector('#dp-acctype-label');
      this.dpAcctypeContent = this.form.querySelector('#dp-acctype-content');
      this.acctype = 'sva';
      this.acctypeMap = { sva: 'Saving Account', cra: 'Current Account' };

      this.initializeForm();
      this.addEventListeners();
    } catch (error) {
      console.error('Transfer form : ', error.message);
    }
  }

  initializeForm() {
    this.sections.forEach((s, index) => {
      s.dataset.index = index;
      const input = s.querySelector('input');
      if (input && index) s.querySelector('input').tabIndex = -1;
      if (index) s.classList.add('disabled');
    });

    this.hideResultBlock({ isBoth: true });
    this.senderInput.focus();
    this.dpAcctypeContent.classList.add('hidden');
  }

  addEventListeners() {
    this.form.querySelectorAll('button')?.forEach((btn) => {
      btn.addEventListener('click', (e) => e.preventDefault());
      if (btn.classList.contains('modal-btn')) {
        return;
      }
      btn.disabled = true;
    });

    this.form.querySelectorAll('.form-row--btn')?.forEach((btn, index) => {
      btn.addEventListener('click', () => this.passToNextSection(btn, index));
    });

    this.cancelBtn.addEventListener('click', () => {
      const { modal } = modalTemplate({
        title: 'Confirm cancelation',
        content: ' ',
        question: 'Are you sure you want to cancel the transaction?',
        btnOkText: 'No',
        btnCancelText: 'Back to home',
        btnOkAction: async () => emptyAndClose(this.modalContainer),
        btnCancelAction: () => redirectTo('/'),
      });
      this.modalContainer.replaceChildren(modal);
      showElement(this.modalContainer);
    });
    this.submitBtn.addEventListener('click', this.handleSubmitBtn);

    this.dpAcctypeLabel?.addEventListener('click', () => {
      this.dpAcctypeContent.classList.toggle('hidden');
      this.dpAcctypeLabel.classList.toggle('dropdown-opened');
    });
    this.form.parentElement.addEventListener('click', (e) => {
      if (e.target === this.dpAcctypeLabel) {
        return;
      }
      this.dpAcctypeContent.classList.add('hidden');
      this.dpAcctypeLabel.classList.remove('dropdown-opened');
    });
    this.dpAcctypeContent.querySelectorAll('.dropdown-item').forEach((item) => {
      item.addEventListener('click', (e) => {
        const { acctype = this.acctype } = e.target.dataset;
        this.acctype = acctype;
        this.dpAcctypeLabel.replaceChildren(this.acctypeMap[acctype]);
      });
    });

    this.getUserData()
      .then(() =>
        ['focus', 'input'].forEach((event) => {
          this.senderInput.addEventListener(event, this.handleSenderInputID);
        }),
      )
      .catch(console.error);

    this.getData()
      .then(() =>
        ['focus', 'input'].forEach((event) => {
          this.receiverInput.addEventListener(
            event,
            this.handleReceiverInputID,
          );
          this.amountInput.addEventListener(event, this.handleAmountInput);
        }),
      )
      .catch(console.error);

    const button = this.receiverInput.parentNode.lastElementChild;
    button.addEventListener('click', () => {
      ['focus', 'input'].forEach((event) =>
        this.receiverInput.removeEventListener(
          event,
          this.handleReceiverInputID,
        ),
      );
    });

    this.sections.forEach((s) => {
      const btn = s.querySelector('.form-row--btn');
      btn && s.addEventListener('keydown', handleEnterKeyPres(btn));
    });
  }

  passToNextSection(btn, index) {
    let rowInput = this.currentSection?.querySelector('input');
    rowInput.tabIndex = -1;
    [btn.disabled, rowInput.disabled] = [true, true];
    btn.firstElementChild.classList.replace('fa-forward', 'fa-check-circle');
    [(rowInput, rowInput)].forEach((el) => el.blur());
    this.currentSection?.classList.add('disabled');
    this.currentSection = this.sections[index + 1];
    this.currentSection?.classList.remove('disabled');
    rowInput = this.currentSection?.querySelector('input');
    if (rowInput) {
      rowInput.focus();
      rowInput.tabIndex = 0;
    } else {
      this.currentSection.querySelectorAll('button')?.forEach((btnEl) => {
        btnEl.disabled = false;
      });
    }
  }

  handleSenderInputID() {
    const { value } = this.senderInput;
    const typed = value?.toLowerCase().trim() || '';
    const btn = this.currentSection.querySelector('button');
    const f = (str) => str?.toLowerCase().includes(typed);
    this.hideResultBlock({ isSender: true });
    btn.disabled = true;
    if (!typed) {
      return;
    }
    this.senderInput.value = value.toUpperCase();
    const candidate = this.userData.filter(({ id, name }) => f(id) || f(name));
    if (!candidate.length) {
      return;
    }
    if (candidate.length === 1 && candidate[0]?.id === typed.toUpperCase()) {
      btn.disabled = false;
      return;
    }

    this.showResultBlock({ isSender: true });
    const lis = candidate.map((item) => parseHTML(getResultItem(item)));
    lis.forEach((item) => {
      item.addEventListener('click', () => {
        this.senderInput.value = item.dataset.id;
        this.senderInput.focus();
        this.hideResultBlock({ isSender: true });
      });
    });
    this.senderResList?.replaceChildren(...lis);
  }

  handleReceiverInputID() {
    const { value } = this.receiverInput;
    const typed = value?.toLowerCase().trim() || '';
    const btn = this.currentSection.querySelector('button');
    const isTypedIncluded = ({ id }) => id?.toLowerCase().includes(typed);
    this.hideResultBlock({ isSender: false });
    btn.disabled = true;

    if (!typed) {
      return;
    }
    this.receiverInput.value = value.toUpperCase();
    const candidate = this.data.filter(isTypedIncluded);
    if (!candidate.length) {
      return;
    }

    if (candidate.length === 1 && candidate[0]?.id === typed.toUpperCase()) {
      btn.disabled = false;
      return;
    }

    this.showResultBlock({ isSender: false });
    const lis = candidate.map((item) => parseHTML(getResultItem(item)));
    lis.forEach((item) => {
      item.addEventListener('click', () => {
        this.receiverInput.value = item.dataset.id;
        this.receiverInput.focus();
        this.hideResultBlock({ isSender: false });
      });
    });

    this.receiverResList?.replaceChildren(...lis);
  }

  handleAmountInput(e) {
    const btn = this.currentSection.querySelector('button');
    btn.disabled = true;
    const { target } = e;
    const { value } = target;
    const limit = Number(e.target.max);
    const floatNumberRegex = /^\d+(\.\d*)?$/;
    // Remove non digit or non dot character
    target.value = value.replace(/[^\d.]/g, '');
    // Remove negative sign
    target.value = target.value.replace(/^-/, '');
    // Get valid leading floating number with five decimal after dot
    target.value = target.value.replace(/(\d*(\.\d{1,5})).*/, '$1');
    !floatNumberRegex.test(target.value) && (target.value = '');
    if (Number(target.value) > limit) {
      target.value = limit;
    }
    target.value && (btn.disabled = false);
  }

  handleSubmitBtn() {
    const rId = makeBold(this.receiverInput.value);
    const sId = makeBold(this.senderInput.value);
    let amount = this.amountInput.value.replace(/\.$/, '.0');
    amount = makeBold(`${amount} DH`);
    const content = `You're about to send ${amount} to ${rId} using ${sId} account`;
    const { modal } = modalTemplate({
      title: 'Confirm Transfer',
      content: parseHTML(`<p>${content}</p>`),
      btnOkText: 'Send Money',
      btnOkAction: async () => this.makeTransfer(),
      btnCancelAction: () => reloadPage(),
    });
    this.modalContainer.replaceChildren(modal);
    showElement(this.modalContainer);
  }

  async makeTransfer() {
    const { value: receiverId } = this.receiverInput;
    const { value: senderId } = this.senderInput;
    const sAcctype = this.acctype;
    const { value: amount } = this.amountInput;
    const url = '/api/users/accounts/transfer.json';
    const data = { sAcctype, senderId, receiverId, amount };

    const { status /* json: resJson */ } = await postData({ url, data });
    if ([200, 201].includes(status)) {
      const { modal } = modalTemplate({
        title: 'Transfer Success',
        content: 'Your transfer has been successfully completed',
        btnOkText: 'Back to Home',
        btnOkAction: () => redirectTo('/'),
        disableBtn: 'cancel',
      });
      this.modalContainer.replaceChildren(modal);
    } else {
      const { modal } = modalTemplate({
        title: 'Transfer Failed',
        content: 'Your transfer has failed',
        btnOkText: 'Retry the transaction',
        btnOkAction: () => reloadPage(),
        btnCancelText: 'Back to Home',
        btnCancelAction: () => redirectTo('/'),
      });
      this.modalContainer.replaceChildren(modal);
    }
    showElement(this.modalContainer);
  }

  async getData() {
    const url = '/public/api/users/accounts.json';
    const { data = [] } = await fetch(url).then((res) => res.json());
    this.data = data.map(({ id, name }) => ({ id: id.toUpperCase(), name }));
    return this.data;
  }

  async getUserData() {
    const url = `/public/api/user/accounts/${this.acctype}.json`;
    const { data = [] } = await fetch(url).then((res) => res.json());
    this.userData = data;
    return this.userData;
  }

  showResultBlock({ isBoth = false, isSender = true } = {}) {
    const show = (el) => el.classList.remove('hidden');
    if (isBoth) {
      [this.senderSearchRes, this.receiverSearchRes].forEach(show);
    } else {
      isSender ? show(this.senderSearchRes) : show(this.receiverSearchRes);
    }
  }

  hideResultBlock({ isBoth = false, isSender = true } = {}) {
    const hide = (el) => el.classList.add('hidden');
    if (isBoth) {
      [this.senderSearchRes, this.receiverSearchRes].forEach(hide);
    } else if (isSender) {
      hide(this.senderSearchRes);
    } else {
      hide(this.receiverSearchRes);
    }
  }
}
