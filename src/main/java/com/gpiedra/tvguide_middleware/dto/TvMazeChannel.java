package com.gpiedra.tvguide_middleware.dto;

import com.fasterxml.jackson.annotation.*;
//Este lo metemos para ignorar los campos que no nos importan
@JsonIgnoreProperties(ignoreUnknown = true)
public record TvMazeChannel(String name){

}