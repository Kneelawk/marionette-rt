package com.kneelawk.marionette.rt.template;

import com.kneelawk.marionette.rt.callback.template.CallbackTData;
import com.kneelawk.marionette.rt.instance.template.MinecraftClientInstanceBuilderTData;
import com.kneelawk.marionette.rt.instance.template.MinecraftClientInstanceTData;
import com.kneelawk.marionette.rt.instance.template.MinecraftServerInstanceBuilderTData;
import com.kneelawk.marionette.rt.instance.template.MinecraftServerInstanceTData;
import com.kneelawk.marionette.rt.mod.client.template.ClientGlobalQueuesTData;
import com.kneelawk.marionette.rt.mod.client.template.ClientGlobalSignalsTData;
import com.kneelawk.marionette.rt.mod.client.template.MarionetteClientPreLaunchTData;
import com.kneelawk.marionette.rt.mod.client.template.MinecraftClientAccessTData;
import com.kneelawk.marionette.rt.mod.server.template.MarionetteServerPreLaunchTData;
import com.kneelawk.marionette.rt.mod.server.template.MinecraftServerAccessTData;
import com.kneelawk.marionette.rt.mod.server.template.ServerGlobalQueuesTData;
import com.kneelawk.marionette.rt.mod.server.template.ServerGlobalSignalsTData;
import com.kneelawk.marionette.rt.mod.template.MarionetteModPreLaunchTData;
import com.kneelawk.marionette.rt.proxy.template.ProxyImplementationTData;
import com.kneelawk.marionette.rt.proxy.template.ProxyInterfaceTData;
import com.kneelawk.marionette.rt.rmi.template.RMIMinecraftClientAccessTData;
import com.kneelawk.marionette.rt.rmi.template.RMIMinecraftServerAccessTData;

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

    public static InputStream getRMIClientAccessTemplate() {
        return RMIMinecraftClientAccessTData.class.getResourceAsStream("RMIMinecraftClientAccess.java.vm");
    }

    public static InputStream getRMIServerAccessTemplate() {
        return RMIMinecraftServerAccessTData.class.getResourceAsStream("RMIMinecraftServerAccess.java.vm");
    }

    public static InputStream getModPreLaunchTemplate() {
        return MarionetteModPreLaunchTData.class.getResourceAsStream("MarionetteModPreLaunch.java.vm");
    }

    public static InputStream getClientPreLaunchTemplate() {
        return MarionetteClientPreLaunchTData.class.getResourceAsStream("MarionetteClientPreLaunch.java.vm");
    }

    public static InputStream getServerPreLaunchTemplate() {
        return MarionetteServerPreLaunchTData.class.getResourceAsStream("MarionetteServerPreLaunch.java.vm");
    }

    public static InputStream getClientAccessTemplate() {
        return MinecraftClientAccessTData.class.getResourceAsStream("MinecraftClientAccess.java.vm");
    }

    public static InputStream getServerAccessTemplate() {
        return MinecraftServerAccessTData.class.getResourceAsStream("MinecraftServerAccess.java.vm");
    }

    public static InputStream getClientGlobalSignalsTemplate() {
        return ClientGlobalSignalsTData.class.getResourceAsStream("ClientGlobalSignals.java.vm");
    }

    public static InputStream getServerGlobalSignalsTemplate() {
        return ServerGlobalSignalsTData.class.getResourceAsStream("ServerGlobalSignals.java.vm");
    }

    public static InputStream getClientGlobalQueuesTemplate() {
        return ClientGlobalQueuesTData.class.getResourceAsStream("ClientGlobalQueues.java.vm");
    }

    public static InputStream getServerGlobalQueuesTemplate() {
        return ServerGlobalQueuesTData.class.getResourceAsStream("ServerGlobalQueues.java.vm");
    }

    public static InputStream getCallbackTemplate() {
        return CallbackTData.class.getResourceAsStream("Callback.java.vm");
    }

    public static InputStream getProxyInterfaceTemplate() {
        return ProxyInterfaceTData.class.getResourceAsStream("ProxyInterface.java.vm");
    }

    public static InputStream getProxyImplementationTemplate() {
        return ProxyImplementationTData.class.getResourceAsStream("ProxyImplementation.java.vm");
    }
}
