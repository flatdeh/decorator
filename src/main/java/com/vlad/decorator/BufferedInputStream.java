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
        this.inputStream = inputStream;
        this.buffer = new byte[bufferSize];
    }

    public int read() throws IOException {
        if (index == count) {
            count = inputStream.read(buffer);
            index = 0;
        }
        return count != -1 ? buffer[index++] : -1;
    }

    @Override
    public void close() throws IOException {
        inputStream.close();
    }
}
