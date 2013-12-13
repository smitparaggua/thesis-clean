package com.csg.warrior.raydotcom.controller;

import com.csg.warrior.raydotcom.controller.HomepageController;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class HomepageControllerTest {
    @Test
    public void retrieveHomePage() {
        HomepageController homepageController = new HomepageController();
        assertEquals("homepage", homepageController.showHomePage());
    }
}
