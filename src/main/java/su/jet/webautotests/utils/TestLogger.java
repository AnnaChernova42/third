package su.jet.webautotests.utils;

import org.apache.log4j.*;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TestLogger {

    private static final String logFileName = "./out/test_.log";
    public static Logger log;

    public static void initLogger() {
        //if(log != null){
            org.apache.log4j.LogManager.shutdown();
        //}
        log = Logger.getLogger(TestLogger.class);

        SimpleDateFormat df = new SimpleDateFormat("yyMMdd-HHmmss");
        Date currentDateAndTime = new Date();
        String strDateTime = df.format(currentDateAndTime);

        String fileName = logFileName.replace("_", "_" + strDateTime);
        Layout layout = new SimpleLayout();
        Appender fileAppender = null;
        try{
            fileAppender = new FileAppender(layout, fileName);
            fileAppender.setName("log");
        }catch (IOException e){e.printStackTrace();}

        log.addAppender(fileAppender);
        // region Временно, на время отладки, добавлен вывод лога в консоль.
        ConsoleAppender ca = new ConsoleAppender();
        ca.setWriter(new OutputStreamWriter(System.out));
        ca.setLayout(new PatternLayout("%-5p [%t]: %m%n"));
        log.addAppender(ca);
        //endregion
        log.setLevel(Level.ALL);
    }

}
