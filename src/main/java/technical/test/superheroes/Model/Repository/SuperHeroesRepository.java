package technical.test.superheroes.Model.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import technical.test.superheroes.Model.Entity.SuperHeroe;

import java.util.List;

@Repository
public interface SuperHeroesRepository extends JpaRepository<SuperHeroe, Integer> {
    @Query(value = "FROM SuperHeroe sh " +
            "WHERE (sh.name LIKE %:name%)")
    List<SuperHeroe> findByName(String name);
}
