package com.mybank.routes.api;

import com.google.gson.Gson;
import com.mybank.routes.ServletUtils;
import com.mybank.exception.NoClientConnectedException;
import com.mybank.model.Client;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "UserApiServlet", value = "/userdata")
public class UserApiServlet extends HttpServlet implements ServletUtils {
  @Override
  protected void doGet(HttpServletRequest request,
                       HttpServletResponse response) throws ServletException,
                                                            IOException {
    try {
      Client client = assertThereIsClientConnected(request);
      Gson gson = new Gson();

      String svaJsonStr = gson.toJson(client.getAllSavingAccount().values());
      String craJsonStr = gson.toJson(client.getAllCurrentAccount().values());
      String jsonStr =
        "{\"sva\":" + svaJsonStr + ", \"cra\":" + craJsonStr + "}";
//      String data = gson.toJson(jsonStr);
      response.addHeader("Access-Control-Allow-Origin", "*");
      response.setContentType("application/json");
      response.setCharacterEncoding("UTF-8");
      response.getWriter().print(jsonStr);
      response.getWriter().flush();

    } catch (NullPointerException | NoClientConnectedException e) {
      response.sendError(401, "You need to be connected in order to retrieve " +
                                "your data");
    }
  }

  @Override
  protected void doPost(HttpServletRequest request,
                        HttpServletResponse response) throws ServletException, IOException {

  }
}
