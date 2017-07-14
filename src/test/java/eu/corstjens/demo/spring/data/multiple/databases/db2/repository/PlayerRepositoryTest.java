package eu.corstjens.demo.spring.data.multiple.databases.db2.repository;

import eu.corstjens.demo.spring.data.multiple.databases.SpringBootMultipleDatabasesApplicationTests;
import eu.corstjens.demo.spring.data.multiple.databases.db2.model.Player;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by koencorstjens on 13/07/17.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringBootMultipleDatabasesApplicationTests.class)
@Transactional(transactionManager = "db2TransactionManager")
public class PlayerRepositoryTest {

    @Autowired
    private PlayerRepository playerRepository;

    @Test
    public void test() {
        playerRepository.save(new Player());
        Assert.assertEquals(1l, playerRepository.findOne(1l).getId().longValue());
    }

    @Test
    public void correctDB(){
        Assert.assertEquals("DB2",playerRepository.returnDBName());
    }
}
