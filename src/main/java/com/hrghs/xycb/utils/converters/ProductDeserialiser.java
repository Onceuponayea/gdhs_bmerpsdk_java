package com.hrghs.xycb.utils.converters;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.joda.JodaModule;
import com.hrghs.xycb.domains.banmaerpDTO.OrderMasterDTO;
import com.hrghs.xycb.domains.banmaerpDTO.ProductDescriptionsDTO;
import com.hrghs.xycb.domains.banmaerpDTO.ProductSkusDTO;
import com.hrghs.xycb.domains.banmaerpDTO.ProductSpuDTO;

import java.io.IOException;

import static jodd.util.StringPool.LEFT_SQ_BRACKET;

public class ProductDeserialiser {
    private static ObjectMapper objectMapper = new ObjectMapper().registerModule(new JodaModule());

    public static class ProductSpuDeserializer extends JsonDeserializer<ProductSpuDTO> {
        @Override
        public ProductSpuDTO deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JacksonException {
            return objectMapper.convertValue(p.getText(),ProductSpuDTO.class);
        }
    }
    public static class ProductDescriptionsDeserializer extends JsonDeserializer<ProductDescriptionsDTO> {
        @Override
        public ProductDescriptionsDTO deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JacksonException {
            return objectMapper.convertValue(p.getText(),ProductDescriptionsDTO.class);
        }
    }
    public static class ProductSkuDeserializer extends JsonDeserializer<ProductSkusDTO> {
        @Override
        public ProductSkusDTO deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JacksonException {
            return objectMapper.convertValue(p.getText(),ProductSkusDTO.class);
        }
    }
    public static class OrderMasterDeserializer extends JsonDeserializer<OrderMasterDTO> {
        @Override
        public OrderMasterDTO deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JacksonException {
            return objectMapper.convertValue(p.getText(),OrderMasterDTO.class);
        }
    }

}
