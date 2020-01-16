package pl.rafalmiskiewicz.ADOZL.hours;

import java.util.List;


public interface HourService {

    List<Hour> findAll();

    Hour findHourById(int id);

    List<Hour> findAllByUserId(int id);

    void save(Hour hour);

    // void savehour(Hour hour);
    
}
