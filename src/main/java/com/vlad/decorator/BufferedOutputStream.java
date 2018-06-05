package com.vlad.decorator;

import java.io.IOException;
import java.io.OutputStream;
import java.security.InvalidParameterException;

public class BufferedOutputStream extends OutputStream {
  private static final int INITIAL_CAPACITY = 5;
  private OutputStream outputStream;
  private byte[] buffer;
  private int count;

  public BufferedOutputStream(OutputStream outputStream) {
    this(outputStream, INITIAL_CAPACITY);
  }

  public BufferedOutputStream(OutputStream outputStream, int bufferSize) {
    if (outputStream == null) {
      throw new NullPointerException("OutputStream = null");
    }
    if (bufferSize <= 0) {
      throw new InvalidParameterException("Size should be larger 0!");
    }
    this.outputStream = outputStream;
    this.buffer = new byte[bufferSize];
  }

  @Override
  public void write(byte[] bytes) throws IOException {
    if (bytes == null) {
      throw new NullPointerException("bytes = null");
    }
    write(bytes, 0, bytes.length);
  }

  @Override
  public void write(byte[] bytes, int off, int len) throws IOException {
    validateParameters(bytes, off, len);

    int emptySpace = buffer.length - count;

    if (emptySpace <= len) {
      flush();
      outputStream.write(bytes, off, len);
    } else {
      System.arraycopy(bytes, off, buffer, count, len);
      count += len;
    }
  }

  @Override
  public void flush() throws IOException {
    if (count != 0) {
      outputStream.write(buffer, 0, count);
      count = 0;
    }
  }

  @Override
  public void close() throws IOException {
    flush();
    outputStream.close();
  }

  public void write(int value) throws IOException {
    buffer[count++] = (byte) value;
    if (count == buffer.length) {
      flush();
    }
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
