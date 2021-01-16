package com.sample.redis.repository;

import com.sample.redis.controller.RedisController;
import com.sample.redis.entity.RedisCrud;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface RedisCrudRepository extends CrudRepository<RedisCrud, Long> {
    Optional<RedisCrud>   findByDescription(String description);
}
