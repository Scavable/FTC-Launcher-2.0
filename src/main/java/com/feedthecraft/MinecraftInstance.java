package com.feedthecraft;

import java.io.File;

public class MinecraftInstance {
    String name, instanceVersion, description, mods, minecraftVersion, forgeVersion;
    File instanceFolder, modsFolder, configFolder, saveFolder;

    MinecraftInstance(){
        this.name = null;
        this.instanceVersion = null;
        this.description = null;
        this.mods = null;
        this.minecraftVersion = null;
        this.forgeVersion = null;
        this.instanceFolder = null;
        this.modsFolder = null;
        this.configFolder = null;
        this.saveFolder = null;
    }
    MinecraftInstance(File instanceFolder){
        this.instanceFolder = instanceFolder;
        this.modsFolder = new File(instanceFolder, "mods");
        this.configFolder = new File(instanceFolder, "config");
        this.saveFolder = new File(instanceFolder, "saves");
    }
}
