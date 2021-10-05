package br.newtonpaiva.Book.api;

import br.newtonpaiva.Book.domain.entity.Book;
import br.newtonpaiva.Book.domain.enums.StatusEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookDto {
    private String id;
    private String title;
    private String author;
    private String publisher;
    private LocalDate publishAt;
    private StatusEnum status;

    public BookDto(Book book){
        this.setId(book.getId());
        this.setTitle(book.getTitle());
        this.setAuthor(book.getAuthor());
        this.setPublisher(book.getPublisher());
        this.setPublishAt(book.getPublishAt());
        this.setStatus(book.getStatus());
    }
}
