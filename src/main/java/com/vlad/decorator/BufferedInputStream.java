package com.vlad.decorator;

import java.io.IOException;
import java.io.InputStream;

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
      count += inputStream.read(buffer,index,len-unreadCount);
      System.arraycopy(buffer, index, bytesArray, offset, len);
      //count = inputStream.read(buffer);
      //index = 0;
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
}
