package com.codingfairy.monitor.tools;

import javafx.fxml.FXMLLoader;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

/**
 * Created by cuihao on 2017-08-24.
 * Get resource file
 */
public class ResourceTools {

    public static InputStream getGeneralConfig(String path) {
        return ResourceTools.class.getClassLoader().getResourceAsStream(path);
    }

    public static BufferedReader getConfig(String configName) {
        return new BufferedReader(new InputStreamReader(getGeneralConfig("config/"+configName)));
    }

    public static FXMLLoader getFxmlConfig(String fxmlName) {
        return new FXMLLoader(getResourceUrl("fxml/"+fxmlName));
    }

    private static URL getResourceUrl(String path) {
        return ResourceTools.class.getClassLoader().getResource(path);
    }

}
