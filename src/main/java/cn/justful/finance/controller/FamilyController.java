package cn.justful.finance.controller;

import cn.justful.finance.dao.FamilyRepository;
import cn.justful.finance.dto.RestResult;
import cn.justful.finance.entity.Family;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/families", produces = MediaType.APPLICATION_JSON_VALUE)
public class FamilyController {
    private final FamilyRepository repository;

    @Autowired
    public FamilyController(FamilyRepository repository) {
        this.repository = repository;
    }

    @GetMapping()
    RestResult<List<Family>> list() {
        return RestResult.success(repository.findAll());
    }

    @GetMapping("/{id}")
    RestResult<Family> detail(@PathVariable int id) {
        return RestResult.success(repository.findOne(id));
    }

    @PostMapping()
    RestResult<String> insert(@RequestBody Family family) {
        repository.save(family);
        return RestResult.success();
    }

    @PutMapping("/{id}")
    RestResult<String> update(@PathVariable int id, @RequestBody Family family, RestResult<String> success) {
        family.setId(id);
        repository.save(family);
        return success;
    }

    @PatchMapping("/{id}")
    RestResult<String> patch(@PathVariable int id, @RequestBody Family family) {
        Family originFamily = repository.findOne(id);
        if (family.getName() != null)
            originFamily.setName(family.getName());
        repository.save(originFamily);
        return RestResult.success();
    }

    @DeleteMapping("/{id}")
    RestResult<String> delete(@PathVariable int id) {
        repository.delete(id);
        return RestResult.success();
    }
}
