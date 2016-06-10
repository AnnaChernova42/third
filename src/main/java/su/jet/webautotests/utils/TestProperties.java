package su.jet.webautotests.utils;

import java.io.*;
import java.util.Properties;

/**
 * Класс хранит параметры из файла aml_autotests.properties
 * Created by vv.drobot on 18.03.2016.
 */
public class TestProperties {

    public static final Properties prop = getProperties();

    private static Properties getProperties() {
        Properties prop = new Properties();
        try{
            prop.load(new BufferedReader(new FileReader("aml_autotests.properties")));
        }catch (IOException e){
            TestLogger.log.error(e.getMessage());
            e.printStackTrace();}
        return prop;
    }
}
