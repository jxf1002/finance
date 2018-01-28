package cn.justful.finance.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by justful on 2018/1/28.
 * 家庭Controller
 */
@RestController
public class FamilyController {
    @RequestMapping(value = "/families", method = RequestMethod.GET)
    String list() {
        return "Hello World!11111";
    }
}
