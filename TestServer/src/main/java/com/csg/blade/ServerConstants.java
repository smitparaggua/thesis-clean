package com.csg.blade;

public class ServerConstants {
    private ServerConstants() {}

    public static final String PROTOCOL = "http";
    public static final int PORT = 8080;
    
    // This is the message for when a correct (username, website, BLADE Key, deviceID) is already BLADE registered
    public static final String messageDeviceAlreadyBLADERegistered = "From BLADE Server: device already BLADE registered";
    
    // This is the message for when a correct (username, website, BLADE key) is uploaded with the wrong deviceID
    public static final String messageIncorrectDevice = "From BLADE Server: this is not the registered device";	
    
    // This is the message when deleting a quad fails
    public static final String messageFailedToDeleteQuad = "Something went wrong.";
}
