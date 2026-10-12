package com.gpiedra.tvguide_middleware.controller;

import com.gpiedra.tvguide_middleware.dto.*;
import com.gpiedra.tvguide_middleware.service.*;
import jakarta.validation.Valid;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comments")
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping
    public ResponseEntity<CommentStatusResponse> create(@Valid @RequestBody CommentRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(commentService.save(request));
    }
}