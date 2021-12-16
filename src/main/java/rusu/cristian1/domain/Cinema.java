package rusu.cristian1.domain;

import rusu.cristian1.domain.Films.Film;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Cinema {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String address;
    private Long nrPhone;
    private String email;
    private Long nrHall;
    private String workingTime;
    @Column(length = 10000)
    private String description;
    private String photoName;

    @ManyToMany(mappedBy = "cinema")
    private Set<Film> film;

    public Cinema() {
    }

    public Cinema(String name, String address, Long nrPhone, String email, Long nrHall, String workingTime, String description) {
        this.name = name;
        this.address = address;
        this.nrPhone = nrPhone;
        this.email = email;
        this.nrHall = nrHall;
        this.workingTime = workingTime;
        this.description = description;
    }

    public Cinema(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Long getNrPhone() {
        return nrPhone;
    }

    public void setNrPhone(Long nrPhone) {
        this.nrPhone = nrPhone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getNrHall() {
        return nrHall;
    }

    public void setNrHall(Long nrHall) {
        this.nrHall = nrHall;
    }

    public String getWorkingTime() {
        return workingTime;
    }

    public void setWorkingTime(String workingTime) {
        this.workingTime = workingTime;
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

    public Set<Film> getFilm() {
        return film;
    }

    public void setFilm(Set<Film> film) {
        this.film = film;
    }
}
