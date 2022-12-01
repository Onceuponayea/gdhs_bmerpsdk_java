package com.hrghs.xycb.utils.converters;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.joda.JodaModule;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.reflect.TypeToken;
import com.hrghs.xycb.domains.banmaerpDTO.OrderMasterDTO;
import com.hrghs.xycb.domains.banmaerpDTO.ProductDescriptionsDTO;
import com.hrghs.xycb.domains.banmaerpDTO.ProductSkusDTO;
import com.hrghs.xycb.domains.banmaerpDTO.ProductSpuDTO;
import com.sun.org.apache.bcel.internal.generic.IF_ACMPEQ;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static jodd.util.StringPool.LEFT_SQ_BRACKET;

public class ProductDeserialiser {
    private static ObjectMapper objectMapper = new ObjectMapper().registerModule(new JodaModule());
    private static Gson gson = new GsonBuilder().disableHtmlEscaping()
            .setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)
            .create();

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
    public static class ProductSkusDeserializer extends JsonDeserializer<List<ProductSkusDTO>> {
        @Override
        public List<ProductSkusDTO> deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JacksonException {
            JsonNode jsonElements  = p.readValueAsTree();
            if (jsonElements!=null && !jsonElements.isEmpty()){
                if (jsonElements.isArray() && jsonElements.size() > 0){
                    Type type = new TypeToken<Collection<ProductSkusDTO>>(){}.getType();
                    return gson.fromJson(jsonElements.toPrettyString(),type);
                }else{
                    return new ArrayList<>();
                }
            }
            return null;
        }
    }
    public static class OrderMasterDeserializer extends JsonDeserializer<OrderMasterDTO> {
        @Override
        public OrderMasterDTO deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JacksonException {
            return objectMapper.convertValue(p.getText(),OrderMasterDTO.class);
        }
    }

}
