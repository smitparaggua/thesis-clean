package com.csg.warrior.raydotcom;

import com.csg.warrior.raydotcom.domain.User;
import com.csg.warrior.raydotcom.service.impl.WarriorServiceImpl;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.XMLConfiguration;
import org.junit.Test;

public class Spike {
//    @Test
//    public void testSignUp() {
//        WarriorServiceImpl warriorService = new WarriorServiceImpl();
//        User user = new User();
//        user.setUsername("user");
//        user.setPassword("pass");
//        user.setEmail("email");
//        warriorService.signUpToWarrior("user");
//    }

    @Test
    public void xmlTest() {
        try {
            XMLConfiguration xmlConfiguration = new XMLConfiguration("warriorProperties.xml");
            System.out.println(xmlConfiguration.getString("address"));
            System.out.println(xmlConfiguration.getString("urls.sign-up"));
        } catch (ConfigurationException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }
}
