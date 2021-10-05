package br.newtonpaiva.Book.integration;

import br.newtonpaiva.Book.controller.BookController;
import br.newtonpaiva.Book.domain.entity.Book;
import br.newtonpaiva.Book.domain.enums.StatusEnum;
import br.newtonpaiva.Book.exception.BadRequestException;
import br.newtonpaiva.Book.exception.NotFoundException;
import br.newtonpaiva.Book.service.BookService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDate;

@WebMvcTest(BookController.class)
public class BookApiIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookService bookService;

    @Autowired
    private ObjectMapper objectMapper;


    @Test
    void test_getById_withInvalidId_shouldReturn404() throws Exception {
        var id = "001";

        Mockito.when(bookService.get(id))
                .thenThrow(new NotFoundException("Not found"));

        mockMvc.perform(MockMvcRequestBuilders.get("/books/" +id))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isNotFound()); // 404

        Mockito.verify(bookService).get(id);
    }

    @Test
    void test_getById_withValidId_shouldReturn200() throws Exception {
        // given
        var id = "615ba18df9206d5abf261004";
        var book = new Book();
        book.setId(id);
        book.setTitle("Eloquent JavaScript, Third Edition");
        book.setAuthor("Marijn Haverbeke");
        book.setPublisher("No Starch Press");
        book.setPublishAt(LocalDate.of(2018,12,4));
        book.setStatus(StatusEnum.available);

        // mock definitions
        Mockito.when(bookService.get(id))
                .thenReturn(book);

        var expectedJson = objectMapper.writeValueAsString(book);

        // test/assert
        mockMvc.perform(MockMvcRequestBuilders.get("/books/" +id))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk()) // 200
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.content().json(expectedJson))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(id));

        // verify
        Mockito.verify(bookService).get(id);
    }

    @Test
    void test_delete_withValidId_shouldReturn204() throws Exception {
        // given
        var id = "615ba18df9206d5abf261004";

        // test/assert
        mockMvc.perform(MockMvcRequestBuilders.delete("/books/" + id))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isNoContent()); // 204

        // verify
        Mockito.verify(bookService).delete(id);
    }

    @Test
    void test_delete_withInvalidId_shouldReturn404() throws Exception {
        // given
        var id = "615ba18df9206d5abf261004";

        Mockito.doThrow(new NotFoundException("Not found")).when(bookService).delete(id);

        // test/assert
        mockMvc.perform(MockMvcRequestBuilders.delete("/books/" + id))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isNotFound()); // 404

        // verify
        Mockito.verify(bookService).delete(id);
    }
}
