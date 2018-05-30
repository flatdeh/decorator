package com.vlad.decorator;

import java.io.IOException;
import java.io.OutputStream;
import java.security.InvalidParameterException;

public class BufferedOutputStream extends OutputStream {
    private static final int INITIAL_CAPACITY = 5;
    private OutputStream outputStream;
    private byte[] buffer;
    private int count;

    public BufferedOutputStream(OutputStream outputStream) {
        this(outputStream, INITIAL_CAPACITY);
    }

    public BufferedOutputStream(OutputStream outputStream, int bufferSize) {
        if (bufferSize <= 0) {
            throw new InvalidParameterException("Size should be larger 0!");
        }
        this.outputStream = outputStream;
        this.buffer = new byte[bufferSize];
    }

    @Override
    public void write(byte[] bytes) throws IOException {
        write(bytes, 0, bytes.length);
    }

    @Override
    public void write(byte[] bytes, int off, int len) throws IOException {
        if (bytes.length == buffer.length - count) {
            System.arraycopy(bytes, off, buffer, count, bytes.length);
            count += bytes.length;
            flush();
        } else if (bytes.length < buffer.length - count) {
            System.arraycopy(bytes, off, buffer, count, bytes.length);
            count += bytes.length;
        } else {
            for (int i = 0; i < len; i++) {
                if (count == buffer.length) {
                    flush();
                }
                buffer[count++] = bytes[off + i];
            }
        }
    }

    @Override
    public void flush() throws IOException {
        outputStream.write(buffer, 0, count);
        count = 0;
    }

    @Override
    public void close() throws IOException {
        flush();
        outputStream.close();
    }

    public void write(int value) throws IOException {
        if (count == buffer.length) {
            flush();
        }
        buffer[count++] = (byte) value;
    }
}
