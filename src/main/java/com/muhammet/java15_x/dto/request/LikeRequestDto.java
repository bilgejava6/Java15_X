package com.muhammet.java15_x.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record LikeRequestDto(
        @NotNull
        @Min(1)
        Long postId,
        @NotNull
        @Size(min = 64)
        String token
) {
}
