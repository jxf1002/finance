package cn.justful.finance.controller;

import cn.justful.finance.dao.UserRepository;
import cn.justful.finance.dto.RestResult;
import cn.justful.finance.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {
    private final UserRepository repository;

    @Autowired
    public UserController(UserRepository repository) {
        this.repository = repository;
    }

    @GetMapping()
    RestResult<List<User>> list() {
        return RestResult.success(repository.findAll());
    }

    @GetMapping("/{id}")
    RestResult<User> detail(@PathVariable int id) {
        return RestResult.success(repository.findOne(id));
    }

    @PostMapping()
    RestResult<String> insert(@RequestBody User user) {
        repository.save(user);
        return RestResult.success();
    }

    @PutMapping("/{id}")
    RestResult<String> update(@PathVariable int id, @RequestBody User user) {
        user.setId(id);
        repository.save(user);
        return RestResult.success();
    }

    @PatchMapping("/{id}")
    RestResult<String> patch(@PathVariable int id, @RequestBody User user) {
        User originUser = repository.findOne(id);
        if (user.getName() != null)
            originUser.setName(user.getName());
        if (user.getPassword() != null)
            originUser.setPassword(user.getPassword());
        if (user.getFamily() != null)
            originUser.setFamily(user.getFamily());
        if (user.getRole() != null)
            originUser.setRole(user.getRole());
        repository.save(originUser);
        return RestResult.success();
    }

    @DeleteMapping("/{id}")
    RestResult<String> delete(@PathVariable int id) {
        repository.delete(id);
        return RestResult.success();
    }
}
