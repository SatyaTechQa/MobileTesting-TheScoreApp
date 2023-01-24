package src.main.core.utils;

import java.io.*;
import java.nio.file.Paths;
import java.util.Properties;

import src.main.constants.Constants;

public class ReadWriteProperties {
    public ReadWriteProperties() {
    }

    public static Properties props;

    static {
        try {
            File configFile = Paths.get(Constants.ConfigurationFiles_Dir + "config.properties").toFile();
            FileInputStream fs = new FileInputStream(configFile);
            props = new Properties();
            props.load(fs);
        } catch (Exception e) {
        }
    }
}
