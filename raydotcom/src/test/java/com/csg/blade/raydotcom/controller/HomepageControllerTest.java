package com.csg.blade.raydotcom.controller;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class HomepageControllerTest {
    @Test
    public void retrieveHomePage() {
        HomepageController homepageController = new HomepageController();
        assertEquals("homepage", homepageController.showHomePage());
    }
}
