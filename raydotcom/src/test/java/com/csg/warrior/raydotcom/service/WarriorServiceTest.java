package com.csg.warrior.raydotcom.service;

import com.csg.warrior.raydotcom.WarriorConfig;
import com.csg.warrior.raydotcom.service.impl.WarriorServiceImpl;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class WarriorServiceTest {
    private WarriorService warriorService = new WarriorServiceImpl();
    private WarriorConfig warriorConfig = new WarriorConfig();

    @Test
    public void interpretKeyNotUploadedReply() {
        String temp = warriorConfig.getKeyNotUploadedReply();
        boolean interpretation = warriorService.isWarriorLockedFromReply(temp);
        assertTrue(interpretation);
    }

    @Test
    public void interpretNullReply() {
        boolean interpretation = warriorService.isWarriorLockedFromReply(null);
        assertFalse(interpretation);
    }
}
