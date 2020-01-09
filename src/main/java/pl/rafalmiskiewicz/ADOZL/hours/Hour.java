package pl.rafalmiskiewicz.ADOZL.hours;

import javax.persistence.Entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;


@Entity
@Table(name = "hours")
public class Hour {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_hours")
    private int id_hours;

    @Column(name = "id_user")
    @NotNull
    private String id_user;

    @Column(name = "date")
    @NotNull
    private String date;

    @Column(name = "hour_from")
    @NotNull
    private String hour_from;

    @Column(name = "hour_to")
    @NotNull
    private String hour_to;

    public int getId_hours() {
        return id_hours;
    }
    public String getId_user() {
        return id_user;
    }
    public String getDate() {
        return date;
    }
    public String getHour_from() {
        return hour_from;
    }
    public String getHour_to() {
        return hour_to;
    }
    public void setId_hours(int id_hours) {
        this.id_hours = id_hours;
    }
    public void setId_user(String id_user) {
        this.id_user = id_user;
    }
    public void setDate(String date) {
        this.date = date;
    }
    public void setHour_from(String hour_from) {
        this.hour_from = hour_from;
    }
    public void setHour_to(String hour_to) {
        this.hour_to = hour_to;
    }
}
