package com.vlad.decorator;

import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidParameterException;

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

    @Override
    public int read(byte[] bytes, int off, int len) {
        if (off < 0 || len < 0 || bytes.length < len || buffer.length < off + len) {
            throw new InvalidParameterException();
        }
//------>>>Тут косяк
        System.arraycopy(buffer,off,bytes,0,len);
        return len;
    }

    private void readFromInputStream() {

    }

    public int read() throws IOException {
        if (index == count) {
            count = inputStream.read(buffer, 0, buffer.length);
            index = 0;
        }
        return count != -1 ? buffer[index++] : -1;
    }

    @Override
    public void close() throws IOException {
        inputStream.close();
    }
}
