package com.vlad.decorator;

import java.io.InputStream;

import static com.vlad.decorator.Validate.validateParameters;

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

  public int read() {
    return index == buffer.length ? -1 : buffer[index++];
  }
}
