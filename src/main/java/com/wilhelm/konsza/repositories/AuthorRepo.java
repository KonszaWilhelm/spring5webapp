package com.wilhelm.konsza.repositories;

import com.wilhelm.konsza.entities.Author;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepo extends CrudRepository<Author, Long> {

}
