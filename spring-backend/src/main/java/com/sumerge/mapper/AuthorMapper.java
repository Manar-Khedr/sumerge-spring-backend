package com.sumerge.mapper;

import com.sumerge.classes.Author;
import com.sumerge.dto.AuthorDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AuthorMapper {

    AuthorMapper INSTANCE = Mappers.getMapper(AuthorMapper.class);

    AuthorDTO mapToAuthorDTO(Author author);
    Author mapToAuthor(AuthorDTO authorDTO);
}
