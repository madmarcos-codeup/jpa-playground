package docrob.springjpatutorial;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import repositories.*;

import java.sql.SQLException;

@SpringBootTest
@Transactional(rollbackFor = { SQLException.class })
@Rollback(false)
class CustomerTests {

    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private CustomerPurchaseRepository purchaseRepository;

    @Test
    void fetchAuthors() {
        assert true;
    }

}