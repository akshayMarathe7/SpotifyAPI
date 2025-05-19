package utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class propReader {

    public  Properties initProp(String path)  {

        Properties prop=new Properties();

        FileInputStream file= null;
        try {
            file = new FileInputStream(path);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        try {
            prop.load(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return prop;
    }
}
