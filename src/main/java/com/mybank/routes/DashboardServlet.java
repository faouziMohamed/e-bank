package com.mybank.routes;

import com.google.gson.Gson;
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

@WebServlet(name = "DashboardServlet", value = "/dashboard")
public class DashboardServlet extends HttpServlet implements ServletUtils {
  @Override
  protected void doGet(HttpServletRequest request,
                       HttpServletResponse response) throws ServletException,
                                                            IOException {

    try {
      Client client = assertThereIsClientConnected(request);
      int hour = LocalTime.now().getHour();
      request.setAttribute("hour", hour);
      request.setAttribute("client", client);
      request.setAttribute("data", client.getAllSavingAccount().values());

      Gson gson = new Gson();

      String svaJsonStr = gson.toJson(client.getAllSavingAccount().values());
      String craJsonStr = gson.toJson(client.getAllCurrentAccount().values());
      request.setAttribute("sva", svaJsonStr);
      request.setAttribute("cra", craJsonStr);

      ShowDashboardPage(request, response);
    } catch (IllegalStateException | NullPointerException | NoClientConnectedException e) {
      request.setAttribute("error", "You need to connect in order to continue");
      sendUserToLoginPage(request, response);
    }
  }

  private void ShowDashboardPage(HttpServletRequest request,
                                 HttpServletResponse response) throws ServletException, IOException {
    this.getServletContext().getRequestDispatcher("/WEB-INF/users/dashboard" + ".jsp").forward(request, response);
  }

  @Override
  protected void doPost(HttpServletRequest request,
                        HttpServletResponse response) throws ServletException, IOException {
    doGet(request, response);
  }
}
