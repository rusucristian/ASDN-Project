package rusu.cristian1.ControllerPath;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import rusu.cristian1.domain.Cinema;
import rusu.cristian1.domain.Films.*;
import rusu.cristian1.service.CinemaService;
import rusu.cristian1.service.FilmService;

import java.io.File;
import java.io.IOException;
import java.util.*;

@org.springframework.stereotype.Controller
public class AddController {

    @Autowired
    private FilmService filmService;

    @Autowired
    private CinemaService cinemaService;

    @Value("${upload.path}")
    private String uploadPath;

    @GetMapping("/admin/add/film")
    public String addFilm(Model model) {
        model.addAttribute("quality", Quality.values());
        model.addAttribute("genre", Genre.values());
        model.addAttribute("tags", Tags.values());
        model.addAttribute("cinema", cinemaService.findAll());
        return "/dashboard/add/film";
    }

    @GetMapping("/admin/add/cinema")
    public String addCinema() {
        return "/dashboard/add/cinema";
    }

    @PostMapping("/admin/add/cinema")
    public String addCinema(@RequestParam String name,
                            @RequestParam String address,
                            @RequestParam Long nrPhone,
                            @RequestParam String email,
                            @RequestParam Long nrHall,
                            @RequestParam String workingTime,
                            @RequestParam String description,
                            @RequestParam("photoName") MultipartFile photoName) throws IOException {
        Cinema cinema = new Cinema(name, address, nrPhone, email, nrHall, workingTime, description);

        if (photoName != null && !photoName.getOriginalFilename().isEmpty()) {
            File uploadDir = new File(uploadPath);

            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }

            photoName.transferTo(new File(uploadPath + "/" + photoName.getOriginalFilename()));

            cinema.setPhotoName(photoName.getOriginalFilename());
        }

        cinemaService.save(cinema);
        return "redirect:/admin/edit/cinema";
    }

    @PostMapping("/admin/add/film")
    public String addNewFilm(@RequestParam String name,
                             @RequestParam String country,
                             @RequestParam Double rating,
                             @RequestParam Integer releaseDate,
                             @RequestParam Integer runningTime,
                             @RequestParam String description,
                             @RequestParam MultipartFile photoName,
                             @RequestParam String videoURL,
                             @RequestParam String publicationDate,
                             @RequestParam String endDate,
                             @RequestParam String quality,
                             @RequestParam List<String> genre,
                             @RequestParam String tags,
                             @RequestParam List<String> cinema,
                             @RequestParam Integer price) throws IOException {

        Film film = new Film(name, country, releaseDate, description, videoURL, publicationDate, endDate, price);

        if (rating != null) {
            film.setRating(rating);
        } else {
            film.setRating(0);
        }

        if (runningTime != null)
            film.setRunningTime(runningTime);
        else film.setRunningTime(0);

        if (photoName != null && !photoName.getOriginalFilename().isEmpty()) {
            File uploadDir = new File(uploadPath);

            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }

            photoName.transferTo(new File(uploadPath + "/" + photoName.getOriginalFilename()));

            film.setPhotoName(photoName.getOriginalFilename());
        }

        Set<Quality> setQuality = new HashSet<>();
        setQuality.add(Quality.valueOf(quality));
        film.setQuality(setQuality);

        Set<Genre> setGenre = new HashSet<>();
        for (String n : genre) {
            setGenre.add(Genre.valueOf(n));
        }
        film.setGenre(setGenre);

        Set<Tags> setTags = new HashSet<>();
        setTags.add(Tags.valueOf(tags));
        film.setTag(setTags);

        Set<Cinema> cinemaSet = new HashSet<>();
        for (String s : cinema) {
            cinemaSet.add(cinemaService.getByName(s));
        }
        film.setCinema(cinemaSet);

        filmService.save(film);

        return "redirect:/admin/edit/film";
    }
}
