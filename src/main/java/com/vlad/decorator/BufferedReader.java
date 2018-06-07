package com.vlad.decorator;

import java.io.IOException;
import java.io.Reader;

public class BufferedReader extends Reader {

  @Override
  public int read(char[] chars, int i, int i1) throws IOException {
    return 0;
  }

  @Override
  public void close() throws IOException {

  }

  @Override
  public int read() throws IOException {


    return super.read();
  }
}
