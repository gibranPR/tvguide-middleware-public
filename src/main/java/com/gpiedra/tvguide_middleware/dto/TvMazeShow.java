package com.gpiedra.tvguide_middleware.dto;

import java.util.List;
import com.fasterxml.jackson.annotation.*;
//Este para que funcione en ambas fuentes
@JsonIgnoreProperties(ignoreUnknown = true)
public record TvMazeShow(
    long id,
    String name,
    String summary,
    List<String>genres,
    TvMazeChannel network,
    TvMazeChannel webChannel
) {
}
