package com.vlad.decorator;

import java.io.InputStream;
import java.security.InvalidParameterException;
import java.util.Arrays;

public class StringBufferInputStream extends InputStream {
    private String string;
    private int index;

    public StringBufferInputStream(String string) {
        this.string = string;
    }

    @Override
    public int read(byte[] values, int off, int len) {
        if (off < 0 || len < 0 || values.length < len) {
            throw new InvalidParameterException();
        }
        if (string == null) {
            throw new NullPointerException();
        }
        if (string.length() < off + len) {
            throw new InvalidParameterException();
        }
        System.arraycopy(string.getBytes(), off, values, 0, len);
        return len;
    }

    public int read() {
        return index == string.length() ? -1 : string.charAt(index++);
    }

    public static void main(String[] args) {
        StringBufferInputStream stringBufferInputStream = new StringBufferInputStream("Hello world!!!");

        int v;
        while ((v = stringBufferInputStream.read()) != -1) {
            System.out.print(v+", ");
        }

        byte[] array = new byte[5];
        v = stringBufferInputStream.read(array,0,5);
        System.out.println(v);
        System.out.println(Arrays.toString(array));
    }
}
