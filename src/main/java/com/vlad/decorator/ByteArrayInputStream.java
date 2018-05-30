package com.vlad.decorator;

import java.io.InputStream;
import java.security.InvalidParameterException;
import java.util.Arrays;

public class ByteArrayInputStream extends InputStream {
    private byte[] buffer;
    private int count;
    private int index;

    public ByteArrayInputStream(byte[] buffer) {
        this(buffer, 0, buffer.length);
    }

    public ByteArrayInputStream(byte[] buffer, int offset, int length) {
        this.buffer = buffer;
        this.index = offset;
        this.count = offset + length;
    }

    @Override
    public int read(byte[] values, int off, int len) {
        if (off < 0 || len < 0 || values.length < len) {
            throw new InvalidParameterException();
        }
        if (buffer == null) {
            throw new NullPointerException();
        }
        if (buffer.length < off + len) {
            throw new InvalidParameterException();
        }

        System.arraycopy(buffer, off, values, 0, len);
        return len;
    }

    public int read() {
        return index == count ? -1 : buffer[index++];
    }

    public static void main(String[] args) {
        byte[] array = {80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90};
        byte[] array2 = new byte[1];
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(array);
        int v;
        while ((v = byteArrayInputStream.read()) != -1) {
            System.out.print(v + " ");
        }

        v = byteArrayInputStream.read(array2, 2, 1);
        System.out.println(v);
        System.out.println(Arrays.toString(array2));
    }
}
