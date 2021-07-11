package com.mybank.controller;

import com.mybank.exception.NoClientConnectedException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "Logout", value = "/logout")
public class Logout extends HttpServlet implements ServletUtils {
  @Override
  protected void doGet(HttpServletRequest request,
    HttpServletResponse response) throws ServletException, IOException {
    try {
      assertThereIsClientConnected(request);
      disconnectUser(request);
      showLogoutPage(request, response);
    } catch (NullPointerException | NoClientConnectedException e) {
      sendUserToLoginPage(request, response);
    }
  }

  private void disconnectUser(HttpServletRequest request) {
    request.getSession().invalidate();
  }

  private void showLogoutPage(HttpServletRequest request,
    HttpServletResponse response) throws ServletException, IOException {
    request.setAttribute("title", "See you soon!");
    request.setAttribute("pageHeading", "You have been disconnected!");
    this
      .getServletContext()
      .getRequestDispatcher("/WEB-INF/users/logout.jsp")
      .forward(request, response);
  }
}
