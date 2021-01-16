package com.sample.redis;

import com.sample.redis.dto.RedisCrudSaveRequestDto;
import com.sample.redis.repository.RedisCrudRepository;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RedisControllerTest {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private RedisCrudRepository redisCrudRepository;

    @After
    public void tearDown() throws Exception {
        redisCrudRepository.deleteAll();
    }

    @Test
    public void 접속_기본() {
        // given
        String url = "http://localhost:" + port;

        // when
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(url, String.class);

        // then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isEqualTo("ok");
    }

    @Test
    public void 기본_등록_조회() {
        // given
        RedisCrudSaveRequestDto requestDto = RedisCrudSaveRequestDto.builder()
                .id(1L)
                .description("description")
                .updatedAt(LocalDateTime.now())
                .build();

        String url = "http://localhost:" + port + "/save";

        // when
        ResponseEntity<Long> responseEntity = restTemplate.postForEntity(url, requestDto, Long.class);

        // then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isGreaterThan(0L);
    }

    @Test
    public void SET_멤버_키_조회() {
        // given
        String url = "http://localhost:" + port + "/keys";

        // when
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(url, String.class);

        // then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        logger.info(responseEntity.getBody());
        //assertThat(responseEntity.getBody()).isEqualTo("ok");
    }
}