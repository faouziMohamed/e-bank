package com.mybank.controller;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

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
