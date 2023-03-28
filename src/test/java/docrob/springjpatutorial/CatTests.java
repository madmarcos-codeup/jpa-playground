package docrob.springjpatutorial;

import models.Cat;
import models.PetOwner;
import models.Toy;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import repositories.CatRepository;
import repositories.PetOwnerRepository;
import repositories.ToyRepository;

import java.sql.SQLException;
import java.util.List;

@SpringBootTest
@Transactional(rollbackFor = { SQLException.class })
@Rollback(false)
class CatTests {

    @Autowired
    private CatRepository catDao;

    @Autowired
    private PetOwnerRepository ownerDao;

    @Autowired
    private ToyRepository toyDao;

    @Test
    public void fetchCats() {
        List<Cat> cats = catDao.findAll();
        System.out.println(cats);
    }

    @Test
    public void deleteCat() {
        catDao.deleteById(1L);

    }

    @Test
    public void getLolaACat() {
        // find the owner's record in the db
        PetOwner lola = ownerDao.findById(2L).get();
        Cat scruffy = new Cat();
        scruffy.setGender("Male");
        scruffy.setName("Scruffy");
        scruffy.setAge(1);
        scruffy.setOwner(lola);

        catDao.save(scruffy);
    }

    @Test
    public void giveThatCatSomeToys() {
        Toy toy1 = toyDao.findById(1L).get();
        Toy toy3 = toyDao.findById(3L).get();

        Cat lolasCat = catDao.findById(3L).get();
        lolasCat.getToys().add(toy1);
        lolasCat.getToys().add(toy3);

        catDao.save(lolasCat);
    }
}
