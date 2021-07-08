package com.mabanque.presentation;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "Login", value = "/login")
public class Login extends HttpServlet {

  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, jakarta.servlet.ServletException {
    this.getServletContext().getRequestDispatcher("/view/login.jsp").forward(request, response);
  }

  @Override
  public void doPost(HttpServletRequest request,
    HttpServletResponse response) throws IOException,
    jakarta.servlet.ServletException {
    String name = request.getParameter("name");
    String pass = request.getParameter("pass");
    request.setAttribute("name", name);
    request.setAttribute("pass", pass);
    this.getServletContext().getRequestDispatcher("/view/login.jsp").forward(request, response);
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
