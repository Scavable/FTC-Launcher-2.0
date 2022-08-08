package com.feedthecraft;


import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

public class Launcher extends Application {
    private Stage primaryStage;
    private final Button launchButton = new Button("Launch");
    private final Button patreonButton = new Button();
    private final Button discordButton = new Button();
    private final Button websiteButton = new Button();
    private final Button settingsButton = new Button();
    private final InstanceList instanceList = new InstanceList();
    private final BorderPane borderPane = new BorderPane();
    private final Scene scene = new Scene(borderPane);
    private final ButtonBar buttonBar = new ButtonBar();
    private final WebView webView = new WebView();
    private EventHandler<ActionEvent> settingsButtonAction;
    private final GridPane gridPane = new GridPane();
    private final ListView<Label> settingsListView = new ListView<>();

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;

        borderPane.setBackground(new Background(new BackgroundFill(null, null, null)));
        ListView<MinecraftInstance> listView = instanceList.getInstanceList();

        initButtonImages();
        eventHandlers();
        attachButtonActions();
        settingsListViewOptions();

        ButtonBar.setButtonUniformSize(launchButton, false);
        buttonBar.setPadding(new Insets(0,20,0,0));
        launchButton.setPrefSize(100, 30);

        buttonBar.buttonMinWidthProperty().set(0);
        buttonBar.getButtons().addAll(patreonButton, discordButton, websiteButton, launchButton);

        webView.getEngine().load("https://www.feedthecraft.com/");

        GridPane.setHalignment(settingsButton, HPos.RIGHT);
        gridPane.add(settingsButton,0,0);
        gridPane.add(settingsListView, 0,1);

        borderPane.setLeft(listView);
        borderPane.setCenter(webView);
        borderPane.setBottom(buttonBar);
        borderPane.setRight(gridPane);

        primaryStageInit(scene);
    }

    private void settingsListViewOptions() {
        settingsListView.setVisible(false);
        Label memory = new Label("Memory");
        settingsListView.getItems().addAll(memory);
    }

    private void attachButtonActions() {
        settingsButton.setOnAction(settingsButtonAction);
    }

    private void eventHandlers() {
        settingsButtonAction = event -> settingsListView.setVisible(!settingsListView.isVisible());
    }

    private void initButtonImages() {
        Image img = new Image(String.valueOf(Launcher.class.getResource("/patreon_icon.png")));
        ImageView imageView = new ImageView(img);
        imageView.setFitHeight(20);
        imageView.setFitWidth(20);
        patreonButton.setGraphic(imageView);

        img = new Image(String.valueOf(Launcher.class.getResource("/discord_icon.png")));
        imageView = new ImageView(img);
        imageView.setFitHeight(20);
        imageView.setFitWidth(20);
        discordButton.setGraphic(imageView);

        img = new Image(String.valueOf(Launcher.class.getResource("/ftc_icon.png")));
        imageView = new ImageView(img);
        imageView.setFitHeight(20);
        imageView.setFitWidth(20);
        websiteButton.setGraphic(imageView);

        img = new Image(String.valueOf(Launcher.class.getResource("/hamburger_menu_icon.png")));
        imageView = new ImageView(img);
        imageView.setFitHeight(20);
        imageView.setFitWidth(20);
        settingsButton.setGraphic(imageView);
    }

    private void primaryStageInit(Scene scene) {
        primaryStage.setScene(scene);
        primaryStage.setTitle("FTC Launcher 2.0");
        primaryStage.setMinWidth(300);
        primaryStage.show();
    }
}