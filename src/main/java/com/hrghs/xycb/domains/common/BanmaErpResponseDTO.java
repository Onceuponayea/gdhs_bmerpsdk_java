package com.hrghs.xycb.domains.common;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.joda.JodaModule;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.hrghs.xycb.domains.banmaerpDTO.StoreDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Component;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class BanmaErpResponseDTO<T> {
    public BanmaErpResponseDTO(Class<T> clz) {
        try {
            this.data = clz.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
    public BanmaErpResponseDTO(Class<T> clz,Integer code,String time,Boolean success,String message,T data,Long tick) {
        try {
            this.code = code;
            this.time = time;
            this.success = success;
            this.message = message;
            this.tick = tick;
            this.data = clz.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
    @JsonProperty(value = "Code")
    private Integer code;
    @JsonProperty(value = "Time")
    private String time;
    @JsonProperty(value = "Success")
    private Boolean success;
    @JsonProperty(value = "Message")
    private String message;
    @JsonProperty(value = "Data")
    private T data;
    @JsonProperty(value = "Tick")
    private Long tick;
    @JsonProperty(value = "RequestId")
    private String requestId;

    @JsonIgnore
    private ObjectMapper objectMapper = new ObjectMapper().registerModule(new JodaModule());
    @JsonIgnore
    private Gson gson = new Gson();

    @SneakyThrows
    public Object[] toDataList(Class<?> vClass,String objClass){
        List<Object> datas = new ArrayList<>();
        if (data !=null && data instanceof JsonNode){
            JsonNode dataList = (JsonNode)data;
            JsonNode jsonNodeStores = dataList.get(objClass);
//            java.lang.reflect.Type type = vClass;
//
//            Class c =vClass.getClass();
//            new TypeToken<List<String>>(){}.getType();
//            StoreDTO[] storeDTOS = gson.fromJson(jsonNodeStores.toString(),new TypeToken<List<type>>());
            StoreDTO[] storeDTOS = gson.fromJson(jsonNodeStores.toString(),StoreDTO[].class);
            datas.addAll(Arrays.asList(storeDTOS));
        }
        return datas.toArray();
    }
}
