package com.vlad.decorator;

import java.io.InputStream;
import java.security.InvalidParameterException;

public class StringBufferInputStream extends InputStream {
    private String string;
    private int index;

    public StringBufferInputStream(String string) {
        this.string = string;
    }

    @Override
    public int read(byte[] bytes, int off, int len) {
        if (off < 0 || len < 0 || bytes.length < len) {
            throw new InvalidParameterException();
        }
        if (string == null) {
            throw new NullPointerException();
        }
        if (string.length() < off + len) {
            throw new InvalidParameterException();
        }
        System.arraycopy(string.getBytes(), off, bytes, 0, len);
        return len;
    }

    public int read() {
        return index == string.length() ? -1 : string.charAt(index++);
    }
}
