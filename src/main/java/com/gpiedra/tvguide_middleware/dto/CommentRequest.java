package com.gpiedra.tvguide_middleware.dto;

import com.fasterxml.jackson.annotation.*;
import jakarta.validation.constraints.*;

public record CommentRequest(
        @NotNull @JsonProperty("show_id") Long showId,
        @NotBlank String comment,
        @NotNull @Min(0) @Max(5) Integer rating
) {
}