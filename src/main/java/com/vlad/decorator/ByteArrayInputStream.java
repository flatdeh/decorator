package com.vlad.decorator;

import java.io.InputStream;
import java.security.InvalidParameterException;

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
    public int read(byte[] bytes, int off, int len) {
        if (off < 0 || len < 0 || bytes.length < len) {
            throw new InvalidParameterException();
        }
        if (buffer == null) {
            throw new NullPointerException();
        }
        if (buffer.length < off + len) {
            throw new InvalidParameterException();
        }

        System.arraycopy(buffer, off, bytes, 0, len);
        return len;
    }

    public int read() {
        return index == count ? -1 : buffer[index++];
    }

}
