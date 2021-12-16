package rusu.cristian1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import rusu.cristian1.domain.Cinema;
import rusu.cristian1.repository.CinemaRepository;

import java.util.List;

@Service
public class CinemaService {

    @Autowired
    private CinemaRepository cinemaRepository;

    public List<Cinema> findAll(Pageable pageable12){return cinemaRepository.findAll();}

    public List<Cinema> findAll() { return cinemaRepository.findAll();}

    public Cinema getById(Long id){return cinemaRepository.getById(id);}

    public Cinema save(Cinema cinema){return cinemaRepository.save(cinema);}

    public void deleteById(Long id){ cinemaRepository.deleteById(id);}

    public void delete(Cinema cinema){ cinemaRepository.delete(cinema);}

    public Cinema findByName(String name) {return cinemaRepository.findByName(name);}

    public Cinema getByName(String name) {return cinemaRepository.getByName(name);}
}
