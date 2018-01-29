package cn.justful.finance.controller;

import cn.justful.finance.dao.FamilyRepository;
import cn.justful.finance.dto.RestResult;
import cn.justful.finance.entity.Family;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by justful on 2018/1/28.
 * 家庭Controller
 */
@RestController
@RequestMapping("/families")
public class FamilyController {
    private final FamilyRepository familyRepository;

    @Autowired
    public FamilyController(FamilyRepository familyRepository) {
        this.familyRepository = familyRepository;
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    RestResult<List<Family>> list() {
        return RestResult.success(familyRepository.findAll());
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    RestResult<Family> detail(@PathVariable int id) {
        return RestResult.success(familyRepository.findOne(id));
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    RestResult<String> insert(@Param("name") String name) {
        Family family = new Family(name);
        familyRepository.save(family);
        return RestResult.success();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    RestResult<String> update(@PathVariable int id, Family family) {
        family.setId(id);
        familyRepository.save(family);
        return RestResult.success();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    RestResult<String> delete(@PathVariable int id) {
        familyRepository.delete(id);
        return RestResult.success();
    }
}
