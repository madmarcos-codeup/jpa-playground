package repositories;

import models.MyMessage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MyMessageRepository extends JpaRepository<MyMessage, Integer> {
}