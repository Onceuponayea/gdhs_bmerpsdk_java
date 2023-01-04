package com.hrghs.xycb.utils.converters;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.hrghs.xycb.domains.enums.BanmaerpAccountEnums;
import com.hrghs.xycb.domains.enums.BanmaerpOrderEnums;

import java.io.IOException;
import java.lang.reflect.Type;

public class EnumeratorSerialiser {
    public static class DataAccessSerialiser extends JsonSerializer<BanmaerpAccountEnums.DataAccessMode> {

        @Override
        public void serialize(BanmaerpAccountEnums.DataAccessMode value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
            gen.writeString(value.getDataAccessMode());
        }
    }
    public static class OrderStatusSerialiser extends JsonSerializer<BanmaerpOrderEnums.Status> {

        @Override
        public void serialize(BanmaerpOrderEnums.Status value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
            gen.writeString(value.name());
        }
    }
}
