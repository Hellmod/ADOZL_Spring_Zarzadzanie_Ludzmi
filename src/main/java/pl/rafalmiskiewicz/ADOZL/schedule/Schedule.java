package pl.rafalmiskiewicz.ADOZL.schedule;

import org.springframework.beans.factory.annotation.Autowired;
import pl.rafalmiskiewicz.ADOZL.places.Places;
import pl.rafalmiskiewicz.ADOZL.places.PlacesService;
import pl.rafalmiskiewicz.ADOZL.user.User;

import javax.persistence.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


@Entity
@Table(name = "schedule")
public class Schedule {
    @Transient
    @Autowired
    private PlacesService placesService;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_schedule")
    private int id_schedule;

    @Column(name = "id_user")
    private int id_user;

    @Column(name = "id_role")
    private int id_role;

    @Column(name = "id_places")
    private int id_places;

    @Column(name = "hour_from")
    private Date hour_from;

    @Column(name = "hour_to")
    private Date hour_to;

    @Transient
    private User user;

    @Transient
    private Places places;

    @Transient
    private String hour_from_string;

    @Transient
    private String hour_to_string;


    public int getId_schedule() {
        return id_schedule;
    }

    public void setId_schedule(int id_schedules) {
        this.id_schedule = id_schedules;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) { this.id_user = id_user; }

    public Date getHour_from() {
        return hour_from;
    }

    public void setHour_from(Date hour_from) {
        this.hour_from = hour_from;
    }

    public Date getHour_to() {
        return hour_to;
    }

    public void setHour_to(Date hour_to) {
        this.hour_to = hour_to;
    }

    public String getHour_from_string() {
        return hour_from_string;
    }

    public void setHour_from_string(String hour_from_string) {
        this.hour_from_string = hour_from_string;
    }

    public String getHour_to_string() {
        return hour_to_string;
    }

    public void setHour_to_string(String hour_to_string) {
        this.hour_to_string = hour_to_string;
    }

    public PlacesService getPlacesService() {
        return placesService;
    }

    public void setPlacesService(PlacesService placesService) {
        this.placesService = placesService;
    }

    public int getId_role() {
        return id_role;
    }

    public void setId_role(int id_role) {
        this.id_role = id_role;
    }

    public int getId_places() {
        return id_places;
    }

    public void setId_places(int id_places) {
        this.id_places = id_places;
    }

    public Places getPlaces() {
        return places;
    }

    public void setPlaces(Places places) {
        this.places = places;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void stringToDate() throws ParseException {
        setHour_to(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(hour_to_string));
        setHour_from(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(hour_from_string));
    }

    public void dateToString() throws ParseException {
        setHour_from_string(getHour_from().toString());
        setHour_to_string(getHour_to().toString());
    }
}
