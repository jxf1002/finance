package cn.justful.finance.controller;

import cn.justful.finance.dao.AccountRepository;
import cn.justful.finance.dto.RestResult;
import cn.justful.finance.entity.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/accounts", produces = MediaType.APPLICATION_JSON_VALUE)
public class AccountController {
    private final AccountRepository repository;

    @Autowired
    public AccountController(AccountRepository repository) {
        this.repository = repository;
    }

    @GetMapping()
    RestResult<List<Account>> list() {
        return RestResult.success(repository.findAll());
    }

    @GetMapping("/{id}")
    RestResult<Account> detail(@PathVariable int id) {
        return RestResult.success(repository.findOne(id));
    }

    @PostMapping()
    RestResult<String> insert(@RequestBody Account account) {
        repository.save(account);
        return RestResult.success();
    }

    @PutMapping("/{id}")
    RestResult<String> update(@PathVariable int id, @RequestBody Account account) {
        account.setId(id);
        repository.save(account);
        return RestResult.success();
    }

    @PatchMapping("/{id}")
    RestResult<String> patch(@PathVariable int id, @RequestBody Account account) {
        Account originAccount = repository.findOne(id);
        if (account.getType() != null)
            originAccount.setType(account.getType());
        if (account.getName() != null)
            originAccount.setName(account.getName());
        repository.save(originAccount);
        return RestResult.success();
    }

    @DeleteMapping("/{id}")
    RestResult<String> delete(@PathVariable int id) {
        repository.delete(id);
        return RestResult.success();
    }
}
