package com.acedemy.techcenture.config;

import org.openqa.selenium.WebDriver;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
    private static Properties properties;
    private static final String CONFIG_FILE_PATH = "src/main/resources/config.properties";

    private ConfigReader(){}
    static{
        try{
            FileInputStream inputStream = new FileInputStream(CONFIG_FILE_PATH);
            properties = new Properties();
            properties.load(inputStream);
            inputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
public static String getProperty(String keyName){
      return properties.getProperty(keyName);
}
    public static String setProperty(String keyName, String value){
        return String.valueOf(properties.setProperty(keyName,value));
    }
}
