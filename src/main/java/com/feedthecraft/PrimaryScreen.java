package com.feedthecraft;

import com.sun.javafx.tk.Toolkit;

import java.util.HashMap;
import java.util.Map;

public class PrimaryScreen {
    private final Map<String, Float> map = new HashMap<>();
    public static float ptr, adapter, depth, x, y, width, height, platformX, platformY, platformWidth, platformHeight, visibleX, visibleY, visibleWidth, visibleHeight, platformScaleX, platformScaleY, outputScaleX, outputScaleY, resolutionX, resolutionY;

    PrimaryScreen(){
        String primaryScreenAttributes = Toolkit.getToolkit().getPrimaryScreen().toString();
        String[] attributes = primaryScreenAttributes.split("\\r?\\n");
        for(int i = 1; i < attributes.length; i++){
            System.out.println(attributes[i]);
            attributes[i] = attributes[i].trim();
            String[] keyValue = attributes[i].split(":");
            map.put(keyValue[0], Float.valueOf(keyValue[1]));
        }
        assignValues();
    }

    private void assignValues(){
        ptr = map.get("ptr");
        adapter = map.get("adapter");
        depth = map.get("depth");
        x = map.get("x");
        y = map.get("y");
        width = map.get("width");
        height = map.get("height");
        platformX = map.get("platformX");
        platformY = map.get("platformY");
        platformWidth = map.get("platformWidth");
        platformHeight = map.get("platformHeight");
        visibleX = map.get("visibleX");
        visibleY = map.get("visibleY");
        visibleWidth = map.get("visibleWidth");
        visibleHeight = map.get("visibleHeight");
        platformScaleX = map.get("platformScaleX");
        platformScaleY = map.get("platformScaleY");
        outputScaleX = map.get("outputScaleX");
        outputScaleY = map.get("outputScaleY");
        resolutionX = map.get("resolutionX");
        resolutionY = map.get("resolutionY");
    }

}
