package com.hrghs.xycb.domains.banmaerpDTO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "bmerp_properties")
@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BmerpPropertiesDTO {

    @JsonIgnore
    @GeneratedValue
    @Type(type = "uuid-char")
    @Id
    private UUID id;
    @Column
    private  String X_BANMA_APP_NAME;
    @Column
    private  String X_BANMA_MASTER_APP_ID;
    @Column
    private  String X_BANMA_MASTER_APP_SECRET;
    @Column
    private  String X_BANMA_MASTER_APP_ACCOUNT;
    @Column
    private  String X_BANMA_MASTER_SIGN_METHOD;
    @Column
    private  String X_BANMA_MASTER_SIGN_ALGORITHM;
}
