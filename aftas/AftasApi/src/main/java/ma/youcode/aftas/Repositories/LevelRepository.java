package ma.youcode.aftas.Repositories;

import ma.youcode.aftas.Models.Entities.Level;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LevelRepository extends JpaRepository<Level, Long> {
}
