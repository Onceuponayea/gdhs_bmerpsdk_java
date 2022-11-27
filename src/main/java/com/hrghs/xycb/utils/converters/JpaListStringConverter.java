package com.hrghs.xycb.utils.converters;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static jodd.util.StringPool.COMMA;

@Converter
public class JpaListStringConverter implements AttributeConverter<List<String>,String> {

    @Override
    public String convertToDatabaseColumn(List<String> attribute) {
        StringBuilder stringBuilder = new StringBuilder();
        if (attribute.size()>0){
            for (String s : attribute){
                stringBuilder.append(s).append(COMMA);
            }
            return stringBuilder.substring(0,stringBuilder.length()-1);
        }
        return "";
    }

    @Override
    public List<String> convertToEntityAttribute(String dbData) {
        return Arrays.stream(dbData.split(COMMA))
                .collect(Collectors.toList());
    }
}
