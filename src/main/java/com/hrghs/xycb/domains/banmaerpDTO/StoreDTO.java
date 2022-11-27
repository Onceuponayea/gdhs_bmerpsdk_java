package com.hrghs.xycb.domains.banmaerpDTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.hrghs.xycb.domains.enums.BanmaerpPlatformEnums;
import com.hrghs.xycb.utils.converters.JodaDateTimeConverter;
import com.hrghs.xycb.utils.converters.JodaDateTimeDeserialiser;
import com.hrghs.xycb.utils.converters.JodaDateTimeSerialiser;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;
import org.joda.time.DateTime;
import org.joda.time.contrib.hibernate.PersistentDateTime;
import org.springframework.stereotype.Component;

import javax.persistence.*;

@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonTypeInfo(include = JsonTypeInfo.As.WRAPPER_OBJECT, use = JsonTypeInfo.Id.NAME)
@JsonTypeName("Store")
@Entity
@Table(name = "bmerp_store")
public class StoreDTO {
    @Id
    @Column(name = "id")
    @JsonProperty(value = "ID")
    private String ID;

    @Column(name = "name")
    @JsonProperty(value = "Name")
    private String name;

    @Column(name = "platform")
    @JsonProperty(value = "Platform")
    private BanmaerpPlatformEnums.Platform platform;

    @Column(name = "create_time")
    @Convert(converter = JodaDateTimeConverter.class)
    @JsonProperty(value = "CreateTime")
    private DateTime createTime;

    @Column(name = "update_time")
    @Convert(converter = JodaDateTimeConverter.class)
    @JsonProperty(value = "UpdateTime")
    private DateTime updateTime;
}
