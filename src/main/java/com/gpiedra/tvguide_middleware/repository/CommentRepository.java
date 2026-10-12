package com.gpiedra.tvguide_middleware.repository;

import com.gpiedra.tvguide_middleware.model.CommentDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface CommentRepository extends MongoRepository<CommentDocument, String> {
    List<CommentDocument> findByShowIdOrderByIdAsc(Long showId);
}