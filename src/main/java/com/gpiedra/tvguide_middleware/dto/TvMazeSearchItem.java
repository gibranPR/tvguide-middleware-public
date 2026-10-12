package com.gpiedra.tvguide_middleware.dto;

import com.fasterxml.jackson.annotation.*;
/*
https://api.tvmaze.com/search/shows?q=name 
Regresa "score" y "show"
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public record TvMazeSearchItem(double score, TvMazeShow show) {
}