package org.bosbec.createspecificationexample;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigurationHelper {
    private static String apiBaseUrl = "";
    private static String username = "";
    private static String password = "";
    private static Boolean initialized = false;

    public static String getApiBaseUrl() {
        initialize();

        return apiBaseUrl;
    }

    public static String getUsername() {
        initialize();

        return username;
    }

    public static String getPassword() {
        initialize();

        return password;
    }

    private static void initialize()
    {
        if (initialized) {
            return;
        }

        Properties properties = new Properties();

        try {
            properties.load(new FileInputStream("settings.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        apiBaseUrl = properties.getProperty("mobileresponse_api_base_url");
        username = properties.getProperty("mobileresponse_username");
        password = properties.getProperty("mobileresponse_password");

        initialized = true;
    }
}