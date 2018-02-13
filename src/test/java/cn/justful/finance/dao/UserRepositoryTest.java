package cn.justful.finance.dao;

import cn.justful.finance.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private FamilyRepository familyRepository;

    @Test
    public void test() throws Exception {
        User user = new User();
        user.setFamily(familyRepository.findOne(1));
        user.setName("张三");
        user.setPassword("abc");
        user.setRole(0);
        userRepository.save(user);
    }
}