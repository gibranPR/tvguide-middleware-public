package com.gpiedra.tvguide_middleware.dto;

import java.util.List;

public record ShowResponse(
    long id,
    String name,
    String channel,
    String summary,
    List<String> genres,
    List<CommentItem> comments
)
{    
}
