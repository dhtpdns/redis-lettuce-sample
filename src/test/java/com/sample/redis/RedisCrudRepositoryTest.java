package com.sample.redis;

import com.sample.redis.entity.RedisCrud;
import com.sample.redis.exception.RedisCrudNotFoundException;
import com.sample.redis.repository.RedisCrudRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisCrudRepositoryTest {

    @Autowired
    private RedisCrudRepository redisCrudRepository;

//    @After
//    public void tearDown() throws Exception {
//        redisCrudRepository.deleteAll();
//    }

//    @Before
//    public void tearDown() throws Exception {
//        redisCrudRepository.deleteAll();
//    }

    @Test
    public void 기본_등록_조회() {
        // given
        Long id = 0L;
        String description = "description";
        LocalDateTime updatedAt = LocalDateTime.of(2021, 01, 16, 0, 0);

        RedisCrud redisCrudSave = RedisCrud.builder()
                .id(id)
                .description(description)
                .updatedAt(updatedAt)
                .build();

        // when
        redisCrudRepository.save(redisCrudSave);

        // then
        RedisCrud redisCrudFind = redisCrudRepository.findById(id).get();
        assertThat(redisCrudFind.getDescription()).isEqualTo("description");
        assertThat(redisCrudFind.getUpdatedAt()).isEqualTo(updatedAt);
    }

    @Test
    public void description_조회2() {
        // given
        String description = "description";
        //when
        ///Optional<RedisCrud> redisCrudFind  = Optional.ofNullable(redisCrudRepository.findByDescription(description));

        RedisCrud redisCrudFind = redisCrudRepository.findByDescription(description).orElseThrow(() -> new RedisCrudNotFoundException("조회 실패"));
        //then
        assertThat(redisCrudFind.getDescription()).isEqualTo("description");
    }

    @Test(expected=RedisCrudNotFoundException.class)
    public void description_조회_실패() {
        // given
        String description = "description1";
        //when
        ///Optional<RedisCrud> redisCrudFind  = Optional.ofNullable(redisCrudRepository.findByDescription(description));

        RedisCrud redisCrudFind = redisCrudRepository.findByDescription(description).orElseThrow(() -> new RedisCrudNotFoundException("조회 실패"));
        fail("조회 실패");
    }

    @Test
    public void 기본_등록_수정() {
        // given
        Long id = 0L;
        String description = "description";
        LocalDateTime updatedAt = LocalDateTime.of(2021, 01, 16, 0, 0);

        RedisCrud redisCrudSave = RedisCrud.builder()
                .id(id)
                .description(description)
                .updatedAt(updatedAt)
                .build();

        redisCrudRepository.save(redisCrudSave);

        // when
        RedisCrud redisCrudUpdate = redisCrudRepository.findById(id).get();
        redisCrudUpdate.update("updated description", LocalDateTime.of(2020,10, 17, 0, 0));
        redisCrudRepository.save(redisCrudUpdate);

        // then
        RedisCrud redisCrudFind = redisCrudRepository.findById(id).get();
        assertThat(redisCrudFind.getDescription()).isEqualTo("updated description");
    }
}