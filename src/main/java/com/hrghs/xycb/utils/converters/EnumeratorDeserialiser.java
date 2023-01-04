package com.hrghs.xycb.utils.converters;


import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.hrghs.xycb.domains.enums.BanmaerpAccountEnums;
import com.hrghs.xycb.domains.enums.BanmaerpOrderEnums;

import java.io.IOException;

public class EnumeratorDeserialiser {
    public static class DataAccessDeSerialiser extends JsonDeserializer<BanmaerpAccountEnums.DataAccessMode> {

        @Override
        public BanmaerpAccountEnums.DataAccessMode deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JacksonException {
            return BanmaerpAccountEnums.DataAccessMode.valueof(p.getText());
        }
    }
    public static class OrderStatusDeserialiser extends JsonDeserializer<BanmaerpOrderEnums.Status> {

        @Override
        public BanmaerpOrderEnums.Status deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JacksonException {
            return BanmaerpOrderEnums.Status.valueof(p.getText());
        }
    }
}
