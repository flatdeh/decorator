package com.vlad.decorator;

import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import static org.junit.Assert.*;

public class BufferedInputStreamTest {

  @Test
  public void readTest() throws IOException {
    try (OutputStream outputStream = new FileOutputStream("test.log");
         BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream("test.log"))) {

      byte[] bytesArray = "Hello World".getBytes();
      outputStream.write(bytesArray);
      outputStream.flush();

      int count;
      int i = 0;
      while ((count = bufferedInputStream.read()) != -1) {
        assertEquals(count, bytesArray[i++]);
      }
      assertEquals(-1, count);
    }
  }

  @Test
  public void readTestWithOffset() throws IOException {
    try (BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream("test.log"));
         OutputStream outputStream = new FileOutputStream("test.log")) {

      byte[] bytesArray = "Hello World".getBytes();
      int offset = 0;
      outputStream.write(bytesArray, offset, 11);
      outputStream.flush();

      int count;
      while ((count = bufferedInputStream.read()) != -1) {
        assertEquals(count, bytesArray[offset++]);
      }
      assertEquals(-1, count);
    }
  }
}