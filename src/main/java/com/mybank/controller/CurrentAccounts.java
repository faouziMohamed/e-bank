package com.mybank.controller;

import com.mybank.exception.NoClientConnectedException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "CurrentAccounts", value = "/currentAccounts")
public class CurrentAccounts extends HttpServlet implements ServletUtils {
  @Override
  protected void doGet(HttpServletRequest request,
    HttpServletResponse response) throws ServletException, IOException {
    try {
      assertThereIsClientConnected(request);
      this.getServletContext()
        .getRequestDispatcher("/WEB-INF/users/currentAccounts.jsp")
        .forward(request, response);
    } catch (NullPointerException | NoClientConnectedException e) {
      sendUserToLoginPage(request, response);
    }
  }
}
