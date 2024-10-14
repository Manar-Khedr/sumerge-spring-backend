package com.sumerge.configuration;

import com.sumerge.implementation.ChangedCourseRecommenderImpl2;
import com.sumerge.implementation.CourseRecommenderImpl1;
import com.sumerge.repository.CourseRepository;
import com.sumerge.repository.CourseRepositoryImpl;
import com.sumerge.service.CourseService;
import com.sumerge.springTask3.implementation.CourseRecommender;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.*;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration
@ComponentScan(basePackages = "com.sumerge")
@Import(DataSourceConfig.class)
public class AppConfig {

    @Bean
    public CourseService courseService(
            @Qualifier("courseRecommenderImpl2") CourseRecommender courseRecommender,
            CourseRepository courseRepository) {
        return new CourseService(courseRecommender, courseRepository);
    }

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

    @Bean
    public CourseRepository courseRepository(JdbcTemplate jdbcTemplate) {
        return new CourseRepositoryImpl(jdbcTemplate);
    }
}