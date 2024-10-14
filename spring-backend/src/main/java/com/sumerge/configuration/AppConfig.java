package com.sumerge.configuration;

import com.sumerge.mapper.AuthorMapper;
import com.sumerge.mapper.CourseMapper;
import org.mapstruct.factory.Mappers;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.*;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@ComponentScan(basePackages = "com.sumerge")
@EnableJpaRepositories(basePackages = "com.sumerge.repository")
@EntityScan(basePackages = {"com.sumerge.springTask3", "com.sumerge.classes"})
public class AppConfig {

    @Bean
    public CourseMapper courseMapper() {
        return Mappers.getMapper(CourseMapper.class);
    }

    @Bean
    public AuthorMapper authorMapper() {
        return Mappers.getMapper(AuthorMapper.class);
    }
}