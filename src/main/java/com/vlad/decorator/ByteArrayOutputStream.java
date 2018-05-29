package com.vlad.decorator;

import sun.text.normalizer.UTF16;

import java.io.OutputStream;
import java.nio.charset.Charset;
import java.security.InvalidParameterException;
import java.util.Arrays;

public class ByteArrayOutputStream extends OutputStream {
    private static final int INITIAL_CAPACITY = 5;
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
    public void write(byte[] b) {

    }

    @Override
    public void write(byte[] b, int off, int len) {

    }

    public void write(int value) {
        if (count == array.length) {
            growCapacity();
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

    private void growCapacity() {
        byte[] newArray = new byte[count + 5];
        System.arraycopy(array, 0, newArray, 0, array.length);
        array = newArray;
    }

    public static void main(String[] args) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        int[] intArray = {80, 81, 82, 83, 84, 85, 86, 87, 88, 89};

        for (int i : intArray) {
            byteArrayOutputStream.write(i);
        }

        System.out.println(Arrays.toString(byteArrayOutputStream.toByteArray()));
        System.out.println(byteArrayOutputStream.toString());
    }
}
