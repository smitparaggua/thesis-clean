package com.csg.warrior;

import org.junit.Test;

public class Spike {
    @Test
    public void test() {
        String test = Integer.toHexString(0x100);
        System.out.println(test);
    }

    @Test
    public void  multipleGenerate() {
        KeyStringGenerator generator = new KeyStringGenerator();
        for(int i = 0; i < 20; i++) {
            System.out.println(generator.generateKeyString());
        }
    }
}
