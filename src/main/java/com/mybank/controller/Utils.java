package com.mybank.controller;

import com.google.gson.Gson;
import com.mybank.model.Client;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.TreeMap;

public class Utils {
  static public String getAllAccountData(Client client) {
    String data;
    String svaJsonStr = createSvaJSONFromCLient(client);
    String craJsonStr = createCraJSONFromClient(client);
    data = "{\"sva\":" + svaJsonStr + ", \"cra\":" + craJsonStr + "}";
    return data;
  }

  static public String createCraJSONFromClient(Client client) {
    return new Gson().toJson(client.getAllCurrentAccount().values());
  }

  static public String createSvaJSONFromCLient(Client client) {
    return new Gson().toJson(client.getAllSavingAccount().values());
  }

  static public TreeMap<String, ArrayList<String>> parseQueryString(HttpServletRequest request) throws UnsupportedEncodingException {
    String queryString;
    String[] parameters;
    TreeMap<String, ArrayList<String>> queryParameters = new TreeMap<>();
    try {
      queryString = request.getQueryString();
      queryString = URLDecoder.decode(queryString, "UTF-8").toLowerCase();
      parameters = queryString.split("&");
    } catch (NullPointerException e) {
      return queryParameters;
    }

    for (String parameter : parameters) {
      String[] keyValuePair = parameter.split("=");
      ArrayList<String> values = new ArrayList<String>();
      if (keyValuePair.length == 1) { values.add(""); }
      else {
        Collections
          .addAll(values, keyValuePair[ 1 ].split(","));
      }
      queryParameters.put(keyValuePair[ 0 ], values);
    }
    return queryParameters;
  }


  static public Client getClientFromHttpSession(HttpServletRequest request) {
    HttpSession session = request.getSession(false);
    return (Client) session.getAttribute("client");
  }

  static public void sendUserToLoginPage(HttpServletRequest request,
                                   HttpServletResponse response) throws IOException {
    response.sendRedirect(request.getContextPath() + "/login");
  }

  static public String getFullPath(HttpServletRequest request, String path) {
    return request.getContextPath() + path;
  }
}
