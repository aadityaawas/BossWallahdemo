package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

// for maintaining the commonly used functionality across the project
public class CommonUtils {

    // for reading properties file
    public Properties readPropertiesFile() {
        Properties prop = new Properties();
        FileInputStream fis;
        String COMMON_DETAILS_FILE_PATH = System.getProperty("user.dir") + ReusableConstants.PROPERTIES_FILE;
        try {
            fis = new FileInputStream(COMMON_DETAILS_FILE_PATH);
            prop.load(fis);
            return prop;
        } catch (IOException e) {
            throw new RuntimeException("File not found at "+COMMON_DETAILS_FILE_PATH+"\n"+e);
        }
    }
}
