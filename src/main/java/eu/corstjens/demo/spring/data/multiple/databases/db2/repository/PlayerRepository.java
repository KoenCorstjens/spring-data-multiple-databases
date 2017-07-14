package eu.corstjens.demo.spring.data.multiple.databases.db2.repository;

import eu.corstjens.demo.spring.data.multiple.databases.db2.model.Player;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by koencorstjens on 13/07/17.
 */
@Repository
public interface PlayerRepository extends CrudRepository<Player, Long> {
    @Query(value = "SELECT DATABASE ( )", nativeQuery = true)
    String returnDBName();
}
