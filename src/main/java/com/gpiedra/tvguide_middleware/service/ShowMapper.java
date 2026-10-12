package com.gpiedra.tvguide_middleware.service;

import com.gpiedra.tvguide_middleware.dto.ShowResponse;
import com.gpiedra.tvguide_middleware.dto.TvMazeShow;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ShowMapper {

    public ShowResponse toResponse(TvMazeShow show) {
        return new ShowResponse(
                show.id(),
                show.name(),
                channelOf(show),
                show.summary(),
                show.genres() == null ? List.of() : List.copyOf(show.genres())
        );
    }

    private String channelOf(TvMazeShow show) {
        if (show.network() != null && hasText(show.network().name())) {
            return show.network().name();
        }
        if (show.webChannel() != null && hasText(show.webChannel().name())) {
            return show.webChannel().name();
        }
        return null;
    }

    private boolean hasText(String value) {
        return value != null && !value.isBlank();
    }
}