package com.hrghs.xycb.utils;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DateTimeUtils {
    private static final Map<String, String> DATE_FORMAT_REGEXPS = new HashMap<String, String>() {{
        put("^\\d{8}$", "yyyyMMdd");
        put("^\\d{1,2}-\\d{1,2}-\\d{4}$", "dd-MM-yyyy"); //Aug 24, 1990
        put("^\\d{4}-\\d{1,2}-\\d{1,2}$", "yyyy-MM-dd");
        put("^\\d{1,2}/\\d{1,2}/\\d{4}$", "MM/dd/yyyy");
        put("^\\d{4}/\\d{1,2}/\\d{1,2}$", "yyyy/MM/dd");
        put("^\\d{1,2}\\s[a-z]{3}\\s\\d{4}$", "dd MMM yyyy");
        put("^\\d{1,2}\\s[a-z]{4,}\\s\\d{4}$", "dd MMMM yyyy");
        put("^\\d{12}$", "yyyyMMddHHmm");
        put("^\\d{8}\\s\\d{4}$", "yyyyMMdd HHmm");
        put("^\\d{1,2}-\\d{1,2}-\\d{4}\\s\\d{1,2}:\\d{2}$", "dd-MM-yyyy HH:mm");
        put("^\\d{4}-\\d{1,2}-\\d{1,2}\\s\\d{1,2}:\\d{2}$", "yyyy-MM-dd HH:mm");
        put("^\\d{1,2}/\\d{1,2}/\\d{4}\\s\\d{1,2}:\\d{2}$", "MM/dd/yyyy HH:mm");
        put("^\\d{4}/\\d{1,2}/\\d{1,2}\\s\\d{1,2}:\\d{2}$", "yyyy/MM/dd HH:mm");
        put("^\\d{1,2}\\s[a-z]{3}\\s\\d{4}\\s\\d{1,2}:\\d{2}$", "dd MMM yyyy HH:mm");
        put("^\\d{1,2}\\s[a-z]{4,}\\s\\d{4}\\s\\d{1,2}:\\d{2}$", "dd MMMM yyyy HH:mm");
        put("^\\d{14}$", "yyyyMMddHHmmss");
        put("^\\d{8}\\s\\d{6}$", "yyyyMMdd HHmmss");
        put("^\\d{1,2}-\\d{1,2}-\\d{4}\\s\\d{1,2}:\\d{2}:\\d{2}$", "dd-MM-yyyy HH:mm:ss");
        put("^\\d{4}-\\d{1,2}-\\d{1,2}\\s\\d{1,2}:\\d{2}:\\d{2}$", "yyyy-MM-dd HH:mm:ss");
        put("^\\d{1,2}/\\d{1,2}/\\d{4}\\s\\d{1,2}:\\d{2}:\\d{2}$", "MM/dd/yyyy HH:mm:ss");
        put("^\\d{4}/\\d{1,2}/\\d{1,2}\\s\\d{1,2}:\\d{2}:\\d{2}$", "yyyy/MM/dd HH:mm:ss");
        put("^\\d{1,2}\\s[a-z]{3}\\s\\d{4}\\s\\d{1,2}:\\d{2}:\\d{2}$", "dd MMM yyyy HH:mm:ss");
        put("^\\d{1,2}\\s[a-z]{4,}\\s\\d{4}\\s\\d{1,2}:\\d{2}:\\d{2}$", "dd MMMM yyyy HH:mm:ss");
        put("^\\d{4}-[01]\\d-[0-3]\\dT[0-2]\\d:[0-5]\\d:[0-5]\\d{2}$","yyyy-MM-ddTHH:mm:ss");//2019-11-15T13:34:22.178Z
    }};
    private static final List<DateTimeFormatter> defaultDateTimeFormatters = Arrays.asList(
            DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss"),
            DateTimeFormat.forPattern("yyyy/MM/dd HH:mm:ss"),
            ISODateTimeFormat.dateHourMinuteSecond(),
            ISODateTimeFormat.dateHourMinuteSecondFraction(),
            ISODateTimeFormat.dateHourMinuteSecondMillis(),
            ISODateTimeFormat.dateHourMinute(), ISODateTimeFormat.dateHour(),
            ISODateTimeFormat.hourMinuteSecondFraction(), ISODateTimeFormat.hourMinuteSecondMillis(),
            ISODateTimeFormat.hourMinuteSecond(), ISODateTimeFormat.hourMinute(),
            ISODateTimeFormat.hour(), ISODateTimeFormat.weekyearWeekDay(),
            ISODateTimeFormat.weekyearWeek(), ISODateTimeFormat.weekyear(),
            ISODateTimeFormat.yearMonthDay(), ISODateTimeFormat.yearMonth(),
            ISODateTimeFormat.year(), ISODateTimeFormat.basicWeekDateTimeNoMillis(),
            ISODateTimeFormat.basicWeekDateTime(), ISODateTimeFormat.basicWeekDate(),
            ISODateTimeFormat.basicOrdinalDateTimeNoMillis(), ISODateTimeFormat.basicOrdinalDateTime(),
            ISODateTimeFormat.basicOrdinalDate(), ISODateTimeFormat.basicDateTimeNoMillis(),
            ISODateTimeFormat.basicDateTime(), ISODateTimeFormat.basicTTimeNoMillis(),
            ISODateTimeFormat.basicTTime(), ISODateTimeFormat.basicTimeNoMillis(),
            ISODateTimeFormat.basicTime(), ISODateTimeFormat.basicDate(),
            ISODateTimeFormat.weekDateTimeNoMillis(), ISODateTimeFormat.weekDateTime(),
            ISODateTimeFormat.weekDate(), ISODateTimeFormat.ordinalDateTimeNoMillis(),
            ISODateTimeFormat.ordinalDateTime(), ISODateTimeFormat.ordinalDate(),
            ISODateTimeFormat.dateTimeNoMillis(), ISODateTimeFormat.dateTime(),
            ISODateTimeFormat.tTimeNoMillis(), ISODateTimeFormat.tTime(),
            ISODateTimeFormat.timeNoMillis(), ISODateTimeFormat.time(),
            ISODateTimeFormat.date(), ISODateTimeFormat.localDateOptionalTimeParser(),
            ISODateTimeFormat.dateOptionalTimeParser(), ISODateTimeFormat.dateTimeParser(),
            ISODateTimeFormat.timeElementParser(), ISODateTimeFormat.localTimeParser(),
            ISODateTimeFormat.timeParser(), ISODateTimeFormat.dateElementParser(),
            ISODateTimeFormat.localDateParser(), ISODateTimeFormat.dateParser()
            );

    public String determineDateFormat(String dateString) {
        for (String regexp : DATE_FORMAT_REGEXPS.keySet()) {
            if (dateString.toLowerCase().matches(regexp)) {
                return DATE_FORMAT_REGEXPS.get(regexp);
            }
        }
        throw new IllegalArgumentException("Unknown Format");
    }
    public DateTimeFormatter getDateTimeFormatter(String dateTimeSrc){
       for (DateTimeFormatter dateTimeFormatter : defaultDateTimeFormatters){
           try {
               dateTimeFormatter.parseDateTime(dateTimeSrc);
               System.out.println("right dateTimeFormatter for "+ dateTimeSrc );
               return dateTimeFormatter;
           }catch (IllegalArgumentException e){
               //todo  slf4j log info
               System.out.println("wrong dateTimeFormatter, keep looping\t" +  dateTimeSrc);
           }
       }
       return DateTimeFormat.forPattern(determineDateFormat(dateTimeSrc));
    }
    /**
     * enumerate datetimeFormat.
     * more format will be supported by future.
     * @param dateTimeSrc
     * @return
     */
    public DateTimeFormatter detectPattern(String dateTimeSrc){
        dateTimeSrc=dateTimeSrc.replace("'","");
        return  getDateTimeFormatter(dateTimeSrc);
    }

    public Date joda2SQLDate(DateTime dateTime){
        return new Date(dateTime.getMillis());
    }
    public Timestamp joda2SQLTimeStamp(DateTime dateTime){
        return (dateTime!=null)?new Timestamp(dateTime.getMillis()):null;
    }
    public DateTime sqlTimeStamp2JodaDateTime(Timestamp timestamp){
        return (timestamp!=null)?DateTime.parse(timestamp.toLocalDateTime().toString()):null;
    }

}
