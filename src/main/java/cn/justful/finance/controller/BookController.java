package cn.justful.finance.controller;

import cn.justful.finance.dao.BookRepository;
import cn.justful.finance.dto.RestResult;
import cn.justful.finance.entity.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/books", produces = MediaType.APPLICATION_JSON_VALUE)
public class BookController {
    private final BookRepository repository;

    @Autowired
    public BookController(BookRepository repository) {
        this.repository = repository;
    }

    @GetMapping()
    RestResult<List<Book>> list() {
        return RestResult.success(repository.findAll());
    }

    @GetMapping("/{id}")
    RestResult<Book> detail(@PathVariable int id) {
        return RestResult.success(repository.findOne(id));
    }

    @PostMapping()
    RestResult<String> insert(Book book) {
        repository.save(book);
        return RestResult.success();
    }

    @PutMapping("/{id}")
    RestResult<String> update(@PathVariable int id, Book book) {
        book.setId(id);
        repository.save(book);
        return RestResult.success();
    }

    @PatchMapping("/{id}")
    RestResult<String> patch(@PathVariable int id, Book book) {
        Book originBook = repository.findOne(id);
        if (book.getUser() != null)
            originBook.setUser(book.getUser());
        if (book.getAccount() != null)
            originBook.setAccount(book.getAccount());
        if (book.getAccountDate() != null)
            originBook.setAccountDate(book.getAccountDate());
        if (book.getSummary() != null)
            originBook.setSummary(book.getSummary());
        if (book.getAmount() != null)
            originBook.setAmount(book.getAmount());
        repository.save(originBook);
        return RestResult.success();
    }

    @DeleteMapping("/{id}")
    RestResult<String> delete(@PathVariable int id) {
        repository.delete(id);
        return RestResult.success();
    }
}
