package pl.rafalmiskiewicz.ADOZL.hours;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.rafalmiskiewicz.ADOZL.schedule.Schedule;
import pl.rafalmiskiewicz.ADOZL.user.Role;
import pl.rafalmiskiewicz.ADOZL.user.User;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;


@Service("hourService")
@Transactional
public class HourServiceImpl implements HourService {

    @Qualifier("hourRepository")
    @Autowired
    private HourRepository hourRepository;

    @Override
    public List<Hour> findAll() {
        List<Hour> userList = hourRepository.findAll();
        return userList;
    }

    @Override
    public Hour findHourById(int id) {
        return (Hour) hourRepository.findById(id);
    }

    @Override
    public List<Hour> findHourBySchedule(Schedule schedule) {
        return hourRepository.findAllBySchedule(schedule.getId_role());
    }

    @Override
    public List<Hour> findAllByUserId(int id) {
        return hourRepository.findAllByUserId(id);
    }

    @Override
    public void saveHour(Hour hour) {

        hourRepository.save(hour);
    }

    @Override
    public void saveHourNew(Hour hour) {

        hourRepository.insertHour(hour.getId_user(),hour.getHour_from(),hour.getHour_to());
    }

    @Override
    public void insertHourString(Hour hour) {
        hourRepository.insertHourString(hour.getId_user(),hour.getHour_from_string(),hour.getHour_to_string());
    }

    @Override
    public void updateHour(Hour hour) {
        hourRepository.updateHour(hour.getId_hours(),hour.getHour_from(),hour.getHour_to());
    }
/*
    @Override
    public void save(Hour hour) {
        hourRepository.save(hour);
    }
*/

}
