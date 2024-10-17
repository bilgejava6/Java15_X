package com.muhammet.java15_x.mapper;

import com.muhammet.java15_x.dto.request.RegisterRequestDto;
import com.muhammet.java15_x.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;


@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);


    @Mapping(target ="memberDate", expression = "java(System.currentTimeMillis())")
    @Mapping(target ="createdDate", expression = "java(System.currentTimeMillis())")
    @Mapping(target ="modifiedDate", expression = "java(System.currentTimeMillis())")
    User fromRegisterDto(RegisterRequestDto dto);
}
