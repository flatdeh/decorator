package com.vlad.decorator;

import org.junit.Test;

import static org.junit.Assert.*;

public class StringBufferInputStreamTest {
  private String testText = "private void";

  @Test
  public void readTest() {
    StringBufferInputStream stringBufferInputStream = new StringBufferInputStream(testText);

    for (int i = 0; i < testText.length(); i++) {
      assertEquals(testText.charAt(i), stringBufferInputStream.read());
    }
    assertEquals(-1, stringBufferInputStream.read());
    assertEquals(-1, stringBufferInputStream.read());
  }

  @Test
  public void readTestWithParams() {
    StringBufferInputStream stringBufferInputStream = new StringBufferInputStream(testText);

    byte[] bytesArray = new byte[14];
    assertEquals(2, stringBufferInputStream.read(bytesArray, 2, 2));
    assertEquals('p', bytesArray[2]);
    assertEquals('r', bytesArray[3]);
    assertEquals(10, stringBufferInputStream.read(bytesArray, 0, 10));
    assertEquals(-1, stringBufferInputStream.read(bytesArray, 0, 10));
  }
}