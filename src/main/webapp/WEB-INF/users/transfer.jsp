<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="t" tagdir='/WEB-INF/tags/connected' %>
<jsp:useBean id="hour" scope="request" type="java.lang.Integer" />
<jsp:useBean id="client" scope="request" type="com.mybank.model.Client" />

<t:layout title='Make transfert' client='${client}'>
  <div class="form-wrapper">
    <form class="gentle-form transfer-form" id="transfer-form">
      <div class="modal-container hidden" id="modal-container"></div>
      <section class="form-content">
        <h2 class="form-title">Sender card ID</h2>
        <fieldset class="form-group form-fieldset">
          <legend class="sr-only">Sender account</legend>
          <div class="form-dropdown" id="dp-acctype">
            <p class="form-dropdown-label" id="dp-acctype-label" tabindex="0">
              Saving Account
            </p>
            <div class="dropdown-content hidden" id="dp-acctype-content">
              <ul class="dropdown-list">
                <li class="dropdown-item" tabindex="0" data-acctype="sva">
                  <span class="dropdown-text">Saving Account</span>
                </li>
                <li class="dropdown-item" tabindex="0" data-acctype="cra">
                  <span class="dropdown-text">Current Account</span>
                </li>
              </ul>
            </div>
          </div>
          <label class="form-row">
            <input
              type="search"
              name="senderID"
              id="senderID"
              class="form-row--input"
              placeholder=" "
              autocomplete="off"
            />
            <span class="form-row--label">
                <i class="row-label--icon fas fa-credit-card"></i>
                <span class="row-label--text">Type the account name or ID</span>
              </span>
            <button class="form-row--btn btn-clickable" name="senderOkBtn">
              <i class="fad fa-forward"></i>
            </button>
          </label>
          <div class="search-result" id="sender-search-res">
            <ul class="result-list" id="sender-res-list"></ul>
          </div>
        </fieldset>
      </section>

      <section class="form-content">
        <h2 class="form-title">Receiver card ID</h2>
        <fieldset class="form-group form-fieldset">
          <legend class="sr-only">Receiver account</legend>
          <label class="form-row">
            <input
              type="search"
              name="receiverID"
              id="receiverID"
              class="form-row--input"
              placeholder=" "
              autocomplete="off"
            />
            <span class="form-row--label">
                <i class="row-label--icon fas fa-credit-card"></i>
                <span class="row-label--text">Type account ID</span>
              </span>
            <button class="form-row--btn" name="receiverOkBtn">
              <i class="fad fa-forward"></i>
            </button>
          </label>
          <div class="search-result" id="receiver-search-res">
            <ul class="result-list" id="receiver-res-list"></ul>
          </div>
        </fieldset>
      </section>

      <section class="form-content disabled">
        <h2 class="form-title">Amount to send</h2>
        <fieldset class="form-group form-fieldset">
          <legend class="sr-only">Setting amount to send</legend>
          <label class="form-row">
            <input
              type="text"
              name="amount"
              id="amount"
              class="form-row--input"
              placeholder=" "
              autocomplete="off"
              min="0"
              max="45536.56"
            />
            <span class="form-row--label">
                <i class="row-label--icon fas fa-coins"></i>
                <span class="row-label--text">
                  Enter amount in DH (max 45,536.56 DH)
                </span>
              </span>
            <button class="form-row--btn" name="amountBtn">
              <i class="fad fa-forward"></i>
            </button>
          </label>
        </fieldset>
      </section>

      <section class="form-content">
        <fieldset class="form-btn-group form-fieldset">
          <button class="btn btn-cancel" id="form-btn-cancel" name="cancel">
            <i class="btn-icon fas fa-times-circle"></i>
            <span class="row-label--text">Cancel</span>
          </button>
          <button class="btn btn-submit" id="form-btn-submit" name="submit">
            <i class="btn-icon fas fa-mail-bulk"></i>
            <span class="row-label--text">Send Money</span>
          </button>
        </fieldset>
      </section>
    </form>
  </div>

  <script
    type="module"
    src="${pageContext.request.contextPath}/public/js/layout/layout.js">
  </script>

  <script
    type="module"
    async
    src="${pageContext.request.contextPath}/public/js/transfer/index.js">
  </script>
</t:layout>
