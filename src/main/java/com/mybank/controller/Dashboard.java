package com.mybank.controller;

import com.mybank.model.Client;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.time.LocalTime;

@WebServlet(name = "Dashboard", value = "/client/dashboard")
public class Dashboard extends HttpServlet {
  @Override
  protected void doGet(HttpServletRequest request,
    HttpServletResponse response) throws ServletException, IOException {
    Client client = null;
    int hour = 0;
    try {
      HttpSession session = request.getSession(false);
      client = (Client) session.getAttribute("client");
      hour = LocalTime.now().getHour();

      if (client == null) {
        throw new NullPointerException();
      }

      request.setAttribute("hour", hour);
      request.setAttribute("client", client);
      this.getServletContext()
        .getRequestDispatcher("/WEB-INF/users/main.jsp")
        .forward(request, response);

    } catch (IllegalStateException | NullPointerException e) {
      request.setAttribute("error", "You need to connect in order to continue");
      response.sendRedirect(request.getContextPath() + "/login");
    }
  }

  @Override
  protected void doPost(HttpServletRequest request,
    HttpServletResponse response) throws ServletException, IOException {
    doGet(request, response);
  }
}
