package selenium.test;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class PropertiesReader {
    private static Properties properties;

    public static Properties getProperties() {
        if (properties == null) {
            properties = new Properties();
            try {
                properties.load(new FileReader("src/test/resources/selenium.properties"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return properties;
    }
}
