package com.serverprogramming.bookstorev2;
import com.serverprogramming.bookstorev2.domain.Book;
import com.serverprogramming.bookstorev2.domain.BookRepository;
import com.serverprogramming.bookstorev2.domain.Category;
import com.serverprogramming.bookstorev2.domain.CategoryRepository;
import org.apache.el.parser.AstFalse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class BookRepositoryTest {
    @Autowired
    private BookRepository repository;
    @Test
    public void findByIsbn(){
        List<Book> books = repository.findByIsbn("978-3-16-148410-0");
        assertThat(books).hasSize(1);
        assertThat(books.get(0).getIsbn()).isEqualTo("978-3-16-148410-0");
    }

    @Test
    public void creatBook(){
        Category category = new Category("biography");
        Book book1 = new Book("234-32-3242-65", "My creation", "Nicolas Becerra Lineira", 2022, 0.5,category);
        repository.save(book1);
        assertThat(book1.getIsbn().isEmpty()).isEqualTo(false);
    }

    @Test
    public void deleteNewBook(){
        List<Book> books = repository.findByIsbn("978-3-16-148410-0");
        Book book = books.get(0);
        repository.deleteById(book.getIsbn());
        List<Book> newBook = repository.findByIsbn("978-3-16-148410-0");
        assertThat(newBook).hasSize(0);
    }

}
