package com.gpiedra.tvguide_middleware.service;

import com.gpiedra.tvguide_middleware.dto.*;
import com.gpiedra.tvguide_middleware.model.*;
import com.gpiedra.tvguide_middleware.repository.*;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class CommentService {

    private final CommentRepository commentRepository;

    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public CommentStatusResponse save(CommentRequest request) {
        CommentDocument document = new CommentDocument();
        document.setShowId(request.showId());
        document.setComment(request.comment().trim());
        document.setRating(request.rating());
        commentRepository.save(document);
        return new CommentStatusResponse(HttpStatus.CREATED.value());
    }
}