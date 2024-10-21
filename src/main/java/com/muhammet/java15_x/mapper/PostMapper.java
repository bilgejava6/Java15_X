package com.muhammet.java15_x.mapper;

import com.muhammet.java15_x.dto.request.NewPostRequestDto;
import com.muhammet.java15_x.dto.response.AllPostsResponseDto;
import com.muhammet.java15_x.entity.Post;
import com.muhammet.java15_x.entity.PostState;
import com.muhammet.java15_x.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface PostMapper {
    PostMapper INSTANCE = Mappers.getMapper(PostMapper.class);

    @Mapping(target = "date", expression = "java(System.currentTimeMillis())")
    @Mapping(target = "commentCount", expression = "java(Integer.valueOf(0))")
    @Mapping(target = "likeCount",expression = "java(Integer.valueOf(0))")
    @Mapping(target = "viewCount", expression = "java(Integer.valueOf(0))")
    Post fromNewPostDto(final NewPostRequestDto dto,final Long userId);

    AllPostsResponseDto fromPostAndUser(final Post post, String userName, String name, String avatar);

}
