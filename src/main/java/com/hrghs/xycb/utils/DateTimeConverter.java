package com.hrghs.xycb.utils;

import com.google.gson.*;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;
import java.lang.reflect.Type;

public class DateTimeConverter implements JsonSerializer<DateTime>, JsonDeserializer<DateTime> {
    private static DateTimeFormatter dateTimeFormatter;
    private final String defaultFormat = "YYYY-MM-dd HH:mm:ss";

    public DateTimeConverter(){
        this.dateTimeFormatter = ISODateTimeFormat.dateHourMinuteSecond();
    }
    public DateTimeConverter(String dateTimeFormat){
        this.dateTimeFormatter = DateTimeFormat.forPattern(dateTimeFormat);
    }
    @Override
    public DateTime deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        if (jsonElement == null || jsonElement.isJsonNull() || jsonElement.getAsString() == null || jsonElement.getAsString().isEmpty()){
            return null;
        }
        return JodaDateTimeFormatter.format(jsonElement.getAsString());
    }

    @Override
    public JsonElement serialize(DateTime dateTime, Type type, JsonSerializationContext jsonSerializationContext) {
        return new JsonPrimitive(dateTimeFormatter.print(dateTime));
    }

    public static class JodaDateTimeFormatter{
        public static DateTimeUtils dateTimeUtils = new DateTimeUtils();
        protected static DateTime format(String dateTimeSrc){
            DateTime dateTime =  null;
            try{
                dateTime = dateTimeFormatter.parseDateTime(dateTimeSrc);
                dateTimeFormatter = ISODateTimeFormat.dateHourMinuteSecond();
            }catch (IllegalArgumentException illegalArgumentException){
                //ISODateTimeFormat
                dateTimeFormatter = dateTimeUtils.detectPattern(dateTimeSrc);
                return format(dateTimeSrc);
            }
            return dateTime;
        }
    }
}
