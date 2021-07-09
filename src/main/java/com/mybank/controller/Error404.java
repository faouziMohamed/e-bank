package com.mybank.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "Error404", value = "/404")
public class Error404 extends HttpServlet {
  @Override
  protected void doGet(HttpServletRequest request,
    HttpServletResponse response) throws ServletException, IOException {
    this.getServletContext()
      .getRequestDispatcher("/WEB-INF/errors/404.jsp")
      .forward(request, response);
  }

  @Override
  protected void doPost(HttpServletRequest request,
    HttpServletResponse response) throws ServletException, IOException {

  }
}
