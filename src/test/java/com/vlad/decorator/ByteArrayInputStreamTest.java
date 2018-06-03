package com.vlad.decorator;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

public class ByteArrayInputStreamTest {

    @Test
    public void readTest() {
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream("Hello World!!!".getBytes());
        assertEquals('H',(char) byteArrayInputStream.read());
        assertEquals('e',(char) byteArrayInputStream.read());
        assertEquals('l',(char) byteArrayInputStream.read());
        assertEquals('l',(char) byteArrayInputStream.read());
        assertEquals('o',(char) byteArrayInputStream.read());
        assertEquals(' ',(char) byteArrayInputStream.read());
        assertEquals('W',(char) byteArrayInputStream.read());
        assertEquals('o',(char) byteArrayInputStream.read());
        assertEquals('r',(char) byteArrayInputStream.read());
        assertEquals('l',(char) byteArrayInputStream.read());
        assertEquals('d',(char) byteArrayInputStream.read());
        assertEquals('!',(char) byteArrayInputStream.read());
        assertEquals('!',(char) byteArrayInputStream.read());
        assertEquals('!',(char) byteArrayInputStream.read());
        assertEquals(-1, byteArrayInputStream.read());
        assertEquals(-1, byteArrayInputStream.read());

        byteArrayInputStream = new ByteArrayInputStream("Hello World!!!".getBytes(),6,5);
        assertEquals('W',(char) byteArrayInputStream.read());
        assertEquals('o',(char) byteArrayInputStream.read());
        assertEquals('r',(char) byteArrayInputStream.read());
        assertEquals('l',(char) byteArrayInputStream.read());
        assertEquals('d',(char) byteArrayInputStream.read());
        assertEquals(-1, byteArrayInputStream.read());
    }

    @Test
    public void readWithParameters() {
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream("Hello W".getBytes());
        byte[] byteArray = new byte[3];
        int count = byteArrayInputStream.read(byteArray,0,3);
        assertEquals(3, count);
        assertEquals('H',(char) byteArray[0]);
        assertEquals('e',(char) byteArray[1]);
        assertEquals('l',(char) byteArray[2]);

        count = byteArrayInputStream.read(byteArray,0,3);
        assertEquals(3, count);
        assertEquals('l',(char) byteArray[0]);
        assertEquals('o',(char) byteArray[1]);
        assertEquals(' ',(char) byteArray[2]);

        count = byteArrayInputStream.read(byteArray,0,3);
        assertEquals(1, count);
        assertEquals('W',(char) byteArray[0]);

    }

    @Test(expected = IllegalArgumentException.class)
    public void readTestWithInvalidParameterException() {
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream("Hello World!!!".getBytes(),123,2222);
    }

    @Test(expected = IllegalArgumentException.class)
    public void readTestWithInvalidParameterExceptionNegativeParameter() {
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream("Hello World!!!".getBytes(),-1,20);
    }

    @Test(expected = NullPointerException.class)
    public void readTestWithNullPointerException() {
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(null,0,0);
    }

    @Test
    public void readTestIsEmpty(){
        ByteArrayInputStream byteArrayInputStreamEmpty = new ByteArrayInputStream("".getBytes());
        assertEquals(-1, byteArrayInputStreamEmpty.read());

    }

    @Test
    public void readWithParameter() {
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream("Hello World!!!".getBytes());
        byte[] bytesArray = new byte[20];
        System.out.println(byteArrayInputStream.read(bytesArray,0,20));
        System.out.println(Arrays.toString(bytesArray));
    }
}