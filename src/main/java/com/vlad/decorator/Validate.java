package com.vlad.decorator;

public class Validate {
  public static void validateParameters(byte[] bytes, int off, int len) {
    if (bytes == null) {
      throw new NullPointerException("bytes = null!");
    }
    if (off < 0) {
      throw new IllegalArgumentException("off should be >= 0! off = " + off);
    }
    if (len <= 0) {
      throw new IllegalArgumentException("len should be > 0! len = " + len);
    }
    if (bytes.length < off + len) {
      throw new IllegalArgumentException("off + len should be <= bytes array length!");
    }
  }
}
