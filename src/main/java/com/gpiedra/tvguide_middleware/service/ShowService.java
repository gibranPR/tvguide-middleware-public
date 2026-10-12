package com.gpiedra.tvguide_middleware.service;

import com.gpiedra.tvguide_middleware.client.TvClient;
import com.gpiedra.tvguide_middleware.dto.ShowResponse;
import com.gpiedra.tvguide_middleware.dto.TvMazeSearchItem;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class ShowService {

    private final TvClient tvClient;
    private final ShowMapper showMapper;

    public ShowService(TvClient tvClient, ShowMapper showMapper) {
        this.tvClient = tvClient;
        this.showMapper = showMapper;
    }

    public List<ShowResponse> search(String searchQuery) {
        return tvClient.search(searchQuery).stream()
                .map(TvMazeSearchItem::show)
                .filter(Objects::nonNull)
                .map(showMapper::toResponse)
                .toList();
    }
    public ShowResponse getById(long id) {
    return showMapper.toResponse(tvClient.getShow(id));
}
}