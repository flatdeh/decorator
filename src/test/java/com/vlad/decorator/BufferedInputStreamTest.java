package com.vlad.decorator;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import static org.junit.Assert.*;

public class BufferedInputStreamTest {
    private String stringArray = "Hello World!!!";

    @Before
    public void setUp() throws Exception {
        //BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(stringArray));
    }

    @Test
    public void read() throws IOException {
        BufferedInputStream bufferedInputStream = new BufferedInputStream(new ByteArrayInputStream(stringArray.getBytes()));
        Assert.assertEquals('H', (char) bufferedInputStream.read());
    }

    @Test
    public void read1() {
    }
}