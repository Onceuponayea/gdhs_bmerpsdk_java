package com.hrghs.xycb.utils.converters;

import com.hrghs.xycb.domains.enums.BanmaerpAccountEnums;
import org.springframework.util.StringUtils;

import javax.persistence.AttributeConverter;

public class JpaEnumConverters {
    public static class UserTypeConverter implements AttributeConverter<BanmaerpAccountEnums.UserType,String> {
        @Override
        public String convertToDatabaseColumn(BanmaerpAccountEnums.UserType attribute) {
            return attribute==null?"":attribute.name();
        }

        @Override
        public BanmaerpAccountEnums.UserType convertToEntityAttribute(String dbData) {
            return StringUtils.hasText(dbData)?BanmaerpAccountEnums.UserType.valueOf(dbData): BanmaerpAccountEnums.UserType.Unknown;
        }
    }
    public static class UserStateConverter implements AttributeConverter<BanmaerpAccountEnums.UserState,String> {
        @Override
        public String convertToDatabaseColumn(BanmaerpAccountEnums.UserState attribute) {
            return attribute==null?"":attribute.name();
        }

        @Override
        public BanmaerpAccountEnums.UserState convertToEntityAttribute(String dbData) {
            return StringUtils.hasText(dbData)?BanmaerpAccountEnums.UserState.valueOf(dbData): BanmaerpAccountEnums.UserState.Unknown;
        }
    }
}
