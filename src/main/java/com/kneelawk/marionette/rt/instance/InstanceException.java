package com.kneelawk.marionette.rt.instance;

/**
 * Exception thrown when an error occurs or is detected in a Minecraft instance.
 */
public class InstanceException extends Exception {
    public InstanceException(String message) {
        super(message);
    }
}
