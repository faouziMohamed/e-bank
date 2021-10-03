package com.mybank.controller;

import com.mybank.exception.NoClientConnectedException;
import com.mybank.model.Client;

import javax.servlet.http.HttpServletRequest;

import static com.mybank.controller.Utils.getClientFromHttpSession;

public interface ServletUtils {
  default Client assertThereIsClientConnected(HttpServletRequest request)
    throws NoClientConnectedException {
    Client client = getClientFromHttpSession(request);
    if (client == null) {
      throw new NoClientConnectedException();
    }
    return client;
  }
}
