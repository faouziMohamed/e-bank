package com.mybank.controller;

import com.mybank.exception.NoClientConnectedException;
import com.mybank.model.Client;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

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
}
