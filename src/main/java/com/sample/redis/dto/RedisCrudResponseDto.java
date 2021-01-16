package com.sample.redis.dto;

import com.sample.redis.entity.RedisCrud;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class RedisCrudResponseDto {

    private Long id;
    private String description;
    private LocalDateTime updatedAt;

    public RedisCrudResponseDto(RedisCrud redisHash) {
        this.id = redisHash.getId();
        this.description = redisHash.getDescription();
        this.updatedAt = redisHash.getUpdatedAt();
    }
}