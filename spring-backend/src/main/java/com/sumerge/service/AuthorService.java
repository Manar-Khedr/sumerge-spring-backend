package com.sumerge.service;


import com.sumerge.dto.AuthorDTO;
import com.sumerge.exception.ResourceNotFoundException;
import com.sumerge.mapper.AuthorMapper;
import com.sumerge.repository.AuthorRepository;
import com.sumerge.springTask3.classes.Author;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import javax.validation.ValidationException;

@Service
public class AuthorService {

    private final AuthorRepository authorRepository;
    private final AuthorMapper authorMapper;

    // constructor
    @Autowired
    public AuthorService(AuthorRepository authorRepository, AuthorMapper authorMapper){
        this.authorRepository = authorRepository;
        this.authorMapper = authorMapper;
    }

    public AuthorDTO addAuthor(AuthorDTO authorDTO) throws ValidationException {
        String authorEmail = authorDTO.getAuthorEmail();
        if (authorRepository.findByAuthorEmail(authorEmail).isPresent()) {
            throw new ValidationException("Author with email " + authorEmail + " already exists.");
        }
        Author author = authorMapper.mapToAuthor(authorDTO);
        Author savedAuthor = authorRepository.save(author);
        return authorMapper.mapToAuthorDTO(savedAuthor);
    }

    public AuthorDTO viewAuthorByEmail(String authorEmail) throws ResourceNotFoundException{
        Author author = authorRepository.findByAuthorEmail(authorEmail)
                .orElseThrow(() -> new ResourceNotFoundException("Author not found with email: " + authorEmail));
        return authorMapper.mapToAuthorDTO(author);
    }

    // changes here
    public AuthorDTO updateAuthor(AuthorDTO authorDTO) throws ResourceNotFoundException {
        String authorEmail = authorDTO.getAuthorEmail();
        Author existingAuthor = authorRepository.findByAuthorEmail(authorEmail)
                .orElseThrow(() -> new ResourceNotFoundException("Author not found with email: " + authorEmail));;

        // Update only the necessary fields
        existingAuthor.setAuthorName(authorDTO.getAuthorName());
        existingAuthor.setAuthorBirthDate(authorDTO.getAuthorBirthDate());
        // Update other fields as needed

        Author updatedAuthor = authorRepository.save(existingAuthor);
        return authorMapper.mapToAuthorDTO(updatedAuthor);
    }

    public void deleteAuthorByEmail(String authorEmail) throws ResourceNotFoundException {
        Author author = authorRepository.findByAuthorEmail(authorEmail)
                .orElseThrow(() -> new ResourceNotFoundException("Author not found with email: " + authorEmail));
        authorRepository.delete(author);
    }

    public Page<AuthorDTO> viewAllAuthors(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return authorRepository.findAll(pageable)
                .map(authorMapper::mapToAuthorDTO);
    }
}