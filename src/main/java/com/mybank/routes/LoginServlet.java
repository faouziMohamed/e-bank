package com.mybank.routes;

import com.mybank.controller.ServletUtils;
import com.mybank.exception.BankException;
import com.mybank.exception.NoClientConnectedException;
import com.mybank.model.BankService;
import com.mybank.model.Client;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.mybank.controller.Utils.getFullPath;

@WebServlet(name = "LoginServlet", value = "/login")
public class LoginServlet extends HttpServlet implements ServletUtils {

  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
    try {
      // IF there a client already connected this will
      // redirect User to it dashboard
      assertThereIsClientConnected(request);
      String mainPage = getFullPath(request, "/dashboard");
      redirectUserToMainPage(response, mainPage);
    } catch (NoClientConnectedException | NullPointerException ignored) {

      request.setAttribute("title", "Authentication page");
      request.setAttribute("pageHeading", "Welcome to your online Bank");
      this
        .getServletContext()
        .getRequestDispatcher("/WEB-INF/login.jsp")
        .forward(request, response);
    }

  }

  @Override
  public void doPost(HttpServletRequest request,
                     HttpServletResponse response)
    throws IOException, ServletException {
    try {
      String username = getUsername(request);
      String password = getPassword(request);
      Client client = getClientFromDB(username, password);
      acceptAuthentication(request, client);
      String mainPage = getFullPath(request, "/dashboard");
      redirectUserToMainPage(response, mainPage);
    } catch (BankException exception) {
      String errorMessage = exception.getMessage();
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
