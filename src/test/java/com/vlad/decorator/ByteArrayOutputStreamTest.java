package com.vlad.decorator;

import org.junit.Test;

import static org.junit.Assert.*;

public class ByteArrayOutputStreamTest {

    @Test
    public void writeTest() {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(6);

        for (int i = 0; i < 10; i++) {
            byteArrayOutputStream.write(i);;
        }
        byte[] byteArray = byteArrayOutputStream.toByteArray();

        for (int i = 0; i < 10; i++) {
            assertEquals(i, byteArray[i]);
        }
    }

    @Test
    public void writeTestWithParams() {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        String expected = "56";
        byte[] byteArray = "0123456789".getBytes();

        byteArrayOutputStream.write(byteArray,5,2);
        assertEquals(expected, byteArrayOutputStream.toString());
    }
}