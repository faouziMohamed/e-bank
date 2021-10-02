package com.mybank.controller;

import com.mybank.exception.NoClientConnectedException;
import com.mybank.model.Client;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public interface ServletUtils {
  default Client assertThereIsClientConnected(HttpServletRequest request)
    throws NoClientConnectedException {
    Client client = getClientFromHttpSession(request);
    if (client == null) {
      throw new NoClientConnectedException();
    }
    return client;
  }

  default Client getClientFromHttpSession(HttpServletRequest request) {
    HttpSession session = request.getSession(false);
    return (Client) session.getAttribute("client");
  }

  default void sendUserToLoginPage(HttpServletRequest request,
                                   HttpServletResponse response) throws IOException {
    response.sendRedirect(request.getContextPath() + "/login");
  }

  default String getFullPath(HttpServletRequest request, String path) {
    return request.getContextPath() + path;
  }
}
