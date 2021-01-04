package com.kneelawk.marionette.rt.template;

import com.kneelawk.marionette.rt.instance.template.MinecraftClientInstanceBuilderTData;
import com.kneelawk.marionette.rt.instance.template.MinecraftClientInstanceTData;
import com.kneelawk.marionette.rt.instance.template.MinecraftServerInstanceBuilderTData;
import com.kneelawk.marionette.rt.instance.template.MinecraftServerInstanceTData;

import java.io.InputStream;

public class MarionetteTemplates {
    public static InputStream getFabricModJsonTemplate() {
        return MarionetteTemplates.class.getResourceAsStream("fabric.mod.json.vm");
    }

    public static InputStream getClientInstanceBuilderTemplate() {
        return MinecraftClientInstanceBuilderTData.class.getResourceAsStream("MinecraftClientInstanceBuilder.java.vm");
    }

    public static InputStream getClientInstanceTemplate() {
        return MinecraftClientInstanceTData.class.getResourceAsStream("MinecraftClientInstance.java.vm");
    }

    public static InputStream getServerInstanceBuilderTemplate() {
        return MinecraftServerInstanceBuilderTData.class.getResourceAsStream("MinecraftServerInstanceBuilder.java.vm");
    }

    public static InputStream getServerInstanceTemplate() {
        return MinecraftServerInstanceTData.class.getResourceAsStream("MinecraftServerInstance.java.vm");
    }
}
