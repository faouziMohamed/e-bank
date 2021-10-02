package com.mybank.controller;

import com.mybank.exception.NoClientConnectedException;
import com.mybank.model.Client;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "CurrentAccountsServlet", value = "/currentAccounts")
public class CurrentAccountsServlet extends HttpServlet implements ServletUtils {
  @Override
  protected void doGet(HttpServletRequest request,
                       HttpServletResponse response) throws ServletException,
                                                            IOException {
    try {
      Client c = assertThereIsClientConnected(request);
      request.setAttribute("client", c);
      request.setAttribute("header1", "Wording");
      request.setAttribute("header2", "Balance");
      request.setAttribute("allAccounts", c.getAllCurrentAccount().values());
      this.getServletContext()
          .getRequestDispatcher("/WEB-INF/users/currentAccounts.jsp")
          .forward(request, response);
    } catch (NullPointerException | NoClientConnectedException e) {
      sendUserToLoginPage(request, response);
    }
  }
}
