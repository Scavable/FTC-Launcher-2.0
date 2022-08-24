package com.feedthecraft;

import java.io.File;

public class MinecraftInstance {
    String name, modpackVersion, description, mods, minecraftVersion, forgeVersion, uploadVersion;
    File instanceFolder, modsFolder, configFolder, saveFolder;

    MinecraftInstance(){
        this.name = null;
        this.modpackVersion = null;
        this.description = null;
        this.mods = null;
        this.minecraftVersion = null;
        this.forgeVersion = null;
        this.instanceFolder = null;
        this.modsFolder = null;
        this.configFolder = null;
        this.saveFolder = null;
    }
    public MinecraftInstance(String name, String modpackVersion, String description, String mods, String minecraftVersion, String forgeVersion, String uploadVersion){
        this.name = name;
        this.modpackVersion = modpackVersion;
        this.description = description;
        this.mods = mods;
        this.minecraftVersion = minecraftVersion;
        this.forgeVersion = forgeVersion;
        this.uploadVersion = uploadVersion;

    }
    MinecraftInstance(File instanceFolder){
        this.instanceFolder = instanceFolder;
        this.modsFolder = new File(instanceFolder, "mods");
        this.configFolder = new File(instanceFolder, "config");
        this.saveFolder = new File(instanceFolder, "saves");
    }

    @Override
    public String toString() {
        return name;
    }
}
