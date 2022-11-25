package com.hrghs.xycb.utils;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.sql.Timestamp;

@Converter
public class JpaJodaDateTimeConverter implements AttributeConverter<DateTime, Timestamp> {
    private DateTimeUtils dateTimeUtils = new DateTimeUtils();
    @Override
    public Timestamp convertToDatabaseColumn(DateTime attribute) {
        return dateTimeUtils.joda2SQLTimeStamp(attribute);
    }

    @Override
    public DateTime convertToEntityAttribute(Timestamp dbData) {
        return dateTimeUtils.sqlTimeStamp2JodaDateTime(dbData);
    }
}
