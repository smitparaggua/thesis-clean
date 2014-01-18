package com.csg.warrior.raydotcom;

public class WarriorConfig {
    // TODO place the configurations in XML.

    public String getSignUpRL() {
        return "http://localhost:8080/sign-up";
    }

    public String getKeyNotUploadedReply() {
        return "User must upload mobile key";
    }

    public String getSignUpSuccessReply() {
        return "Sign up successful";
    }
}
