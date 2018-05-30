package com.vlad.decorator;

import java.io.FileOutputStream;
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
    public void write(byte[] values) throws IOException {
        write(values, 0, values.length);
    }

    @Override
    public void write(byte[] values, int off, int len) throws IOException {
        if (values.length == buffer.length - count) {
            System.arraycopy(values, off, buffer, count, values.length);
            count += values.length;
            flush();
        } else if (values.length < buffer.length - count) {
            System.arraycopy(values, off, buffer, count, values.length);
            count += values.length;
        } else {
            for (int i = 0; i < len; i++) {
                if (count == buffer.length) {
                    flush();
                }
                buffer[count++] = values[off + i];
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

    public static void main(String[] args) throws IOException {
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream("test.txt"), 3);

        byte[] str = {80, 81, 82, 83, 84, 85};


        bufferedOutputStream.write(str);

        bufferedOutputStream.close();

    }
}
