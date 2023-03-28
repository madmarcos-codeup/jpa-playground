package docrob.springjpatutorial;

import models.Cat;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import repositories.CatRepository;

import java.sql.SQLException;
import java.util.List;

@SpringBootTest
@Transactional(rollbackFor = { SQLException.class })
@Rollback(false)
class CatTests {

    @Autowired
    private CatRepository catDao;

    @Test
    public void fetchCats() {

    }
}
