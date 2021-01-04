package com.kneelawk.marionette.rt;

import java.io.IOException;
import java.util.Properties;

public class MarionetteConstants {
    public static final String ENVIRONMENT_PROPERTY = "com.kneelawk.marionette.env";
    public static final String INSTANCE_NAME_PROPERTY = "com.kneelawk.marionette.instance_name";
    public static final String RMI_PORT_PROPERTY = "com.kneelawk.marionette.rmi_port";
    public static final String MINECRAFT_CLASSPATH_PROPERTY = "com.kneelawk.marionette.minecraft_classpath";
    public static final String PROJECT_ROOT_DIR_PROPERTY = "com.kneelawk.marionette.project_root_dir";
    public static final String PROJECT_DIR_PROPERTY = "com.kneelawk.marionette.project_dir";
    public static final String PROJECT_BUILD_DIR_PROPERTY = "com.kneelawk.marionette.project_build_dir";

    public static final String MARIONETTE_VERSION;

    static {
        Properties properties = new Properties();
        try {
            properties.load(MarionetteConstants.class.getResourceAsStream("marionette-rt.properties"));
        } catch (IOException e) {
            throw new IllegalStateException("Unable to load marionette-rt.properties", e);
        }
        MARIONETTE_VERSION = properties.getProperty("marionette_version");
    }
}
