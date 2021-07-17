package com.mybank.controller.http.errors;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "Error404Servlet", value = "/401")
public class Error401Servlet extends HttpServlet {
  @Override
  protected void doGet(HttpServletRequest request,
                       HttpServletResponse response) throws ServletException,
                                                            IOException {
    this.getServletContext()
        .getRequestDispatcher("/WEB-INF/errors/401.jsp")
        .forward(request, response);
  }
}
