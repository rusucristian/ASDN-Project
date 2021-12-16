package rusu.cristian1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import rusu.cristian1.domain.Films.Film;
import rusu.cristian1.domain.Films.Tags;
import rusu.cristian1.repository.FilmsRepository;

import java.util.List;

@Service
public class FilmService {

    @Autowired
    private FilmsRepository filmsRepository;

    public List<Film> findAll(){ return filmsRepository.findAll(); }

    public Film getById(Long id){ return filmsRepository.getById(id);}

    public Page<Film> findByTagOrderByRatingDesc(Tags tag, Pageable pageable){return filmsRepository.findByTagOrderByRatingDesc(tag, pageable);}

    public Page<Film> findByTagNotLikeOrderByName(Tags tag, Pageable pageable){return filmsRepository.findByTagNotLikeOrderByName(tag, pageable);}

    public List<Film> findAll(Pageable pageable){return filmsRepository.findAll();}

    public List<Film> findAllByOrderById(){ return filmsRepository.findAllByOrderById();}

    public Film save(Film film){return filmsRepository.save(film);}

    public void delete(Film film) {filmsRepository.delete(film);}

    //public List<Film> findAllByCinema_Id(Pageable pageable){return filmsRepository.findAllByCinema_Id(pageable);}

}
