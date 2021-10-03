package com.mybank.routes;

import com.mybank.controller.ServletUtils;
import com.mybank.exception.NoClientConnectedException;
import com.mybank.model.Client;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalTime;

import static com.mybank.controller.Utils.sendUserToLoginPage;

@WebServlet(name = "TransferServlet", value = "/transfer")
public class TransferServlet extends HttpServlet implements ServletUtils {
  @Override
  protected void doGet(HttpServletRequest request,
                       HttpServletResponse response) throws ServletException,
                                                            IOException {
    try {
      Client client = assertThereIsClientConnected(request);
      System.out.println(client.getUsername());
      int hour = LocalTime.now().getHour();
      request.setAttribute("hour", hour);
      request.setAttribute("client", client);
      request.setAttribute("data", client.getAllSavingAccount().values());
      this.getServletContext()
          .getRequestDispatcher("/WEB-INF/users/transfer.jsp")
          .forward(request, response);
    } catch (IllegalStateException | NullPointerException | NoClientConnectedException e) {
      request.setAttribute("error", "You need to connect in order to continue");
      sendUserToLoginPage(request, response);
    }

  }

  @Override
  protected void doPost(HttpServletRequest request,
                        HttpServletResponse response) throws ServletException, IOException {
    doGet(request, response);
  }
}
