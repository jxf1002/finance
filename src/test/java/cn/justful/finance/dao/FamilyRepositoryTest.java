package cn.justful.finance.dao;

import cn.justful.finance.entity.Family;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FamilyRepositoryTest {
    @Autowired
    private FamilyRepository familyRepository;

    @Test
    public void test() throws Exception {
        familyRepository.save(new Family("家庭A"));
        familyRepository.save(new Family("家庭B"));
        familyRepository.save(new Family("家庭C"));
        familyRepository.save(new Family("家庭D"));
        assertEquals(4, familyRepository.findAll().size());
        assertEquals("家庭B", familyRepository.findByName("家庭B").getName());
        familyRepository.delete(familyRepository.findByName("家庭C"));
        assertEquals(3, familyRepository.findAll().size());
    }
}