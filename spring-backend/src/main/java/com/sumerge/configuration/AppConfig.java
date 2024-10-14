package com.sumerge.configuration;

import com.sumerge.implementation.ChangedCourseRecommenderImpl2;
import com.sumerge.implementation.CourseRecommenderImpl1;
import com.sumerge.service.CourseService;
import com.sumerge.springTask3.implementation.CourseRecommender;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.*;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration
@ComponentScan(basePackages = "com.sumerge.spring")
@Import(DataSourceConfig.class)
public class AppConfig {


    @Bean
    public CourseService courseService(
            @Qualifier("courseRecommenderImpl2") CourseRecommender courseRecommender,
            JdbcTemplate jdbcTemplate) {
        return new CourseService(jdbcTemplate,courseRecommender);
    }

    /////////////////

    @Bean
    @Qualifier("courseRecommenderImpl1")
    public CourseRecommender courseRecommenderImpl1() {
        return new CourseRecommenderImpl1();
    }

    @Bean
    @Qualifier("courseRecommenderImpl2")
    public CourseRecommender courseRecommenderImpl2() {
        return new ChangedCourseRecommenderImpl2();
    }

}