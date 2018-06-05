package com.vlad.decorator;

import java.io.InputStream;

import static com.vlad.decorator.Validate.validateParameters;

public class ByteArrayInputStream extends InputStream {
  private byte[] buffer;
  private int index;

  public ByteArrayInputStream(byte[] bytes) {
    if (bytes == null) {
      throw new NullPointerException("bytes = null!");
    }
    this.buffer = bytes;
  }

  public ByteArrayInputStream(byte[] bytes, int offset, int length) {
    validateParameters(bytes, offset, length);

    this.buffer = new byte[length];
    System.arraycopy(bytes, offset, this.buffer, 0, length);
  }

  @Override
  public int read(byte[] bytes, int off, int len) {
    validateParameters(bytes, off, len);

    int unreadCount = buffer.length - index;

    if (index == buffer.length) {
      return -1;
    } else if (len >= unreadCount) {
      System.arraycopy(buffer, index, bytes, off, unreadCount);
      index += len;
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
