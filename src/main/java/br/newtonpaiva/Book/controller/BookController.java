package br.newtonpaiva.Book.controller;

import br.newtonpaiva.Book.api.BookDto;
import br.newtonpaiva.Book.domain.entity.Book;
import br.newtonpaiva.Book.service.BookService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@AllArgsConstructor
public class BookController {
    private final BookService bookService;

    @GetMapping("/books/{id}")
    public BookDto getById(@PathVariable String id){
        var book = bookService.get(id);
        return new BookDto(book);
    }

    @GetMapping("/books")
    public List<BookDto> getAll(){
        var books = bookService.getAll();
        var bookDtos = new ArrayList<BookDto>();

        for(var book: books){
            bookDtos.add(new BookDto(book));
        }

        return bookDtos;
    }

    @PostMapping("/books")
    public BookDto create(@RequestBody  BookDto bookDto){
        var book = new Book(bookDto);
        book = bookService.create(book);

        return new BookDto(book);
    }

    @PutMapping("/books/{id}")
    public BookDto create(@PathVariable String id, @RequestBody BookDto bookDto){
        var book = new Book(bookDto);
        book = bookService.update(id, book);

        return new BookDto(book);
    }

    @DeleteMapping("/books/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id){
        bookService.delete(id);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }
}
