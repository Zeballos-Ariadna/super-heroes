package technical.test.superheroes.Model.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import technical.test.superheroes.Model.Entity.SuperHeroe;

@Repository
public interface SuperHeroesRepository extends JpaRepository<SuperHeroe, Integer> {
}
