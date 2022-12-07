package com.hrghs.xycb.domains.banmaerpDTO;

import com.fasterxml.jackson.annotation.*;
import com.hrghs.xycb.domains.BanmaerpProperties;
import com.hrghs.xycb.domains.enums.BanmaerpAccountEnums;
import com.hrghs.xycb.utils.converters.JodaDateTimeConverter;
import com.hrghs.xycb.utils.converters.JpaEnumConverters;
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
@Entity
@Table(name = "bmerp_account")
@JsonTypeInfo(include = JsonTypeInfo.As.WRAPPER_OBJECT,use = JsonTypeInfo.Id.NAME)
@JsonTypeName("Account")
public class AccountDTO {

    public AccountDTO(String _realName,String _email,String _phone, String _department){
        this.realName = _realName;
        this.email = _email;
        this.phone = _phone;
        this.department = _department;
    }

    @Id
    @Column(name = "id")
    @JsonProperty(value = "ID")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer ID;

    @Column(name = "real_name")
    @JsonProperty(value = "RealName")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String realName;

    @JsonProperty(value = "Email")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String email;

    @JsonProperty(value = "Phone")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String phone;

    @JsonProperty(value = "Department")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String department;

    @Column(name = "createTime")
    @Convert(converter = JodaDateTimeConverter.class)
    @JsonProperty(value = "CreateTime")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private DateTime createTime;

    @Column(name = "updateTime")
    @Convert(converter = JodaDateTimeConverter.class)
    @JsonProperty(value = "UpdateTime")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private DateTime updateTime;

    @Convert(converter = JpaEnumConverters.UserStateConverter.class)
    @JsonIgnore
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private BanmaerpAccountEnums.UserState state;

    @Convert(converter = JpaEnumConverters.UserTypeConverter.class)
    @JsonIgnore
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private BanmaerpAccountEnums.UserType userType;
    /**
     * todo 暂时不清楚斑马erp的Account是关联主账号还是app，如果一个主账号创建多个app，用app1接口创建的子账号能够被app2使用吗
     */
    @JsonIgnore
    @JoinColumn(name = "bmerp_account")
    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    BanmaerpProperties banmaerpProperties;

}
