package com.vlad.decorator;

import org.junit.Test;

import static org.junit.Assert.*;

public class StringBufferInputStreamTest {

    @Test
    public void readTest() {
        StringBufferInputStream stringBufferInputStream = new StringBufferInputStream("private void");
        assertEquals('p', stringBufferInputStream.read());
        assertEquals('r', stringBufferInputStream.read());
        assertEquals('i', stringBufferInputStream.read());
        assertEquals('v', stringBufferInputStream.read());
        assertEquals('a', stringBufferInputStream.read());
        assertEquals('t', stringBufferInputStream.read());
        assertEquals('e', stringBufferInputStream.read());
        assertEquals(' ', stringBufferInputStream.read());
        assertEquals('v', stringBufferInputStream.read());
        assertEquals('o', stringBufferInputStream.read());
        assertEquals('i', stringBufferInputStream.read());
        assertEquals('d', stringBufferInputStream.read());
        assertEquals(-1, stringBufferInputStream.read());
        assertEquals(-1, stringBufferInputStream.read());

    }

    @Test
    public void readTestWithParams() {
        StringBufferInputStream stringBufferInputStream = new StringBufferInputStream("private void");

        byte[] bytesArray = new byte[12];
        assertEquals(2, stringBufferInputStream.read(bytesArray, 2, 2));
        assertEquals('p', bytesArray[2]);
        assertEquals('r', bytesArray[3]);
        assertEquals(10, stringBufferInputStream.read(bytesArray, 0, 10));
        assertEquals(-1, stringBufferInputStream.read(bytesArray, 0, 10));

    }
}