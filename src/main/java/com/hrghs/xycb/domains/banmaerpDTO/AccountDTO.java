package com.hrghs.xycb.domains.banmaerpDTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
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
@JsonTypeName("Account")
@Entity
@Table(name = "bmerp_account")
public class AccountDTO {

    @Id
    @Column(name = "id")
    @JsonProperty(value = "ID")
    private Integer ID ;

    @Column(name = "real_name")
    @JsonProperty(value = "RealName")
    private String realName;

    @JsonProperty(value = "Email")
    private String email;

    @JsonProperty(value = "Phone")
    private String phone;

    @JsonProperty(value = "Department")
    private String department;

    @Column(name = "create_time")
    @Convert(converter = JodaDateTimeConverter.class)
    @JsonProperty(value = "CreateTime")
    private DateTime createTime;

    @Column(name = "update_time")
    @Convert(converter = JodaDateTimeConverter.class)
    @JsonProperty(value = "UpdateTime")
    private DateTime updateTime;

}
