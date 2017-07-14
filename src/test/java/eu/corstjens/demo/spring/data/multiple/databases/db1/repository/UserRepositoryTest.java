package eu.corstjens.demo.spring.data.multiple.databases.db1.repository;

import eu.corstjens.demo.spring.data.multiple.databases.SpringBootMultipleDatabasesApplication;
import eu.corstjens.demo.spring.data.multiple.databases.SpringBootMultipleDatabasesApplicationTests;
import eu.corstjens.demo.spring.data.multiple.databases.db1.model.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * Created by koencorstjens on 13/07/17.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringBootMultipleDatabasesApplicationTests.class)
public class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;

    @Test
    public void test() {
        userRepository.save(new User());
        List<User> all = userRepository.findAll();
        Assert.assertEquals(1L, all.get(0).getId().longValue());
    }

    @Test
    public void correctDB(){
        Assert.assertEquals("DB1",userRepository.returnDBName());
    }
}
