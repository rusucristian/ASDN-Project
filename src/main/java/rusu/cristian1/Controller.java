package rusu.cristian1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import rusu.cristian1.domain.Cinema;
import rusu.cristian1.domain.Films.Film;
import rusu.cristian1.domain.Films.Tags;
import rusu.cristian1.service.CinemaService;
import rusu.cristian1.service.FilmService;
import java.util.List;

@org.springframework.stereotype.Controller
public class Controller {

    @Autowired
    private FilmService filmService;

    @Autowired
    private CinemaService cinemaService;

    Pageable pageable = PageRequest.of(0, 6);
    Pageable pageable12 = PageRequest.of(0, 12);

    @GetMapping("/")
    public String index(Model model){
        Page<Film> filmsNew = filmService.findByTagOrderByRatingDesc(Tags.NEW, pageable);
        Page<Film> filmes = filmService.findByTagNotLikeOrderByName(Tags.EXPECTED, pageable);
        Page<Film> filmsExpected = filmService.findByTagOrderByRatingDesc(Tags.EXPECTED, pageable);
        List<Cinema> cinemas = cinemaService.findAll(pageable12);
        List<Film> filmById = filmService.findAll(pageable12);

        model.addAttribute("filmList", filmsNew);
        model.addAttribute("films", filmes);
        model.addAttribute("filmExpected", filmsExpected);
        model.addAttribute("cinema", cinemas);
        model.addAttribute("filmById" ,filmById);

        return "index";
    }

    @GetMapping("/faq")
    public String pricing(){
        return "/site/faq";
    }

    @GetMapping("/details/{id}")
    public String details(Model model, @PathVariable Long id){
        model.addAttribute("getFilm", filmService.getById(id));
        return "/site/details";
    }

    @GetMapping("/cinemalist")
    public String cinemalist(Model model){
        List<Cinema> cinemaList = cinemaService.findAll();
        model.addAttribute("cinemaList", cinemaList);
        return "/site/cinemalist";
    }

    @GetMapping("/films")
    public String films(Model model){
        List<Film> film = filmService.findAll();
        model.addAttribute("film", film);
        return "/site/films";
    }

    @GetMapping("/about")
    public String catalog(){
        return "/site/about";
    }

    @GetMapping("/admin")
    public String admin(){return "/dashboard/admin";}
}
