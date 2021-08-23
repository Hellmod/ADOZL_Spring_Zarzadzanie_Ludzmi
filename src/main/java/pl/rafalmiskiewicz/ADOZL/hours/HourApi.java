package pl.rafalmiskiewicz.ADOZL.hours;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.rafalmiskiewicz.ADOZL.places.PlacesService;
import pl.rafalmiskiewicz.ADOZL.schedule.Schedule;
import pl.rafalmiskiewicz.ADOZL.schedule.ScheduleService;
import pl.rafalmiskiewicz.ADOZL.user.UserService;
import pl.rafalmiskiewicz.ADOZL.utilities.UserUtilities;

import java.util.List;

@RestController
public class HourApi {

    @Autowired
    private HourService hourService;

    @Autowired
    private UserService userService;

    @Autowired
    private ScheduleService scheduleService;

    @Autowired
    private PlacesService placesService;

    @RequestMapping({"/api/hours"})
    public String hours() {
        List<Hour> hourList = hourService.findAllByUserId(userService.findUserByEmail(UserUtilities.getLoggedUser()).getId());
        return new Gson().toJson(hourList);
    }

    @RequestMapping({"/api/schedules"})
    public String schedules() {
        List<Schedule> scheduleList = scheduleService.findAllByUserId(userService.findUserByEmail(UserUtilities.getLoggedUser()).getId());
        for (Schedule s : scheduleList) {
            matchPlaces(s);
        }
        return new Gson().toJson(scheduleList);
    }

    void matchPlaces(Schedule schedule) {
        schedule.setPlaces(placesService.findPlacesById(schedule.getId_places()));
    }
}
