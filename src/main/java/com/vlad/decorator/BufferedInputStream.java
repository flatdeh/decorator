package com.vlad.decorator;

import java.io.IOException;
import java.io.InputStream;

public class BufferedInputStream extends InputStream {
    private static final int INITIAL_CAPACITY = 5;
    private InputStream inputStream;
    private byte[] buffer;

    public BufferedInputStream(InputStream inputStream) {
        this(inputStream, INITIAL_CAPACITY);
    }

    public BufferedInputStream(InputStream inputStream, int bufferSize) {
        this.inputStream = inputStream;
        this.buffer = new byte[bufferSize];
    }

    @Override
    public int read(byte[] b) throws IOException {
        return super.read(b);
    }

    @Override
    public int read(byte[] b, int off, int len) throws IOException {
        return super.read(b, off, len);
    }

    @Override
    public void close() throws IOException {
        inputStream.close();
    }

    public int read() throws IOException {
        inputStream.read();
        return 0;
    }
}
