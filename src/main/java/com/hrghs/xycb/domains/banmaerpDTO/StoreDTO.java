package com.hrghs.xycb.domains.banmaerpDTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.joda.time.DateTime;
import org.springframework.stereotype.Component;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Component
@Data

@AllArgsConstructor
@NoArgsConstructor
@JsonTypeInfo(include = JsonTypeInfo.As.WRAPPER_OBJECT, use = JsonTypeInfo.Id.NAME)
//@JsonTypeInfo(include = JsonTypeInfo.As.EXISTING_PROPERTY, use = JsonTypeInfo.Id.NAME, visible = true, property = "Stores")
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
    private String platform;

    @Column(name = "createTime")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonProperty(value = "CreateTime")
    private String createTime;

    @Column(name = "updateTime")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonProperty(value = "UpdateTime")
    private String updateTime;
}
