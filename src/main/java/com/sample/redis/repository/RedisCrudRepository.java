package com.sample.redis.repository;

import com.sample.redis.entity.RedisCrud;
import org.springframework.data.repository.CrudRepository;

public interface RedisCrudRepository extends CrudRepository<RedisCrud, Long> {
}
