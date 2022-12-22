package com.hrghs.xycb.domains.banmaerpDTO;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.hrghs.xycb.domains.enums.BanmaerpAccountEnums;
import com.hrghs.xycb.utils.converters.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;
import org.joda.time.DateTime;
import org.springframework.stereotype.Component;
import java.util.List;
import javax.persistence.*;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonTypeInfo(include = JsonTypeInfo.As.WRAPPER_OBJECT, use = JsonTypeInfo.Id.NAME)
@JsonTypeName("DataAccess")
@Component
@Entity
@Table(name = "bmerp_account_data_access")
public class DataAccessDTO {

    @JsonIgnore
    @Convert(converter = JpaUUIDConverter.class)
    @GeneratedValue(generator = "UUID")
    @Type(type = "uuid-char")
    @Id
    private UUID id;

    @JsonProperty("DataAccessMode")
    @JsonFormat(shape = JsonFormat.Shape.OBJECT)
    @JsonSerialize(using = EnumeratorSerialiser.DataAccessSerialiser.class)
    @JsonDeserialize(using = EnumeratorDeserialiser.DataAccessDeSerialiser.class)
    @Column(name = "data_access_mode")
    private BanmaerpAccountEnums.DataAccessMode dataAccessMode;

    @Convert(converter = JpaListStringConverter.class)
    @JsonProperty("Data")
    private List<String> data;

    @Convert(converter = JodaDateTimeConverter.class)
    @JsonProperty(value = "CreateTime")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Column(name = "create_time")
    private DateTime createTime;

    @Convert(converter = JodaDateTimeConverter.class)
    @JsonProperty(value = "UpdateTime")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Column(name = "update_time")
    private DateTime updateTime;

    @JsonIgnore
    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER,orphanRemoval = true)
    @JsonBackReference
    @JoinColumn(name = "banma_account_id", referencedColumnName = "id")
    private AccountDTO accountDTO;
}
