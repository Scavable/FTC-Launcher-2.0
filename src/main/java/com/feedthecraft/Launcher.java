package com.feedthecraft;


import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

public class Launcher extends Application {
    Stage primaryStage;
    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        InstanceList instanceList = new InstanceList();
        ListView<MinecraftInstance> listView = instanceList.getInstanceList();

        BorderPane borderPane = new BorderPane();
        Scene scene = new Scene(borderPane);

        Button launchButton = new Button("Launch");

        WebView webView = new WebView();
        webView.getEngine().load("https://www.feedthecraft.com/");

        borderPane.setLeft(listView);
        borderPane.setCenter(webView);

        //Launcher button layout for right alignment
        HBox hBox = new HBox();
        borderPane.setBottom(hBox);
        hBox.setAlignment(Pos.CENTER_RIGHT);
        hBox.getChildren().add(launchButton);

        primaryStageInit(scene);
    }

    private void primaryStageInit(Scene scene) {
        primaryStage.setScene(scene);
        primaryStage.setTitle("FTC Launcher 2.0");
        primaryStage.setMinWidth(300);
        primaryStage.show();
    }
}