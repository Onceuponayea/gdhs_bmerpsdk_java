package com.hrghs.xycb.domains.banmaerpDTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.joda.time.DateTime;
import org.springframework.stereotype.Component;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Component
@Data
@Entity
@Table(name = "bmerp_store")
@AllArgsConstructor
@NoArgsConstructor
@JsonTypeInfo(include = JsonTypeInfo.As.WRAPPER_OBJECT,use = JsonTypeInfo.Id.NAME)
//@JsonTypeInfo(include = JsonTypeInfo.As.EXISTING_PROPERTY, use = JsonTypeInfo.Id.NAME, visible = true, property = "Stores")
@JsonTypeName("Store")
public class StoreDTO {
    @Id
    @JsonProperty(value = "ID")
    @Column(name = "ID",columnDefinition = "varchar(100) COMMENT 'StoreID' ")
    private String ID;

    @Column(name = "Name",columnDefinition = "varchar(100) COMMENT 'StoreName' ")
    @JsonProperty(value = "Name")
    private String name;

    @Column(name = "Platform",columnDefinition = "varchar(100) COMMENT 'Store_platform' ")
    @JsonProperty(value = "Platform")
    private String platform;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonProperty(value = "CreateTime")
    @Column(name = "CreateTime",columnDefinition = "DATETIME COMMENT 'Store_CreateTime' ")
    private DateTime createTime;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonProperty(value = "UpdateTime")
    @Column(name = "UpdateTime",columnDefinition = "DATETIME COMMENT 'Store_UpdateTime' ")
    private DateTime updateTime;
}
