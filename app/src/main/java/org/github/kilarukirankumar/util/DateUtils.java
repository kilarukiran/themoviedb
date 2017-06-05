package org.github.kilarukirankumar.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 */

public class DateUtils {

    private DateUtils() {

    }

    /**
     * Remove the time component and return the created date.
     * Sample date sent by API - 2017-05-02T03:44:39-04:00
     *
     * @param createdDateTime Date with Time as returned by the API.
     * @return Date component in string format.
     */
    public static String getCreatedDate(String createdDateTime) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");
        Date date = format.parse(createdDateTime);
//        format = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ssZ");
        format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.format(date);
    }

}
