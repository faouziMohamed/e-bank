package com.mybank.controller;

import com.mybank.exception.BankException;
import com.mybank.model.BankService;
import com.mybank.model.Client;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "Login", value = "/login")
public class Login extends HttpServlet {

  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
    request.setAttribute("title", "Authentication page");
    request.setAttribute("pageHeading", "Welcome to your online Bank");
    this.getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
  }

  @Override
  public void doPost(HttpServletRequest request,
    HttpServletResponse response) throws IOException, ServletException {
    String username = getUsername(request);
    String password = getPassword(request);

    try {
      Client client = getClientFromDB(username, password);
      acceptAuthentication(request, client);
      String mainPage = getFullPath(request, "/dashboard");
      redirectUserToMainPage(response, mainPage);
    } catch (BankException e) {
      String errorMessage = e.getMessage();
      request.setAttribute("error", errorMessage);
      doGet(request, response);
    }
  }

  private String getPassword(HttpServletRequest request) {
    return request.getParameter("password");
  }

  private String getUsername(HttpServletRequest request) {
    return request.getParameter("username");
  }

  private Client getClientFromDB(String username, String password) throws BankException {
    return BankService
             .getInstance()
             .findClient(username, password);
  }

  private String getFullPath(HttpServletRequest request, String path) {
    return request.getContextPath() + path;
  }

  private void redirectUserToMainPage(
    HttpServletResponse response, String mainPage) throws IOException {
    response.sendRedirect(mainPage);
  }

  private void acceptAuthentication(HttpServletRequest request, Client client) {
    request.setAttribute("client", client);
    request.getSession(true)
      .setAttribute("client", client);
  }

  @Override
  public void destroy() {
    // TODO: add code
  }

  @Override
  public void init() {
    // TODO: add code
  }
}
