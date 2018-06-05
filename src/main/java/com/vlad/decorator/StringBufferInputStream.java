package com.vlad.decorator;

import java.io.InputStream;

public class StringBufferInputStream extends InputStream {
  private byte[] buffer;
  private int index;

  public StringBufferInputStream(String string) {
    if (string == null) {
      throw new NullPointerException("String = null");
    }
    this.buffer = string.getBytes();
  }

  @Override
  public int read(byte[] bytes, int off, int len) {
    validateParameters(bytes, off, len);

    int unreadCount = buffer.length - index;

    if (index == buffer.length) {
      return -1;
    } else if (len >= unreadCount) {
      System.arraycopy(buffer, index, bytes, off, unreadCount);
      index += unreadCount;
      return unreadCount;
    } else {
      System.arraycopy(buffer, index, bytes, off, len);
      index += len;
    }
    return len;
  }

  private void validateParameters(byte[] bytes, int off, int len) {
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

  public int read() {
    return index == buffer.length ? -1 : buffer[index++];
  }
}
