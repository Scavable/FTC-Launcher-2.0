package com.feedthecraft;

import com.sun.javafx.tk.Toolkit;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.web.WebView;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class Launcher extends Application {
    private Stage primaryStage;

    private final Button launchButton = new Button("Launch");
    private final Button patreonButton = new Button();
    private final Button discordButton = new Button();
    private final Button websiteButton = new Button();
    private final Button settingsButton = new Button();
    private final Button downloadModpack = new Button("Download Modpack");
    private final Button extractButton = new Button("Extract");

    private final InstanceList instanceList = InstanceList.getInstance();
    private final ListView<Label> settingsListView = new ListView<>();

    private final BorderPane borderPane = new BorderPane();
    private final StackPane webPaneStack = new StackPane();
    private final BorderPane webBorderPane = new BorderPane();
    private final GridPane gridPane = new GridPane();

    private final Scene scene = new Scene(borderPane);

    private EventHandler<ActionEvent> settingsButtonAction, downloadModpackAction, patreonButtonAction, discordButtonAction;
    private EventHandler<ActionEvent> websiteButtonAction;

    private final ButtonBar buttonBar = new ButtonBar();
    private final WebView webView = new WebView();
    private final Map<String, Float> map = new HashMap<>();
    private boolean offlineMode = false;


    public static void main(String[] args) {
        launch();
    }
    /**
     *
     * @param primaryStage the primary stage for this application, onto which
     * the application scene can be set. The primary stage will be embedded in
     * the browser if the application was launched as an applet.
     * Applications may create other stages, if needed, but they will not be
     * primary stages and will not be embedded in the browser.
     * @throws Exception
     *
     * Start Point of Application
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;

        new PrimaryScreen();

        new DownloadPacks();

        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

        initButtonBar();
        initButtonImages();
        eventHandlers();
        attachButtonActions();
        settingsListViewOptions();

        borderPane.setBackground(new Background(new BackgroundFill(null, null, null)));

        webView.getEngine().load("https://www.feedthecraft.com/");

        webBorderPane.setMaxWidth(settingsButton.getWidth());
        webBorderPane.setMaxHeight(settingsButton.getHeight());
        webBorderPane.setRight(settingsButton);

        webPaneStack.getChildren().add(0, webView);
        webPaneStack.getChildren().add(1, webBorderPane);

        webPaneStack.setAlignment(Pos.TOP_RIGHT);

        ListView<MinecraftInstance> listView = instanceList.getInstanceList();

        borderPane.setCenter(webPaneStack);
        borderPane.setLeft(listView);
        borderPane.setBottom(buttonBar);
        borderPane.setRight(gridPane);

        primaryStageInit(scene);

        webPaneStack.getChildren().get(1).setTranslateX(-15);


    }

    /**
     * Initial ButtonBar Setup
     */
    private void initButtonBar() {
        buttonBar.getButtons().addAll(downloadModpack, extractButton, patreonButton, discordButton, websiteButton, launchButton);
        ButtonBar.setButtonUniformSize(launchButton, false);
        ButtonBar.setButtonUniformSize(downloadModpack, false);
        buttonBar.setPadding(new Insets(0,20,0,20));
        launchButton.setPrefSize(100, 30);
        buttonBar.buttonMinWidthProperty().set(0);
        ButtonBar.setButtonData(downloadModpack, ButtonBar.ButtonData.LEFT);
        ButtonBar.setButtonData(extractButton, ButtonBar.ButtonData.LEFT);
    }

    /**
     * Initial Settings List Setup
     */
    private void settingsListViewOptions() {
        Label memory = new Label("Memory");
        Label java = new Label("Java");
        Label customize = new Label("Customize");
        Label installLocation = new Label("Install Location");
        settingsListView.getItems().addAll(memory, java, customize, installLocation);
        settingsListView.setEditable(false);
        settingsListView.setMaxWidth(100);
    }

    /**
     * Add Actions to Buttons
     */
    private void attachButtonActions() {
        settingsButton.setOnAction(settingsButtonAction);
        downloadModpack.setOnAction(downloadModpackAction);
        patreonButton.setOnAction(patreonButtonAction);
        discordButton.setOnAction(discordButtonAction);
        websiteButton.setOnAction(websiteButtonAction);
    }

    /**
     * Create Actions for Buttons
     */
    private void eventHandlers() {
        //Settings Button Action
        settingsButtonAction = event -> {
            if(gridPane.getChildren().contains(settingsListView))
                gridPane.getChildren().remove(settingsListView);
            else
                gridPane.add(settingsListView, 0,1);
        };

        //Download Modpack Button Action
        downloadModpackAction = event -> {
            try {
                Desktop.getDesktop().browse(new URI("https://www.curseforge.com/minecraft/modpacks"));
            } catch (IOException | URISyntaxException e) {
                throw new RuntimeException(e);
            }
        };

        //Patreon Button Action
        patreonButtonAction = event -> {
            try {
                Desktop.getDesktop().browse(new URI("https://www.patreon.com/feedthecraft/membership"));
            } catch (IOException | URISyntaxException e) {
                throw new RuntimeException(e);
            }
        };

        //Discord Button Action
        discordButtonAction = event -> {
            try {
                Desktop.getDesktop().browse(new URI("https://discord.gg/FeedTheCraft"));
            } catch (IOException | URISyntaxException e) {
                throw new RuntimeException(e);
            }
        };

        //Website Button Action
        websiteButtonAction = event -> {
            try {
                Desktop.getDesktop().browse(new URI("https://www.feedthecraft.com"));
            } catch (IOException | URISyntaxException e) {
                throw new RuntimeException(e);
            }
        };
    }

    /**
     * Initial Button Images (Patreon, Discord, Website, Hamburger)
     */
    private void initButtonImages() {
        Image img = new Image(getClass().getResource("/patreon_icon.png").toString());
        ImageView imageView = new ImageView(img);
        imageView.setFitHeight(20);
        imageView.setFitWidth(20);
        patreonButton.setGraphic(imageView);

        img = new Image(getClass().getResource("/discord_icon.png").toString());
        imageView = new ImageView(img);
        imageView.setFitHeight(20);
        imageView.setFitWidth(20);
        discordButton.setGraphic(imageView);

        img = new Image(getClass().getResource("/ftc_icon.png").toString());
        imageView = new ImageView(img);
        imageView.setFitHeight(20);
        imageView.setFitWidth(20);
        websiteButton.setGraphic(imageView);

        img = new Image(getClass().getResource("/hamburger_menu_icon.png").toString());
        imageView = new ImageView(img);
        imageView.setFitHeight(20);
        imageView.setFitWidth(20);
        settingsButton.setGraphic(imageView);
    }

    /**
     * Initial Window Setup
     */
    private void primaryStageInit(Scene scene) {
        primaryStage.getIcons().add(new Image(getClass().getResource("/ftc_icon.png").toString()));
        primaryStage.setScene(scene);
        primaryStage.setTitle("FTC Launcher 2.0");
        primaryStage.setMinWidth(300);
        primaryStage.show();
    }
}