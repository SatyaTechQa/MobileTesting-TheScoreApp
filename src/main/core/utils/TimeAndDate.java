package src.main.core.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TimeAndDate {

    /**
     * Get the Data and Time in dd_MM_yyyy_HH_mm_ss
     *
     * @return
     */
    public static String getDateAndTime() {
        Date date = Calendar.getInstance().getTime();
        String format = ReadWriteProperties.props.getProperty("timestamp.format");
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(date);
    }
}
