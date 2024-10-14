package com.sumerge.repository;

import com.sumerge.springTask3.classes.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Integer> {

    // custom methods
    Optional<Author> findByAuthorEmail(String email);

}
