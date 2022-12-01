package com.hrghs.xycb.domains.common;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.joda.JodaModule;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.hrghs.xycb.domains.banmaerpDTO.*;
import com.hrghs.xycb.utils.DateTimeConverter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;
import org.joda.time.DateTime;
import org.springframework.stereotype.Component;
import java.lang.reflect.Type;
import java.util.*;

import static com.hrghs.xycb.domains.Constants.*;

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
    private ObjectMapper objectMapper = new ObjectMapper().registerModule(new JodaModule()).setTimeZone(SimpleTimeZone.getDefault());
    @JsonIgnore
    private Gson gson = new GsonBuilder().disableHtmlEscaping()
            .setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)
            .registerTypeAdapter(DateTime.class,new DateTimeConverter())
            .setDateFormat("yyyy-MM-dd HH:mm:ss")
            .create();

    @SneakyThrows
    public Object[] toDataList(String objClass){
        List<Object> datas = new ArrayList<>();
        if (data !=null && data instanceof JsonNode){
            JsonNode dataList = (JsonNode)data;
            if(dataList.has(objClass)){
                JsonNode jsonNodeSrc = dataList.get(objClass);
                Type type = null;
                switch (objClass){
                    case BANMAERP_FIELD_STORES:
                        type = new TypeToken<Collection<StoreDTO>>(){}.getType();
                        //Collection<StoreDTO> storeDTOS = gson.fromJson(jsonNodeStores.toString(),store);
                        break;
                    case BANMAERP_FIELD_CATEGORYS:
                        type = new TypeToken<Collection<CategoryDTO>>(){}.getType();
                        break;
                    case BANMAERP_FIELD_ORDERS:
                        type = new TypeToken<Collection<OrderDTO>>(){}.getType();
                        break;
                    case BANMAERP_FIELD_PRODUCTS:
                        type = new TypeToken<Collection<ProductDTO>>(){}.getType();
                        break;
                    case BANMAERP_FIELD_SKUS:
                        type = new TypeToken<Collection<ProductSkusDTO>>(){}.getType();
                        break;
                    case BANMAERP_FIELD_TAGS:
                        type = new TypeToken<Collection<ProductTagsDTO>>(){}.getType();
                        break;
                    case BANMAERP_FIELD_SUPPLIERS:
                        type = new TypeToken<Collection<ProductSuppliersInfoDTO>>(){}.getType();
                        break;
                    case BANMAERP_FIELD_STORAGES:
                        type = new TypeToken<Collection<StorageDTO>>(){}.getType();
                        break;
                    case BANMAERP_FIELD_PAGE:
                        type = new TypeToken<Collection<PageDTO>>(){}.getType();
                        break;
                    case BANMAERP_FIELD_ACCOUNTS:
                        type = new TypeToken<Collection<AccountDTO>>(){}.getType();
                        break;
                    case BANMAERP_FIELD_FULFILLMENTS:
                        type = new TypeToken<Collection<OrderFulfillmentDTO>>(){}.getType();
                        break;
                    case BANMAERP_FIELD_TRACKINGS:
                        type = new TypeToken<Collection<OrderTrackingDTO>>(){}.getType();
                        break;
                }
                datas = gson.fromJson(jsonNodeSrc.toString(),type);
            }
        }
        return datas.toArray();
    }
}
