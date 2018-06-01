package com.vlad.decorator;

import java.io.OutputStream;
import java.security.InvalidParameterException;

public class ByteArrayOutputStream extends OutputStream {
    private static final int INITIAL_CAPACITY = 5;
    private static final int DEFAULT_GROW_SIZE = 5;
    private byte[] array;
    private int count;

    public ByteArrayOutputStream() {
        this(INITIAL_CAPACITY);
    }

    public ByteArrayOutputStream(int size) {
        if (size <= 0) {
            throw new InvalidParameterException("Size should > 0! size: " + size);
        }
        this.array = new byte[size];
    }


    @Override

    public void write(byte[] bytes, int off, int len) {
        validateParameters(bytes, off, len);

        int emptySize = array.length - count;

        if (len <= emptySize) {
            System.arraycopy(bytes, off, array, count, len);
            count += len;
        } else {
            growCapacity(len - emptySize);
            System.arraycopy(bytes, off, array, count, len);
            count += len;
        }
    }

    private void validateParameters(byte[] bytes, int off, int len) {
        if (bytes == null) {
            throw new NullPointerException("bytes = null!");
        }
        if (off < 0) {
            throw new IllegalArgumentException("off should be >= 0! off = " + off);
        }
        if (len <= 0) {
            throw new IllegalArgumentException("len should be > 0! len = " + len);
        }
        if (bytes.length < off + len) {
            throw new IllegalArgumentException("off + len should be <= bytes array length!");
        }
    }

    public void write(int value) {
        if (count == array.length) {
            growCapacity(DEFAULT_GROW_SIZE);
        }
        array[count++] = (byte) value;
    }

    public byte[] toByteArray() {
        byte[] newArray = new byte[count];
        System.arraycopy(array, 0, newArray, 0, array.length);
        return newArray;
    }

    public String toString() {
        return new String(array, 0, count);
    }

    private void growCapacity(int growSize) {
        byte[] newArray = new byte[array.length + growSize];
        System.arraycopy(array, 0, newArray, 0, array.length);
        array = newArray;
    }

}
