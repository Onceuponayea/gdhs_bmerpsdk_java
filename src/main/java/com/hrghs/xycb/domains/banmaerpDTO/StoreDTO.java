package com.hrghs.xycb.domains.banmaerpDTO;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.hrghs.xycb.domains.BanmaerpProperties;
import com.hrghs.xycb.utils.converters.JodaDateTimeConverter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.joda.time.DateTime;
import org.springframework.stereotype.Component;
import javax.persistence.*;

@Component
@Data

@AllArgsConstructor
@NoArgsConstructor
@JsonTypeInfo(include = JsonTypeInfo.As.WRAPPER_OBJECT,use = JsonTypeInfo.Id.NAME)
@JsonTypeName("Store")
@Entity
@Table(name = "bmerp_store")
public class StoreDTO {
    @Id
    @Column(name = "id")
    @JsonProperty(value = "ID")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long ID;
    @JsonProperty(value = "Name")
    private String name;
    @JsonProperty(value = "Platform")
    private String platform;


    @Convert(converter = JodaDateTimeConverter.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss")
    @JsonProperty(value = "CreateTime")
    @Column(name = "create_time",columnDefinition = "TIMESTAMP COMMENT 'Store_CreateTime' ")
    private DateTime createTime;
    @Convert(converter = JodaDateTimeConverter.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss")
    @JsonProperty(value = "UpdateTime")
    @Column(name = "update_time",columnDefinition = "TIMESTAMP COMMENT 'Store_UpdateTime' ")
    private DateTime updateTime;

    @JsonIgnore
    //@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "banma_master_app_id")
    private BanmaerpProperties banmaerpProperties;
}
