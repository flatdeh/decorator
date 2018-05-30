package com.vlad.decorator;

import java.io.IOException;
import java.io.OutputStream;
import java.security.InvalidParameterException;
import java.util.Arrays;

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
    public void write(byte[] values) {
        write(values, 0, values.length);
    }

    @Override
    public void write(byte[] values, int off, int len) {
        if (len + count > array.length) {
            growCapacity(len + count - array.length);
        }
        System.arraycopy(values, off, array, count, len);
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

    public void writeTo(OutputStream out) throws IOException {
        out.write(array, 0, count);
    }

    public void reset() {
        count = 0;
    }

    private void growCapacity(int growSize) {
        byte[] newArray = new byte[array.length + growSize];
        System.arraycopy(array, 0, newArray, 0, array.length);
        array = newArray;
    }

    public static void main(String[] args) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        int[] intArray = {80, 81, 82, 83, 84, 85, 86, 87, 88, 89};

        byte[] byteArray = {80, 81, 82, 83, 84, 85, 86, 87, 88, 89};
        for (int i : intArray) {
            byteArrayOutputStream.write(i);
        }

        System.out.println(Arrays.toString(byteArrayOutputStream.toByteArray()));
        System.out.println(byteArrayOutputStream.toString());

        byteArrayOutputStream.write(byteArray);
        System.out.println(Arrays.toString(byteArrayOutputStream.toByteArray()));
        System.out.println(byteArrayOutputStream.toString());
    }
}
