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
            throw new InvalidParameterException("Size should be larger 0!");
        }
        this.array = new byte[size];
    }

    @Override
    public void write(byte[] bytes) {
        write(bytes, 0, bytes.length);
    }

    @Override
    public void write(byte[] bytes, int off, int len) {
        if (len + count > array.length) {
            growCapacity(len + count - array.length);
        }
        System.arraycopy(bytes, off, array, count, len);
        count += len;
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
