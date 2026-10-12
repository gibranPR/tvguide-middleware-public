package com.gpiedra.tvguide_middleware.repository;

import com.gpiedra.tvguide_middleware.model.ShowDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ShowRepository extends MongoRepository<ShowDocument, Long> {
}