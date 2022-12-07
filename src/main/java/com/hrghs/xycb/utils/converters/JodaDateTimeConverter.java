package com.hrghs.xycb.utils.converters;

import com.hrghs.xycb.utils.DateTimeUtils;
import org.joda.time.DateTime;

import javax.persistence.AttributeConverter;
import java.sql.Timestamp;

public class JodaDateTimeConverter implements AttributeConverter<DateTime, Timestamp> {
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
