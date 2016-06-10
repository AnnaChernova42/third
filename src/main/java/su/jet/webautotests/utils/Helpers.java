package su.jet.webautotests.utils;


import java.text.Collator;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by ub.kim on 23.03.2016.
 */
public class Helpers {

    public static String getDate(int numOfDays){
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        Calendar cal = Calendar.getInstance();
        cal.add( Calendar.DATE, numOfDays );
        String convertedDate = dateFormat.format(cal.getTime());
        return convertedDate;
    }

    public static Date getCurrentDate(){
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        String convertedDate = dateFormat.format(cal.getTime());
        Date date = null;
        try {
            date = dateFormat.parse(convertedDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    public static List<String> sortWsCollator(List<String> l) {

        Collator russianCollator = Collator.getInstance(new Locale("ru", "RU"));

        int j;
        String temp;
        boolean flag = true;
        while (flag) {
            flag = false;
            for (j = 0; j < l.size() - 1; j++) {

                if (russianCollator.compare(l.get(j), l.get(j + 1)) > 0) {
                    temp = l.get(j);

                    l.set(j, l.get(j + 1));
                    l.set(j + 1, temp);
                    flag = true;
                }

            }
        }
        return l;
    }
}
