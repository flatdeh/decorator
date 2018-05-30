package com.vlad.decorator;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

public class ByteArrayInputStream extends InputStream {
    private static final int INITIAL_CAPACITY = 5;
    private InputStream inputStream;
    private byte[] buffer;
    private int count;
    private int index;

    public ByteArrayInputStream(InputStream inputStream) {
        this(inputStream, INITIAL_CAPACITY);
    }

    public ByteArrayInputStream(InputStream inputStream, int bufferSize) {
        this.inputStream = inputStream;
        this.buffer = new byte[bufferSize];
    }

    @Override
    public int read(byte[] values) throws IOException {
        return read(values, 0, );
    }

    @Override
    public int read(byte[] values, int off, int len) throws IOException {
        if (index >= count) {
            count = inputStream.read(buffer, off, len);
            index = 0;
        }
        if (values.length >= buffer.length) {
            System.arraycopy(buffer, index, values, 0, buffer.length);
            index = buffer.length;
        } else if (values.length + index <= buffer.length) {
            System.arraycopy(buffer, index, values, 0, values.length);
            index = values.length;
        } else {
            System.arraycopy(buffer, index, values, 0, buffer.length - index);
            index = buffer.length;
        }
        return count;
    }

    public int read() throws IOException {
        if (index == count) {
            count = inputStream.read(buffer, 0, buffer.length);
            index = 0;
        }
        return count != -1 ? buffer[index++] : -1;
    }

    public static void main(String[] args) throws IOException {
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(new FileInputStream("test.txt"));
        byte[] array = new byte[10];
        while (byteArrayInputStream.read(array) != -1) {
            System.out.println(Arrays.toString(array).toCharArray());
        }
    }
}
