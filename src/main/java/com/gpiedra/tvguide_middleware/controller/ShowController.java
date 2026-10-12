package com.gpiedra.tvguide_middleware.controller;

import com.gpiedra.tvguide_middleware.dto.ShowResponse;
import com.gpiedra.tvguide_middleware.service.ShowService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/shows")
public class ShowController {

    private final ShowService showService;

    public ShowController(ShowService showService) {
        this.showService = showService;
    }

    @GetMapping("/search")
    public List<ShowResponse> search(@RequestParam("search_query") String searchQuery) {
        return showService.search(searchQuery);
    }
}