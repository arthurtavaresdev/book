package br.newtonpaiva.Book.domain.repository;

import br.newtonpaiva.Book.domain.entity.Book;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BookRepository extends MongoRepository<Book, String> { }
