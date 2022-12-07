package com.hrghs.xycb.utils.converters;

import com.fasterxml.jackson.databind.DeserializationContext;
import com.hrghs.xycb.utils.DateTimeUtils;
import org.joda.time.DateTime;
import java.io.IOException;

public class JodaDateTimeDeserialiser extends com.fasterxml.jackson.databind.JsonDeserializer<DateTime> {
    public static DateTimeUtils dateTimeUtils = new DateTimeUtils();
    @Override
    public DateTime deserialize(com.fasterxml.jackson.core.JsonParser p, DeserializationContext ctxt) throws IOException {
        return  DateTime.parse(p.getText(), dateTimeUtils.detectPattern(p.getText()));
    }
}