package com.vlad.decorator;

import java.io.OutputStream;
import java.security.InvalidParameterException;

import static com.vlad.decorator.Validate.validateParameters;

public class ByteArrayOutputStream extends OutputStream {
  private static final int INITIAL_CAPACITY = 5;
  private static final int DEFAULT_GROW_SIZE = 5;
  private byte[] array;
  private int count;

  public ByteArrayOutputStream() {
    this(INITIAL_CAPACITY);
  }

  public ByteArrayOutputStream(int size) {
    if (size <= 0) {
      throw new InvalidParameterException("Size should be > 0! size: " + size);
    }
    this.array = new byte[size];
  }


  @Override
  public void write(byte[] bytes, int off, int len) {
    validateParameters(bytes, off, len);

    int emptySize = array.length - count;

    if (len > emptySize) {
      growCapacity(len - emptySize + DEFAULT_GROW_SIZE);
    }
    System.arraycopy(bytes, off, array, count, len);
    count += len;
  }

  @Override
  public void write(int value) {
    if (count == array.length) {
      growCapacity(DEFAULT_GROW_SIZE);
    }
    array[count++] = (byte) value;
  }

  public byte[] toByteArray() {
    byte[] newArray = new byte[count];
    System.arraycopy(array, 0, newArray, 0, count);
    return newArray;
  }

  public String toString() {
    return new String(array, 0, count);
  }

  private void growCapacity(int growSize) {
    byte[] newArray = new byte[array.length + growSize];
    System.arraycopy(array, 0, newArray, 0, array.length);
    array = newArray;
  }

}
