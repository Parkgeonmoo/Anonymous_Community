package com.example.anonymous_community;


import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;


@SpringBootApplication
@EnableJpaAuditing
public class AnonymousCommunityApplication {

    public static void main(String[] args) {
        SpringApplication.run(AnonymousCommunityApplication.class, args);

        //log.error("에러 테스트");

    }
}
