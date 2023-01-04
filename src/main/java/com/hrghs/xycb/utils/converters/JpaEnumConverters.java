package com.hrghs.xycb.utils.converters;

import com.hrghs.xycb.domains.enums.BanmaerpAccountEnums;
import com.hrghs.xycb.domains.enums.BanmaerpOrderEnums;
import org.springframework.util.StringUtils;

import javax.persistence.AttributeConverter;

public class JpaEnumConverters {
    public static class UserTypeConverter implements AttributeConverter<BanmaerpAccountEnums.UserType,String> {
        @Override
        public String convertToDatabaseColumn(BanmaerpAccountEnums.UserType attribute) {
            return attribute==null?"":attribute.getValue();
        }

        @Override
        public BanmaerpAccountEnums.UserType convertToEntityAttribute(String dbData) {
            return StringUtils.hasText(dbData)?BanmaerpAccountEnums.UserType.valueof(dbData): BanmaerpAccountEnums.UserType.Unknown;
        }
    }
    public static class UserStateConverter implements AttributeConverter<BanmaerpAccountEnums.UserState,String> {
        @Override
        public String convertToDatabaseColumn(BanmaerpAccountEnums.UserState attribute) {
            return attribute==null?"":attribute.getValue();
        }

        @Override
        public BanmaerpAccountEnums.UserState convertToEntityAttribute(String dbData) {
            return StringUtils.hasText(dbData)?BanmaerpAccountEnums.UserState.valueof(dbData): BanmaerpAccountEnums.UserState.Unknown;
        }
    }
    public static class OrderStatusConverter implements AttributeConverter<BanmaerpOrderEnums.Status,String>{

        @Override
        public String convertToDatabaseColumn(BanmaerpOrderEnums.Status attribute) {
            return (attribute == BanmaerpOrderEnums.Status.All||
                    attribute == BanmaerpOrderEnums.Status.Unknown||attribute==null)?"":attribute.getValue();
        }

        @Override
        public BanmaerpOrderEnums.Status convertToEntityAttribute(String dbData) {
            return StringUtils.hasText(dbData)? BanmaerpOrderEnums.Status.valueof(dbData): BanmaerpOrderEnums.Status.Unknown;
        }
    }
}
