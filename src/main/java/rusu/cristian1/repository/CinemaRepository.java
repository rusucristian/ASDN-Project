package rusu.cristian1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rusu.cristian1.domain.Cinema;

public interface CinemaRepository extends JpaRepository<Cinema, Long> {

    Cinema findByName(String name);

    Cinema getByName(String name);
}
