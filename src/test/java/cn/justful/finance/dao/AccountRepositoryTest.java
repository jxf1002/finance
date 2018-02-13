package cn.justful.finance.dao;

import cn.justful.finance.entity.Account;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AccountRepositoryTest {
    @Autowired
    private AccountRepository accountRepository;

    @Test
    public void test() throws Exception {
        Account account = new Account();
        account.setType("资产");
        account.setName("现金");
        accountRepository.save(account);
        account = new Account();
        account.setType("资产");
        account.setName("存款");
        accountRepository.save(account);
        account = new Account();
        account.setType("资产");
        account.setName("理财");
        accountRepository.save(account);
    }
}