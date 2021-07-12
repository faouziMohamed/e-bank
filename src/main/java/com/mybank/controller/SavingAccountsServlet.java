package com.mybank.controller;

import com.mybank.exception.NoClientConnectedException;
import com.mybank.model.Client;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "SavingAccountsServlet", value = "/savingAccounts")
public class SavingAccountsServlet extends HttpServlet implements ServletUtils {
  @Override
  protected void doGet(HttpServletRequest request,
                       HttpServletResponse response) throws ServletException,
                                                            IOException {

    try {
      Client c = assertThereIsClientConnected(request);
      request.setAttribute("client", c);
      request.setAttribute("header1", "Wording");
      request.setAttribute("header2", "Balance");
      request.setAttribute("header3", "Interest Rate");
      request.setAttribute("header4", "Ceiling");
      request.setAttribute("allAccounts", c.getAllSavingAccount().values());
      this.getServletContext()
          .getRequestDispatcher("/WEB-INF/users/savingAccounts.jsp")
          .forward(request, response);
    } catch (NullPointerException | NoClientConnectedException e) {
      sendUserToLoginPage(request, response);
    }
  }

  @Override
  protected void doPost(HttpServletRequest request,
                        HttpServletResponse response) throws ServletException, IOException {

  }
}
