package com.hrghs.xycb.utils.converters;

import com.hrghs.xycb.domains.banmaerpDTO.OrderAccessUsersDTO;

import javax.persistence.AttributeConverter;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static jodd.util.StringPool.COMMA;
import static jodd.util.StringPool.DASH;

public class JpaUserAccessOrderConverter implements AttributeConverter<List<OrderAccessUsersDTO>,String> {
    @Override
    public String convertToDatabaseColumn(List<OrderAccessUsersDTO> attribute) {
        return attribute.parallelStream().map(orderAccessUsersDTO -> orderAccessUsersDTO.getUserID()+DASH+orderAccessUsersDTO.getDataAccessMode())
                .collect(Collectors.joining(COMMA));
    }

    @Override
    public List<OrderAccessUsersDTO> convertToEntityAttribute(String dbData) {
        return
        Arrays.stream(dbData.split(COMMA)).map(user -> user.split(DASH))
                .map(arrays -> new OrderAccessUsersDTO(Integer.parseInt(arrays[0]),arrays[1])).collect(Collectors.toList());
    }
}
