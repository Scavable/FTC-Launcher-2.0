package com.feedthecraft;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.Properties;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class DownloadPacks {
    DownloadPacks() {
        try {
            Properties properties = new Properties();
            properties.load(getClass().getClassLoader().getResourceAsStream("launcher.properties"));

            String url = properties.getProperty("modpacksURL");
            HttpsURLConnection connection = (HttpsURLConnection) new URL(url).openConnection();
            BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String temp;
            JSONParser parser = new JSONParser();
            JSONObject obj = (JSONObject) parser.parse(br.readLine());
            JSONArray obj2 = (JSONArray) obj.get("packages");
            for (Object o : obj2) {
                System.out.println(o);
                String[] attributes = o.toString().substring(1, o.toString().length() - 1).replace("\"", "").split("[:,]+");
                MinecraftInstance instance = new MinecraftInstance(attributes[1], attributes[5], "", "", "", "", attributes[9]);
                InstanceList.addInstance(instance);
            }
        } catch (MalformedURLException e) {
            System.out.println("Malformed URL");
        } catch (IOException e) {
            System.out.println("IOException");
        } catch (ParseException e) {
            System.out.println("Parse Exception");
        }
    }
}
