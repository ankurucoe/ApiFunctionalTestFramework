package Utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.util.Properties;

public class ConfigManager {

    private static ConfigManager configManager;

    private static final Properties prop = new Properties();

    private ConfigManager() throws IOException {
        InputStream stream = ConfigManager.class.getResourceAsStream("/config.properties");
        prop.load(stream);
    }

    public static ConfigManager getInstance() {
        if(configManager == null){
            synchronized (ConfigManager.class){
                try{
                    configManager = new ConfigManager();
                }
                catch (IOException e){
                    e.printStackTrace();
                }
            }
        }
        return configManager;
    }

    public String getProp(String key){
        return System.getProperty(key, prop.getProperty(key));
    }
}
