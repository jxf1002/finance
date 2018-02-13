package cn.justful.finance.dao;

import cn.justful.finance.entity.Book;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BookRepositoryTest {
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AccountRepository accountRepository;

    @Test
    public void test() throws Exception {
        Book book = new Book();
        book.setUser(userRepository.findOne(1));
        book.setAccount(accountRepository.findOne(1));
        book.setAccountDate(new Date());
        book.setSummary("工行卡存款");
        book.setAmount(5001.23);
        bookRepository.save(book);
    }
}