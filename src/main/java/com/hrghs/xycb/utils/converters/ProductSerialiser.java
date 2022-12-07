package com.hrghs.xycb.utils.converters;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.datatype.joda.JodaModule;
import com.hrghs.xycb.domains.banmaerpDTO.OrderMasterDTO;
import com.hrghs.xycb.domains.banmaerpDTO.ProductDescriptionsDTO;
import com.hrghs.xycb.domains.banmaerpDTO.ProductSkusDTO;
import com.hrghs.xycb.domains.banmaerpDTO.ProductSpuDTO;
import java.io.IOException;
import java.util.List;

public class ProductSerialiser {
    private static ObjectMapper objectMapper = new ObjectMapper().registerModule(new JodaModule());

    public static class ProductSpuSerializer extends JsonSerializer<ProductSpuDTO> {
        @Override
        public void serialize(ProductSpuDTO value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
            JsonNode jsonNode =objectMapper.convertValue(value, JsonNode.class);
            String result = jsonNode!=null? jsonNode.toPrettyString() : "";
            gen.writeString(result);
        }
    }
    public static class ProductDescriptionsSerializer extends JsonSerializer<ProductDescriptionsDTO> {
        @Override
        public void serialize(ProductDescriptionsDTO value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
            JsonNode jsonNode =objectMapper.convertValue(value, JsonNode.class);
            String result = jsonNode!=null? jsonNode.toPrettyString() : "";
            gen.writeString(result);
        }
    }
    public static class ProductSkusSerializer extends JsonSerializer<List<ProductSkusDTO>> {
        @Override
        public void serialize(List<ProductSkusDTO> value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
            JsonNode jsonNode =objectMapper.convertValue(value, JsonNode.class);
            String result = jsonNode!=null? jsonNode.toPrettyString() : "";
            gen.writeString(result);
        }
    }
    public static class OrderMasterSerializer extends JsonSerializer<OrderMasterDTO> {
        @Override
        public void serialize(OrderMasterDTO value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
            JsonNode jsonNode =objectMapper.convertValue(value, JsonNode.class);
            String result = jsonNode!=null? jsonNode.toPrettyString() : "";
            gen.writeString(result);
        }
    }



}
