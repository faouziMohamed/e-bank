package com.mybank.utils;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;

public class Utils {
  static public String toHex(String arg) {
    byte[] bytes = arg.getBytes(StandardCharsets.UTF_8);
    return String.format("%06x", new BigInteger(1, bytes));
  }
}
