package com.gpiedra.tvguide_middleware.service;

import com.gpiedra.tvguide_middleware.client.TvClient;
import com.gpiedra.tvguide_middleware.dto.ShowResponse;
import com.gpiedra.tvguide_middleware.dto.TvMazeSearchItem;
import org.springframework.stereotype.Service;
import com.gpiedra.tvguide_middleware.repository.ShowRepository;

import java.util.List;
import java.util.Objects;

@Service
public class ShowService {

    private final TvClient tvClient;
    private final ShowMapper showMapper;
    private final ShowRepository showRepository;
    private final CommentService commentService;

    public ShowService(TvClient tvClient, ShowMapper showMapper, ShowRepository showRepository, CommentService commentService) {
        this.tvClient = tvClient;
        this.showMapper = showMapper;
        this.showRepository = showRepository;
        this.commentService = commentService;
    }

    public List<ShowResponse> search(String searchQuery) {
        return tvClient.search(searchQuery).stream()
                .map(TvMazeSearchItem::show)
                .filter(Objects::nonNull)
                .map(showMapper::toResponse)
                .map(this::withComments)
                .toList();
    }

    public ShowResponse getById(long id) {
        ShowResponse show = showRepository.findById(id)
                .map(showMapper::fromDocument)
                .orElseGet(() -> fetchAndStore(id));
        return withComments(show);
    }


    private ShowResponse fetchAndStore(long id) {
        ShowResponse response = showMapper.toResponse(tvClient.getShow(id));
        showRepository.save(showMapper.toDocument(response));
        return response;
    }

    private ShowResponse withComments(ShowResponse show) {
        return new ShowResponse(
                show.id(),
                show.name(),
                show.channel(),
                show.summary(),
                show.genres(),
                commentService.findByShowId(show.id())
        );
    }
}