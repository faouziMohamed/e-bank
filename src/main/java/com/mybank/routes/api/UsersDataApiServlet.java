package com.mybank.routes.api;

import com.mybank.controller.ServletUtils;
import com.mybank.exception.NoClientConnectedException;
import com.mybank.model.Client;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "UsersApiData", value =  "/api/account/data/id/*")
public class UsersDataApiServlet extends HttpServlet implements ServletUtils {
  @Override
  protected void doGet(HttpServletRequest request,
                       HttpServletResponse response) throws ServletException,
                                                            IOException {
    try {
      Client client = assertThereIsClientConnected(request);
      //      TreeMap<String, ArrayList<String>> query = parseQueryString
      //      (request);
      //      if (query.size() == 0 || !query.containsKey("acctype")) {
      //        if (query.get("basic") != null) {
      //          sendResponseBasic(response, client);
      //          return;
      //        }
      //        sendAllData(response, client);
      //        return;
      //      }
      //
      //      String accType = query.get("acctype").get(0);
      //      if (query.get("basic") != null) {
      //        sendBasicData(response, client, accType);
      //        return;
      //      }
      //      sendFilteredData(response, client, accType);
      sendResponse(response, "{\"name\":\"Faouzi\"}");
    } catch (NullPointerException | NoClientConnectedException e) {
      response.sendError(401, "You need to be connected in order to retrieve "
                                + "your data");
    }

  }

  private void sendResponse(HttpServletResponse response, String data) throws IOException {
    response.addHeader("Access-Control-Allow-Origin", "*");
    response.setContentType("application/json");
    response.setCharacterEncoding("UTF-8");
    response.getWriter().print(data);
    response.getWriter().flush();
  }

  @Override
  protected void doPost(HttpServletRequest request,
                        HttpServletResponse response) throws ServletException, IOException {

  }
}
