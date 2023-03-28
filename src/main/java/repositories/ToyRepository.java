package repositories;

import models.Cat;
import models.Toy;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ToyRepository extends JpaRepository<Toy, Long> {

}
