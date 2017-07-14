package eu.corstjens.demo.spring.data.multiple.databases.db1.repository;

import eu.corstjens.demo.spring.data.multiple.databases.db1.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * Created by koencorstjens on 13/07/17.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query(value = "SELECT database();", nativeQuery = true)
    String returnDBName();


}
