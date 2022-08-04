package com.feedthecraft;

import javafx.scene.control.ListView;

public class InstanceList {
    private ListView<MinecraftInstance> listView;

    InstanceList(){
        listView = new ListView<MinecraftInstance>();
    }

    public ListView<MinecraftInstance> getInstanceList() {
        return listView;
    }
}
