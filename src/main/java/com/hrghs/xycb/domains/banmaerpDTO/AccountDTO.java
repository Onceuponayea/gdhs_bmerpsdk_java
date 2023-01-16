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
import java.util.List;

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
    public AccountDTO(Integer _id){
        this.ID = _id;
    }

    @Id
    @Column(name = "id")
    @JsonProperty(value = "ID")
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
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
    /* banma-erp not compatible with json datetime format */
    //@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss")
    @JsonProperty(value = "CreateTime")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private DateTime createTime;

    @Column(name = "updateTime")
    @Convert(converter = JodaDateTimeConverter.class)
    //@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss")
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
     * 暂时不清楚斑马erp的Account是关联主账号还是app，如果一个主账号创建多个app，用app1接口创建的子账号能够被app2使用吗
     */
    @JsonIgnore
    @JoinColumn(name = "banma_master_app_id")
    /* cscade ALL will erase all records with same banma_master_app_id */
    @ManyToOne(fetch = FetchType.LAZY)
    BanmaerpProperties banmaerpProperties;
    /**
     * @@apiNote
     * 如果是主账号则有多个店铺列表，如果是子账号就只有一个（目前也是多个）
     */
    @JsonIgnore
    @JoinColumn(name = "banma_account_id" )
    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    List<StoreDTO> storeDTOList;

    @JsonIgnore
    @JsonManagedReference
    @JoinColumn(name = "banma_data_access_id", referencedColumnName = "id")
    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER,orphanRemoval = true)
    DataAccessDTO dataAccessDTO;


}
