package br.newtonpaiva.Book.domain.entity;

import br.newtonpaiva.Book.api.BookDto;
import br.newtonpaiva.Book.domain.enums.StatusEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.With;
import org.springframework.data.annotation.Id;

import java.time.LocalDate;

@Data
@With // builder pattern
@AllArgsConstructor
@NoArgsConstructor
public class Book {
    @Id
    private String id;

    private String title;
    private String author;
    private String publisher;
    private LocalDate publishAt;
    private StatusEnum status;

    public Book(BookDto bookDto) {
        this.setTitle(bookDto.getTitle());
        this.setAuthor(bookDto.getAuthor());
        this.setPublisher(bookDto.getPublisher());
        this.setPublishAt(bookDto.getPublishAt());
        this.setStatus(bookDto.getStatus());
    }
}
