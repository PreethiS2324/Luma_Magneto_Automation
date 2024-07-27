package pages;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
    private Properties properties;

    public ConfigReader() {
        try (FileInputStream filepath = new FileInputStream("src/main/resources/config.properties")) {
            properties = new Properties();
            properties.load(filepath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getProperty(String key) {
        return properties.getProperty(key);
    }
}
