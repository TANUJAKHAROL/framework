package config;

import java.util.Map;
import java.util.Properties;
import java.io.IOException;

public class ConfigReader {

    private static final Properties properties;
    static{
        properties = new Properties();
        try{
            properties.load(ConfigReader.class.getClassLoader()
                    .getResourceAsStream("config.properties"));
        } catch (IOException e) {
            System.out.println(e.getMessage()); //loggers
        }
    }

    public static String getBaseUri(){
        return properties.getProperty("BASE_URI");
    }

    public static String getTimeout(){

        return properties.getProperty("timeout");

    }

    public static String getBrowser(){

        return properties.getProperty("browser");

    }



}

