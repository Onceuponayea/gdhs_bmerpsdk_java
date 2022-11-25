package com.hrghs.xycb.domains.banmaerpDTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.hrghs.xycb.utils.JpaJodaDateTimeConverter;
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
@JsonTypeName("Account")
@Entity
@Table(name = "bmerp_account")
public class AccountDTO {

    @Id
    @JsonProperty(value = "ID")
    private int ID;

    @Column(name = "real_name")
    @JsonProperty(value = "RealName")
    private String realName;

    @JsonProperty(value = "Email")
    private String email;

    @JsonProperty(value = "Phone")
    private String phone;

    @JsonProperty(value = "Department")
    private String department;
    @Convert(converter = JpaJodaDateTimeConverter.class)
    @Column(name = "create_time")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonProperty(value = "CreateTime")
    private DateTime createTime;
    @Convert(converter = JpaJodaDateTimeConverter.class)
    @Column(name = "update_time")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonProperty(value = "UpdateTime")
    private DateTime updateTime;

}
