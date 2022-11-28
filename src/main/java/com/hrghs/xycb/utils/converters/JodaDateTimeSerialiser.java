package com.hrghs.xycb.utils.converters;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.joda.time.DateTime;
import java.io.IOException;

public class JodaDateTimeSerialiser extends com.fasterxml.jackson.databind.JsonSerializer<DateTime> {
    @Override
    public void serialize(DateTime value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        String result = value!=null? value.toLocalDateTime().toString() : "";
        gen.writeString(result);
    }
}