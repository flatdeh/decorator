package com.vlad.decorator;

import java.io.IOException;
import java.io.InputStream;

import static com.vlad.decorator.Validate.validateParameters;

public class BufferedInputStream extends InputStream {
  private static final int INITIAL_CAPACITY = 5;
  private InputStream inputStream;
  private byte[] buffer;
  private int index;
  private int count;

  public BufferedInputStream(InputStream inputStream) {
    this(inputStream, INITIAL_CAPACITY);
  }

  public BufferedInputStream(InputStream inputStream, int bufferSize) {
    if (inputStream == null) {
      throw new NullPointerException("InputStream = null");
    }

    if (bufferSize <= 0) {
      throw new IllegalArgumentException("bufferSize should be > 0, buffer = " + bufferSize);
    }

    this.inputStream = inputStream;
    this.buffer = new byte[bufferSize];
  }

  public int read(byte[] bytesArray, int offset, int len) throws IOException {
    validateParameters(bytesArray, offset, len);

    if (index == count) {
      count = inputStream.read(buffer);
      index = 0;
    }

    int unreadCount = count - index;

    if (count == -1) {
      return -1;
    } else if (len <= unreadCount) {
      System.arraycopy(buffer, index, bytesArray, offset, len);
      index += len;
      return len;
    } else {
      System.arraycopy(buffer, index, bytesArray, offset, unreadCount);
      count = inputStream.read(buffer);
      index = 0;
      return len;
    }

  }

  public int read() throws IOException {
    if (index == count) {
      count = inputStream.read(buffer);
      index = 0;
    }
    return count == -1 ? -1 : buffer[index++];
  }

  @Override
  public void close() throws IOException {
    inputStream.close();
  }


}
