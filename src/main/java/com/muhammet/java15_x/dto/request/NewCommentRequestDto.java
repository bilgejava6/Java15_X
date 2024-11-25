package com.muhammet.java15_x.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record NewCommentRequestDto(
        @NotNull
        @Size(min = 32)
        String token,
        @NotNull
        Long postId,
        @NotNull
        @Size(min = 3)
        String comment
) {
}
