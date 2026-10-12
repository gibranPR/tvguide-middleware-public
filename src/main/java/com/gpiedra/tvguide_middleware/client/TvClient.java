package com.gpiedra.tvguide_middleware.client;

import com.gpiedra.tvguide_middleware.dto.TvMazeSearchItem;
import com.gpiedra.tvguide_middleware.dto.TvMazeShow;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.util.List;

@Component
public class TvClient {
    private static final ParameterizedTypeReference<List<TvMazeSearchItem>> SEARCH_RESULT =
            new ParameterizedTypeReference<>() {
            };
    
    private final RestClient restClient;

    public TvClient(RestClient.Builder builder) {
        this.restClient = builder.baseUrl("https://api.tvmaze.com").build();
    }

    public List<TvMazeSearchItem> search(String query) {
        List<TvMazeSearchItem> results = restClient.get()
                .uri(uriBuilder -> uriBuilder.path("/search/shows").queryParam("q", query).build())
                .retrieve()
                .body(SEARCH_RESULT);
        return results == null ? List.of() : results;
    }

    public TvMazeShow getShow(long id) {
    return restClient.get()
            .uri("/shows/{id}", id)
            .retrieve()
            .body(TvMazeShow.class);
    }
}