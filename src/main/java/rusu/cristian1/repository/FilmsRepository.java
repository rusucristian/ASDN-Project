package rusu.cristian1.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import rusu.cristian1.domain.Films.Film;
import rusu.cristian1.domain.Films.Tags;

import java.util.List;

public interface FilmsRepository extends JpaRepository<Film, Long>{

    Film findByName(String name);

    Page<Film> findByTagOrderByRatingDesc(Tags tag, Pageable pageable);

    Page<Film> findByTagNotLikeOrderByName(Tags tag, Pageable pageable);

    List<Film> findAllByOrderById();

    //List<Film> findAllByCinema_Id(Pageable pageable);
}
