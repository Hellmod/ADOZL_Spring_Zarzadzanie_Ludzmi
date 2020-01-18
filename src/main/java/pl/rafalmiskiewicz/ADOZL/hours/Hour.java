package pl.rafalmiskiewicz.ADOZL.hours;

import javax.persistence.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


@Entity
@Table(name = "hours")
public class Hour {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_hours")
    private int id_hours;

    @Column(name = "id_user")
    private int id_user;

    @Column(name = "hour_from")
    private Date hour_from;

    @Column(name = "hour_to")
    private Date hour_to;

    @Transient
    private String hour_from_string;

    @Transient
    private String hour_to_string;


    public int getId_hours() {
        return id_hours;
    }

    public void setId_hours(int id_hours) {
        this.id_hours = id_hours;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public Date getHour_from() {
        return hour_from;
    }

    public void setHour_from(Date hour_from) {
        this.hour_from = hour_from;
    }

    public void setHour_from(String hour_from) {
        this.hour_from = new Date();
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

    public void stringToDate() throws ParseException {
        setHour_to(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(hour_to_string));
        setHour_from(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(hour_from_string));
    }

    public void dateToString() throws ParseException {
        setHour_from_string(getHour_from().toString());
        setHour_to_string(getHour_to().toString());
    }
}
