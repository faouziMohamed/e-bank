package com.mybank.routes.api;

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
import java.util.ArrayList;
import java.util.TreeMap;

import static com.mybank.controller.Utils.*;

/**
 * API Route to get connected user's basic data.
 * <br><br>
 * <i>Note that this endpoint is designed for the current connected user
 * only: retrieving it's data.
 * <br>
 * Use <b>/api/account/data/id/&lt;userdId&gt;</b> instead to get other users data
 * </i>
 * <ul>
 *   <li> When no query passed the API send all data </li>
 * <li>if passed 'acctype' query : ?acctype=value, the api send only
 * queried data
 *   <ul>
 *     <li>
 *      value can be either : sva or cra (Other will trigger the default
 *      behavior)</li>
 *   </ul>
 *    </li>
 *    <li>
 *      If the <b>basic</b> flag is set(just present in the query) the response
 *      will contains only account ID and account name (wording)
 *      the account and wording
 *    </li>
 * </ul>
 */
@WebServlet(name = "UserApiServlet", value = "/api/user/data")
public class UserApiServlet extends HttpServlet implements ServletUtils {
  @Override
  protected void doGet(HttpServletRequest request,
                       HttpServletResponse response) throws ServletException,
                                                            IOException {
    try {
      Client client = assertThereIsClientConnected(request);
      TreeMap<String, ArrayList<String>> query = parseQueryString(request);
      if (query.size() == 0 || !query.containsKey("acctype")) {
        if (query.get("basic") != null) {
          sendResponseBasic(response, client);
          return;
        }
        sendAllData(response, client);
        return;
      }

      String accType = query.get("acctype").get(0);
      if (query.get("basic") != null) {
        sendBasicData(response, client, accType);
        return;
      }
      sendFilteredData(response, client, accType);
    } catch (NullPointerException | NoClientConnectedException e) {
      response.sendError(401, "You need to be connected in order to retrieve "
                                + "your data");
    }
  }

  private void sendResponseBasic(HttpServletResponse response, Client client) throws IOException {
    TreeMap<String, Client.BasicData> cra = client.getBasicData("cra");
    TreeMap<String, Client.BasicData> sva = client.getBasicData("sva");
    String craJson = new Gson().toJson(cra.values());
    String svaJson = new Gson().toJson(sva.values());
    String data = "{\"sva\":" + svaJson + ",\"cra\":" + craJson + "}";
    sendResponse(response, data);
  }

  private void sendAllData(HttpServletResponse response, Client client) throws IOException {
    String data;
    data = getAllAccountData(client);
    sendResponse(response, data);
  }

  private void sendBasicData(HttpServletResponse response, Client client,
                             String accType) throws IOException {
    String data;
    TreeMap<String, Client.BasicData> jData = client.getBasicData(accType);
    data = new Gson().toJson(jData.values());
    sendResponse(response, data);
  }

  private void sendFilteredData(HttpServletResponse response, Client client,
                                String accType) throws IOException {
    String data;
    System.out.println(accType);
    data = accType.equals("sva") ? createSvaJSONFromCLient(client) :
           createCraJSONFromClient(client);
    sendResponse(response, data);
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
