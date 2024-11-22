package com.muhammet.java15_x.dto.response;

public record AllPostsResponseDto(
        Long id,
        String userName,
        String name,
        String avatar,
        String comment,
        String imageUrl,
        Long date,
        Integer commentCount,
        Integer likeCount,
        Integer viewCount,
        Long userId,
        Integer reteweetCount
) {
}
