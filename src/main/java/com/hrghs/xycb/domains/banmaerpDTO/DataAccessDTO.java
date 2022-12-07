package com.hrghs.xycb.domains.banmaerpDTO;

import com.fasterxml.jackson.annotation.*;
import com.hrghs.xycb.utils.converters.JodaDateTimeConverter;
import com.hrghs.xycb.utils.converters.JpaListStringConverter;
import com.hrghs.xycb.utils.converters.JpaUUIDConverter;
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
    @Column(name = "data_access_mode")
    private String dataAccessMode;

    @Convert(converter = JpaListStringConverter.class)
    @JsonProperty("Data")
    private List<String> data;

    @Convert(converter = JodaDateTimeConverter.class)
    @JsonProperty(value = "CreateTime")
    @Column(name = "create_time")
    private DateTime createTime;

    @Convert(converter = JodaDateTimeConverter.class)
    @JsonProperty(value = "UpdateTime")
    @Column(name = "update_time")
    private DateTime updateTime;
}
