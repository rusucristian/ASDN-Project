package rusu.cristian1.ControllerPath;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import rusu.cristian1.domain.Cinema;
import rusu.cristian1.domain.Films.Film;
import rusu.cristian1.domain.Films.Genre;
import rusu.cristian1.domain.Films.Quality;
import rusu.cristian1.domain.Films.Tags;
import rusu.cristian1.service.CinemaService;
import rusu.cristian1.service.FilmService;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@org.springframework.stereotype.Controller
public class EditController {

    @Autowired
    private FilmService filmService;

    @Autowired
    private CinemaService cinemaService;

    @Value("${upload.path}")
    private String uploadPath;

    @GetMapping("/admin/edit/film")
    public String editFilm(Model model) {
        model.addAttribute("films", filmService.findAllByOrderById());
        return "/dashboard/edit/filmTable";
    }

    @GetMapping("/admin/edit/cinema")
    public String editCinema(Model model) {
        model.addAttribute("cinema", cinemaService.findAll());
        return "/dashboard/edit/cinemaTable";
    }

    @GetMapping("/admin/edit/film/{filmID}")
    public String editFilmById(Model model, @PathVariable Long filmID) {
        model.addAttribute("quality", Quality.values());
        model.addAttribute("genre", Genre.values());
        model.addAttribute("tags", Tags.values());
        model.addAttribute("cinemas", cinemaService.findAll());
        model.addAttribute("film", filmService.getById(filmID));
        return "/dashboard/edit/film";
    }

    @GetMapping("/admin/edit/film/delete/{filmID}")
    public String deleteFilmById(@PathVariable("filmID") Long filmID) {
        Film film = filmService.getById(filmID);
        filmService.delete(film);

/*        Path path = Paths.get(uploadPath + "\\" + film.getPhotoName());

        try {
            Files.deleteIfExists(path);
        } catch (IOException e) {
            e.printStackTrace();
        }*/

        return "redirect:/admin/edit/film";
    }

    @GetMapping("/admin/edit/cinema/{cinemaID}")
    public String editCinemaById(Model model, @PathVariable Long cinemaID) {
        model.addAttribute("cinema", cinemaService.getById(cinemaID));
        return "/dashboard/edit/cinema";
    }

    @PostMapping("/admin/edit/cinema/{cinemaID}")
    public String editCinemaByIdPost(@PathVariable Long cinemaID,
                                     @RequestParam String name,
                                     @RequestParam String address,
                                     @RequestParam Long nrPhone,
                                     @RequestParam String email,
                                     @RequestParam Long nrHall,
                                     @RequestParam String workingTime,
                                     @RequestParam String description,
                                     @RequestParam("photoName") MultipartFile photoName) throws IOException {
        Cinema cinema = cinemaService.getById(cinemaID);
        if (!cinema.getName().equals(name)) {
            cinema.setName(name);
        }
        if (!cinema.getAddress().equals(address)) {
            cinema.setAddress(address);
        }
        if (!cinema.getNrPhone().equals(nrPhone)) {
            cinema.setNrPhone(nrPhone);
        }
        if (!cinema.getEmail().equals(email)) {
            cinema.setEmail(email);
        }
        if (!cinema.getNrHall().equals(nrHall)) {
            cinema.setNrHall(nrHall);
        }
        if (!cinema.getWorkingTime().equals(workingTime)) {
            cinema.setWorkingTime(workingTime);
        }
        if (!cinema.getDescription().equals(description)) {
            cinema.setDescription(description);
        }
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

    @GetMapping("/admin/edit/cinema/delete/{cinemaID}")
    public String deleteCinemaById(@PathVariable("cinemaID") Long cinemaID) {
        Cinema cinema = cinemaService.getById(cinemaID);
        for (Film film : cinema.getFilm()){
            film.getCinema().remove(cinema);
        }
        cinemaService.delete(cinema);
        /*Path path = Paths.get(uploadPath + "\\" + cinema.getPhotoName());

        try {
            Files.deleteIfExists(path);
        } catch (IOException e) {
            e.printStackTrace();
        }*///dupa teste de decomentat

        return "redirect:/admin/edit/cinema";
    }

    @PostMapping("/admin/edit/film/{filmID}")
    public String addNewFilm(@PathVariable("filmID") Long filmID,
                             @RequestParam String name,
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
                             @RequestParam String tag,
                             @RequestParam List<String> cinema,
                             @RequestParam Integer price) throws IOException {

        Film film = filmService.getById(filmID);

        if (!film.getName().equals(name)){
            film.setName(name);
        }

        if (!film.getCountry().equals(country)){
            film.setCountry(country);
        }

        if (film.getRating()!=rating){
            film.setRating(rating);
        }

        if (film.getReleaseDate()!=releaseDate){
            film.setReleaseDate(releaseDate);
        }

        if (film.getRunningTime()!=runningTime){
            film.setRunningTime(runningTime);
        }

        if (!film.getDescription().equals(description)){
            film.setDescription(description);
        }

        if (photoName != null && !photoName.getOriginalFilename().isEmpty()) {
            File uploadDir = new File(uploadPath);

            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }

            photoName.transferTo(new File(uploadPath + "/" + photoName.getOriginalFilename()));

            film.setPhotoName(photoName.getOriginalFilename());
        }

        if (!film.getVideoURL().equals(videoURL)){
            film.setVideoURL(videoURL);
        }

        if (!film.getPublicationDate().equals(publicationDate)){
            film.setPublicationDate(publicationDate);
        }

        if (!film.getEndDate().equals(endDate)){
            film.setEndDate(endDate);
        }

        if (!film.getQuality().contains(Quality.valueOf(quality))){
            Set<Quality> setQuality = new HashSet<>();
            setQuality.add(Quality.valueOf(quality));
            film.setQuality(setQuality);
        }

        Set<Genre> setGenre = new HashSet<>();
        for (String n : genre) {
            setGenre.add(Genre.valueOf(n));
        }
        if (!film.getGenre().equals(setGenre)) {
            film.setGenre(setGenre);
        }

        Set<Tags> setTags = new HashSet<>();
        setTags.add(Tags.valueOf(tag));
        if (!film.getTag().equals(setTags)) {
            film.setTag(setTags);
        }

        Set<Cinema> cinemaSet = new HashSet<>();
        for (String s : cinema) {
            cinemaSet.add(cinemaService.getByName(s));
        }
        if (!film.getCinema().equals(cinemaSet)) {
            film.setCinema(cinemaSet);
        }

        if (film.getPrice()!=price){
            film.setPrice(price);
        }

        filmService.save(film);

        return "redirect:/admin/edit/film";
    }
}
