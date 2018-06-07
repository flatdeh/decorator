package com.vlad.decorator;

import org.junit.Test;

import java.io.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;

import static org.junit.Assert.*;

public class BufferedReaderTest {
  @Test
  public void readTest() throws IOException {
    try (Writer writer = new BufferedWriter(new FileWriter("writer.txt"));
         Reader reader = new BufferedReader(new FileReader("writer.txt"));) {
      assertEquals(-1, reader.read());
      writer.write("Buffered Writer");
      writer.flush();

      int count;
      while ((count = reader.read()) != -1) {
        System.out.print((char) count);
      }

      new File("writer.txt").delete();
    }
  }

}