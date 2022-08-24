package com.feedthecraft;

import javafx.scene.control.ListView;

public class InstanceList {
    private static ListView<MinecraftInstance> listView;
    private static InstanceList single_instance = null;

    private InstanceList(){
        listView = new ListView<MinecraftInstance>();
    }

    public static ListView<MinecraftInstance> getInstanceList() {
        return listView;
    }
    public static void addInstance(MinecraftInstance instance){
        listView.getItems().add(instance);
    }
    public static InstanceList getInstance()
    {
        if (single_instance == null)
            single_instance = new InstanceList();

        return single_instance;
    }
}
