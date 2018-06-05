package com.vlad.decorator;

import org.junit.Test;



import static org.junit.Assert.*;

public class ByteArrayInputStreamTest {

  @Test
  public void readTest() {
    String testText = "Hello World!!!";
    byte[] bytesArray = testText.getBytes();
    ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytesArray);

    for (byte b : bytesArray) {
      assertEquals(b, byteArrayInputStream.read());
    }
    assertEquals(-1, byteArrayInputStream.read());
    assertEquals(-1, byteArrayInputStream.read());

    int offset = 6;
    int length = 5;

    byteArrayInputStream = new ByteArrayInputStream(bytesArray, offset, length);
    for (int i = 0; i < length; i++) {
      assertEquals(bytesArray[offset + i], byteArrayInputStream.read());
    }
    assertEquals(-1, byteArrayInputStream.read());
  }

  @Test
  public void readWithParameters() {
    ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream("Hello W".getBytes());
    byte[] byteArray = new byte[3];
    int count = byteArrayInputStream.read(byteArray, 0, 3);
    assertEquals(3, count);
    assertEquals('H', (char) byteArray[0]);
    assertEquals('e', (char) byteArray[1]);
    assertEquals('l', (char) byteArray[2]);

    count = byteArrayInputStream.read(byteArray, 0, 3);
    assertEquals(3, count);
    assertEquals('l', (char) byteArray[0]);
    assertEquals('o', (char) byteArray[1]);
    assertEquals(' ', (char) byteArray[2]);

    count = byteArrayInputStream.read(byteArray, 0, 3);
    assertEquals(1, count);
    assertEquals('W', (char) byteArray[0]);

  }

  @Test(expected = IllegalArgumentException.class)
  public void readTestWithInvalidParameterException() {
    ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream("Hello World!!!".getBytes(), 123, 2222);
  }

  @Test(expected = IllegalArgumentException.class)
  public void readTestWithInvalidParameterExceptionNegativeParameter() {
    ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream("Hello World!!!".getBytes(), -1, 20);
  }

  @Test(expected = NullPointerException.class)
  public void readTestWithNullPointerException() {
    ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(null, 0, 0);
  }

  @Test
  public void readTestIsEmpty() {
    ByteArrayInputStream byteArrayInputStreamEmpty = new ByteArrayInputStream("".getBytes());
    assertEquals(-1, byteArrayInputStreamEmpty.read());

  }

  @Test
  public void readWithParameter() {
    ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream("Hello World!!!".getBytes());

    byte[] bytesArray = new byte[14];
    assertEquals(2, byteArrayInputStream.read(bytesArray, 2, 2));
    assertEquals('H', bytesArray[2]);
    assertEquals('e', bytesArray[3]);
    assertEquals(12, byteArrayInputStream.read(bytesArray, 0, 12));
    assertEquals(-1, byteArrayInputStream.read(bytesArray, 0, 10));

  }
}