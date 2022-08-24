package com.feedthecraft.auth;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;

public class MicrosoftLogin {
    private String sFTTag, urlPost, email, password;

    public MicrosoftLogin() throws IOException {
        URL url = new URL("https://login.live.com/oauth20_authorize.srf?client_id=000000004C12AE6F&redirect_uri=https://login.live.com/oauth20_desktop.srf&scope=service::user.auth.xboxlive.com::MBI_SSL&display=touch&response_type=token&locale=en");
        HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String temp;
        while((temp = in.readLine()) != null){
            System.out.println(temp);
            if(temp.contains("value=")){
                sFTTag = temp.split("value="+"(.+?)")[1].split("\"")[0];
            }
            if(temp.contains("urlPost:")){
                urlPost = temp.split("urlPost:'(.+?)")[1].split("'")[0];
                urlPost = "h".concat(urlPost);

            }
        }
        microsoftCreds();

    }

    private void microsoftCreds() {
        Stage stage = new Stage();
        GridPane pane = new GridPane();
        Scene scene = new Scene(pane);
        TextField emailTF = new TextField("Email");
        TextField passwordTF = new TextField("Password");
        Button submit = new Button("Submit");
        Button cancel = new Button("Cancel");

        pane.setPadding(new Insets(5,5,5,5));
        pane.add(emailTF, 0, 0, 2, 1);
        pane.add(passwordTF, 0, 1, 2, 1);
        pane.add(submit, 0, 2, 1, 1);
        pane.setHgap(20);
        pane.add(cancel, 1, 2, 1, 1);
        stage.setScene(scene);
        stage.show();

        submit.setOnAction(event -> {
            email = emailTF.getText();
            password = passwordTF.getText();

            String post = "login=".concat(URLEncoder.encode(email)).concat("&loginfmt=").concat(URLEncoder.encode(email)).concat("&passwd=").concat(URLEncoder.encode(password)).concat("&PPFT=").concat(URLEncoder.encode(sFTTag));
            try {
                URL postURL = new URL(urlPost);
                HttpsURLConnection con = (HttpsURLConnection) postURL.openConnection();
                con.setRequestMethod("POST");
                con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                con.setDoOutput(true);
                OutputStream outputStream = con.getOutputStream();
                outputStream.write(post.getBytes());
                outputStream.flush();
                outputStream.close();
                int response = con.getResponseCode();
                System.out.println("Response is: "+response);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        });

    }
}
