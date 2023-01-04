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
import com.hrghs.xycb.domains.BanmaerpProperties;
import com.hrghs.xycb.domains.banmaerpDTO.*;
import com.hrghs.xycb.domains.enums.BanmaerpAccountEnums;
import com.hrghs.xycb.utils.DateTimeConverter;
import com.hrghs.xycb.utils.converters.EnumeratorDeserialiser;
import com.hrghs.xycb.utils.converters.EnumeratorSerialiser;
import com.hrghs.xycb.utils.converters.JodaDateTimeDeserialiser;
import com.hrghs.xycb.utils.converters.JodaDateTimeSerialiser;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;
import org.joda.time.DateTime;
import org.joda.time.LocalDateTime;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
    public BanmaErpResponseDTO(Integer code,Boolean success,String message){
        this.code = code;
        this.time = LocalDateTime.now().toString();
        this.success = success;
        this.message = message;
        this.tick = null;
        this.data = null;
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
    private ObjectMapper objectMapper = new ObjectMapper().registerModule(new JodaModule()
                    .addDeserializer(DateTime.class, new JodaDateTimeDeserialiser())
                    .addSerializer(DateTime.class,new JodaDateTimeSerialiser())
                    .addSerializer(BanmaerpAccountEnums.DataAccessMode.class,new EnumeratorSerialiser.DataAccessSerialiser())
                    .addDeserializer(BanmaerpAccountEnums.DataAccessMode.class,new EnumeratorDeserialiser.DataAccessDeSerialiser()))
            .setTimeZone(SimpleTimeZone.getDefault());
    @JsonIgnore
    public static Gson gson = new GsonBuilder().disableHtmlEscaping()
            .setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)
            .registerTypeAdapter(DateTime.class,new DateTimeConverter())
            .setDateFormat("yyyy-MM-dd HH:mm:ss")
            .create();

    @SneakyThrows
    public Object[] toDataList(String objClass){
        List<Object> datas = new ArrayList<>();
        if (data !=null && data instanceof JsonNode){
            JsonNode dataList = (JsonNode)data;
            if (objClass.equalsIgnoreCase(BANMAERP_FIELD_DATAACCESS)){
                DataAccessDTO dataAccessDTO = objectMapper.readValue(dataList.toString(),DataAccessDTO.class);
                datas = Arrays.asList(dataAccessDTO);
            }else {
                if(dataList.has(objClass)){
                    JsonNode jsonNodeSrc = dataList.get(objClass);
                    Type type = getGsonType(objClass);
                    datas = gson.fromJson(jsonNodeSrc.toString(),type);
                }
            }
        }
        return datas.toArray();
    }
    public DataAccessDTO toDataAccessDTO(String objClass){
        return  (DataAccessDTO) toDataList(objClass)[0];
    }
    public Page<?> toDataList(Class clazz, BanmaerpProperties banmaerpProperties){
        List<?> datas = new ArrayList<>();
        Long totalSize =0l;
        Integer pageNum = 0;
        Integer pageSize = PAGE_SIZE_DEFAULT;
        if (data !=null && data instanceof JsonNode){
            JsonNode dataList = (JsonNode)data;
            String objClass = classNameMapping.get(clazz);
            if(dataList.has(objClass)){
                JsonNode jsonNodeSrc = dataList.get(objClass);
                Type type = getGsonType(objClass);
                datas = gson.fromJson(jsonNodeSrc.toString(),type);
                if (clazz.getSimpleName().equalsIgnoreCase(ProductSuppliersInfoDTO.class.getSimpleName())){
                    List<ProductSuppliersDTO> suppliers = new ArrayList<>();
                    datas.forEach(supplierInfo -> {
                        ProductSuppliersInfoDTO suppliersInfoDTO = (ProductSuppliersInfoDTO)supplierInfo;
                        ProductSuppliersDTO suppliersDTO = suppliersInfoDTO.toProductSuppliersDTO(null,banmaerpProperties);
                        suppliersDTO.setInfo(suppliersInfoDTO);
                        suppliers.add(suppliersDTO);
                    });
                    datas = suppliers;
                }
            }
            if (dataList.has(BANMAERP_FIELD_PAGE)){
                totalSize = dataList.get(BANMAERP_FIELD_PAGE).get(BANMAERP_FIELD_PAGE_TotalCount).asLong(totalSize);
                pageSize = dataList.get(BANMAERP_FIELD_PAGE).get(BANMAERP_FIELD_PAGE_PageSize).asInt(PAGE_SIZE_DEFAULT);
                pageNum = dataList.get(BANMAERP_FIELD_PAGE).get(BANMAERP_FIELD_PAGE_PageNumber).asInt(1);
            }
        }
        pageNum = pageNum<1?1:pageNum;
        Pageable pageable = PageRequest.of(pageNum-1,pageSize);
        Page<?> pageableObj = new PageImpl<>(datas,pageable,totalSize);
        return pageableObj;
    }
    private static final Map<Class,String> classNameMapping = new HashMap<Class,String>(){
        {
            put(StoreDTO.class,BANMAERP_FIELD_STORES);
            put(CategoryDTO.class,BANMAERP_FIELD_CATEGORYS);
            put(OrderDTO.class,BANMAERP_FIELD_ORDERS);
            put(ProductDTO.class,BANMAERP_FIELD_PRODUCTS);
            put(ProductSkusDTO.class,BANMAERP_FIELD_SKUS);
            put(ProductTagsDTO.class,BANMAERP_FIELD_TAGS);
            put(ProductSuppliersInfoDTO.class,BANMAERP_FIELD_SUPPLIERS);
            put(StorageDTO.class,BANMAERP_FIELD_STORAGES);
            put(PageDTO.class,BANMAERP_FIELD_PAGE);
            put(AccountDTO.class,BANMAERP_FIELD_ACCOUNTS);
            put(OrderFulfillmentDTO.class,BANMAERP_FIELD_FULFILLMENTS);
            put(OrderTrackingDTO.class,BANMAERP_FIELD_TRACKINGS);
        }
    };
    public static Type getGsonType(String objClass){
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
        return type;
    }
}
