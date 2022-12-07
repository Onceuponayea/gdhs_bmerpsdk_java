package com.hrghs.xycb.utils.converters;

import javax.persistence.AttributeConverter;
import java.util.UUID;
import static java.util.Optional.ofNullable;

public class JpaUUIDConverter implements AttributeConverter<UUID,String> {
    @Override
    public String convertToDatabaseColumn(final UUID entityValue) {
        System.out.println("uuid from entity: "+ entityValue.toString());
        return ofNullable(entityValue)
                .map(entityUuid -> entityUuid.toString())
                .orElse(null);
    }

    @Override
    public UUID convertToEntityAttribute(final String databaseValue) {
        System.out.println("uuid from db: "+ databaseValue);
        return ofNullable(databaseValue)
                .map(databaseUuid -> UUID.fromString(databaseUuid))
                .orElse(null);
    }
}
