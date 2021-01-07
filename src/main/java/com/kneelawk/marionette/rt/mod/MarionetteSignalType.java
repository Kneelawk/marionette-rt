package com.kneelawk.marionette.rt.mod;

public enum MarionetteSignalType {
    ONE_SHOT("OneShotSignal"),
    REUSABLE("ReusableSignal"),
    REPEATED("RepeatedSignal");

    private final String className;

    MarionetteSignalType(String className) {
        this.className = className;
    }

    public String getClassName() {
        return className;
    }
}
