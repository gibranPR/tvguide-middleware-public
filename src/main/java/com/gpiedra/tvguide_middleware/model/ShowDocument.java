package com.gpiedra.tvguide_middleware.model;

import org.springframework.data.annotation.*;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document("shows")
public record ShowDocument(
        @Id Long id,
        String name,
        String channel,
        String summary,
        List<String> genres
) {
}
