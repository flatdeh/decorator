package com.vlad.decorator;

import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import static org.junit.Assert.*;

public class BufferedOutputStreamTest {

  @Test
  public void write() throws IOException {
    try (BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream("test.log"));
         InputStream inputStream = new FileInputStream("test.log")) {
      byte[] content = "Hello World".getBytes();

      for (byte b : content) {
        bufferedOutputStream.write(b);
      }

      int count;
      int i = 0;
      while ((count = inputStream.read()) != -1) {
        assertEquals(content[i++], count);
      }
      assertEquals(-1, count);
    }
  }

  @Test
  public void writeWithArray() throws IOException {
    try (BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream("test.log"));
         InputStream inputStream = new FileInputStream("test.log")) {
      byte[] content = "Hello World".getBytes();

      bufferedOutputStream.write(content);

      int count;
      int i = 0;
      while ((count = inputStream.read()) != -1) {
        assertEquals(content[i++], count);
      }
      assertEquals(-1, count);
    }
  }

  @Test
  public void writeWithOffset() throws IOException {
    try (BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream("test.log"));
         InputStream inputStream = new FileInputStream("test.log")) {
      byte[] content = "Hello World".getBytes();
      int offset = 6;
      bufferedOutputStream.write(content, offset, 5);

      int count;
      while ((count = inputStream.read()) != -1) {
        assertEquals(content[offset++], count);
      }
      assertEquals(-1, count);
    }
  }
}