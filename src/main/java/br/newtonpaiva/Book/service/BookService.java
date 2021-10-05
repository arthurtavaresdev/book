package br.newtonpaiva.Book.service;

import br.newtonpaiva.Book.domain.entity.Book;
import br.newtonpaiva.Book.domain.repository.BookRepository;
import br.newtonpaiva.Book.exception.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository){
        this.bookRepository = bookRepository;
    }

    public Book create(Book book){
        bookRepository.save(book);
        return book;
    }

    public Book update(String id, Book book) {
        var existing = get(id);

        existing.setId(book.getId());
        existing.setTitle(book.getTitle());
        existing.setAuthor(book.getAuthor());
        existing.setPublisher(book.getPublisher());
        existing.setPublishAt(book.getPublishAt());
        existing.setStatus(book.getStatus());

        bookRepository.save(existing);

        return existing;
    }

    public Book get(String id) {
        var book = bookRepository.findById(id);

        if (book.isEmpty()) {
            throw new NotFoundException("Book with ID " + id + " not found");
        }

        return book.get();
    }

    public List<Book> getAll() {
        return bookRepository.findAll();
    }

    public void delete(String id) {
        get(id);
        bookRepository.deleteById(id);
    }
}
