package com.hrghs.xycb.utils;

import com.google.gson.*;
import com.hrghs.xycb.utils.DateTimeUtils;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;

import java.lang.reflect.Type;


public class DateTimeConverter implements JsonSerializer<DateTime>, JsonDeserializer<DateTime> {
    private  DateTimeFormatter dateTimeFormatter;
    private final String defaultFormat = "YYYY-MM-dd HH:mm:ss";
    public final DateTimeUtils dateTimeUtils = new DateTimeUtils();
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
        return format(jsonElement.getAsString());
    }

    @Override
    public JsonElement serialize(DateTime dateTime, Type type, JsonSerializationContext jsonSerializationContext) {
        return new JsonPrimitive(dateTimeFormatter.print(dateTime));
    }
    private DateTime format(String dateTimeSrc){
        DateTime dateTime =  null;
        try{
            dateTime = dateTimeFormatter.parseDateTime(dateTimeSrc);
            this.dateTimeFormatter = ISODateTimeFormat.dateHourMinuteSecond();
            System.out.println("Right DateTime Format! Resetting dateTimeFormatter\t" + dateTimeSrc );
        }catch (IllegalArgumentException illegalArgumentException){
            System.out.println("Wrong DateTime Format, keep trying!\t" + dateTimeSrc);
            //todo enumerate all ISODateTimeFormat
            //ISODateTimeFormat
            this.dateTimeFormatter = dateTimeUtils.detectPattern(dateTimeSrc);
            return format(dateTimeSrc);
        }
        return dateTime;
    }
}
