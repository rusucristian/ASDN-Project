package rusu.cristian1.domain.Films;

import rusu.cristian1.domain.Cinema;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "film")
public class Film {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String country;
    private double rating;
    private int releaseDate;
    private int runningTime;
    @Column(length = 10000)
    private String description;
    private String photoName;
    private String videoURL;
    private String publicationDate;
    private String endDate;

    @ElementCollection(targetClass = Quality.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "film_quality", joinColumns = @JoinColumn(name = "film_id"))
    @Enumerated(EnumType.STRING)
    private Set<Quality> quality;

    @ElementCollection(targetClass = Genre.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "film_genre", joinColumns = @JoinColumn(name = "film_id"))
    @Enumerated(EnumType.STRING)
    private Set<Genre> genre;

    @ElementCollection(targetClass = Tags.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "film_tag", joinColumns = @JoinColumn(name = "film_id"))
    @Enumerated(EnumType.STRING)
    private Set<Tags> tag;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
    @JoinTable(name = "film_cinema",
            joinColumns = @JoinColumn(name = "film_id"),
            inverseJoinColumns = @JoinColumn(name = "cinema_id"))                //era manytomany
    private Set<Cinema> cinema;

    private int price;

    public Film() {
    }

    public Film(String name, String country, int releaseDate, String description, String videoURL, String publicationDate, String endDate, int price) {
        this.name = name;
        this.country = country;
        this.releaseDate = releaseDate;
        this.description = description;
        this.videoURL = videoURL;
        this.publicationDate = publicationDate;
        this.endDate = endDate;
        this.price = price;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public int getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(int releaseDate) {
        this.releaseDate = releaseDate;
    }

    public int getRunningTime() {
        return runningTime;
    }

    public void setRunningTime(int runningTime) {
        this.runningTime = runningTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPhotoName() {
        return photoName;
    }

    public void setPhotoName(String photoName) {
        this.photoName = photoName;
    }

    public String getVideoURL() {
        return videoURL;
    }

    public void setVideoURL(String videoURL) {
        this.videoURL = videoURL;
    }

    public Set<Quality> getQuality() {
        return quality;
    }

    public void setQuality(Set<Quality> quality) {
        this.quality = quality;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<Genre> getGenre() {
        return genre;
    }

    public void setGenre(Set<Genre> genre) {
        this.genre = genre;
    }

    public Set<Tags> getTag() {
        return tag;
    }

    public void setTag(Set<Tags> tag) {
        this.tag = tag;
    }

    public String getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(String publicationDate) {
        this.publicationDate = publicationDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public Set<Cinema> getCinema() {
        return cinema;
    }

    public void setCinema(Set<Cinema> cinema) {
        this.cinema = cinema;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
