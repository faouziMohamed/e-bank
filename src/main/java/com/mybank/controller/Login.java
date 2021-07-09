package com.mybank.controller;

import com.mybank.exception.BankException;
import com.mybank.exception.ClientNotFoundException;
import com.mybank.exception.PasswordErrorException;
import com.mybank.model.BankService;
import com.mybank.model.Client;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "Login", value = "/login")
public class Login extends HttpServlet {

  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
    this.getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
  }

  @Override
  public void doPost(HttpServletRequest request,
    HttpServletResponse response) throws IOException, ServletException {
    String name = request.getParameter("name");
    String pass = request.getParameter("pass");

    try {
      Client client = BankService.getInstance().findClient(name, pass);
      request.setAttribute("client", client);
      request.getSession(true)
        .setAttribute("client", client);
      response.sendRedirect(request.getContextPath() + "/client/dashboard");
    } catch (ClientNotFoundException | PasswordErrorException e) {
      request.setAttribute("error", e.getMessage());
      doGet(request, response);
    } catch (BankException e) {
      request.setAttribute("error", e.getMessage());
      e.printStackTrace();
      doGet(request, response);
    }
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
