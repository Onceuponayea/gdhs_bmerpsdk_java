package com.hrghs.xycb.domains.common;

import com.hrghs.xycb.utils.converters.JodaDateTimeConverter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.joda.time.DateTime;

import javax.persistence.*;

/**
 * @@implNote Only Logging error response for master account registration.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "bmerp_response_log")
public class BanmaErpResponseLog {

    @Id
    @Column(name = "request_id")
    private String requestId;

    @Column(name = "request_phone")
    private Long requestPhone;

    @Column(name = "request_body")
    private String requestBody;

    @Column(name = "request_time")
    @Convert(converter = JodaDateTimeConverter.class)
    private DateTime requestTime;

    @Column(name = "response_body")
    private String responseBody;

    private Boolean success;
}
