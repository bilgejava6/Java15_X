package com.muhammet.java15_x.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record NewPostRequestDto(
        @NotNull
        @Size(min = 3, max = 160)
        String comment,
        String imageUrl,
        @NotNull
        @Size(min = 50)
        String token
) {
}
