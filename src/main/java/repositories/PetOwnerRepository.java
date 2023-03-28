package repositories;

import models.Cat;
import models.PetOwner;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PetOwnerRepository extends JpaRepository<PetOwner, Long> {

}
