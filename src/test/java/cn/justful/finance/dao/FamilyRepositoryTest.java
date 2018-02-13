package cn.justful.finance.dao;

import cn.justful.finance.entity.Family;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FamilyRepositoryTest {
    @Autowired
    private FamilyRepository familyRepository;

    @Test
    public void test() throws Exception {
        familyRepository.save(new Family("虫虫和稻稻"));
    }
}