package com.hrghs.xycb.utils.converters;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.datatype.joda.JodaModule;
import org.joda.time.DateTime;

import java.io.IOException;

public class JodaDateTimeSerialiser extends com.fasterxml.jackson.databind.JsonSerializer<DateTime> {
    private ObjectMapper objectMapper = new ObjectMapper().registerModule(new JodaModule());
    @Override
    public void serialize(DateTime value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        JsonNode jsonNode =objectMapper.convertValue(value, JsonNode.class);
        System.out.println("JodaDateTimeSerialiser...........................\t" + value.toLocalDateTime());
        String result = jsonNode!=null? jsonNode.toPrettyString() : "";
        System.out.println("JodaDateTimeSerialiser_result\t" + result);
        gen.writeString(result);
    }
}