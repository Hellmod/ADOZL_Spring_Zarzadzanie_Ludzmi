package pl.rafalmiskiewicz.ADOZL.schedule;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import pl.rafalmiskiewicz.ADOZL.hours.Hour;
import pl.rafalmiskiewicz.ADOZL.hours.HourService;
import pl.rafalmiskiewicz.ADOZL.places.PlacesService;
import pl.rafalmiskiewicz.ADOZL.user.UserService;
import pl.rafalmiskiewicz.ADOZL.utilities.UserUtilities;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;


@Controller
public class SchedulePageController {

    @Autowired
    MessageSource messageSource;

    @Autowired
    private ScheduleService scheduleService;

    @Autowired
    private HourService hourService;

    @Autowired
    private UserService userService;

    @Autowired
    private PlacesService placesService;

    @POST
    @RequestMapping(value = "/schedule")
    @Secured(value = {"ROLE_ADMIN","ROLE_USER","ROLE_CONTRROLER"})
    public String openScheduleNewMainPage(Model model) {
        List<Schedule> scheduleList = scheduleService.findAllByUserId(userService.findUserByEmail(UserUtilities.getLoggedUser()).getId());
        for (Schedule s:scheduleList) {
            matchPlaces(s);
        }
        model.addAttribute("scheduleList", scheduleList);
        model.addAttribute(new Schedule());
        return "schedule/schedule";
    }

    @RequestMapping(value = "/schedule/addschedule")
    @Secured(value = {"ROLE_ADMIN","ROLE_USER"})
    public String addSchedule(Schedule schedule,Model model,String action) {
        Map<Integer, String> roleMap = new HashMap<Integer, String>();
        roleMap = prepareRoleMap();
        roleMap.remove(1);

        Hour ha = new Hour();
        //ha.setId_user(1);

       // List<Schedule>test= scheduleService.findAll(schedule);
        List<Hour>testHour= hourService.findAllFilter(ha);

        Map<Integer, String> placeMap = new HashMap<Integer, String>();
        placeMap = preparePlaceMap();

        List<Hour> hourList = hourService.findAll();
        for (Hour h:hourList) {
            matchUsers(h);
        }

        Map<Integer, String> userMap = new HashMap<Integer, String>();

        for(Hour h:hourList){
            userMap.put(h.getUser().getId(),h.getUser().getName()+" "+h.getUser().getLastName());
        }

        model.addAttribute("hourList", hourList);
        model.addAttribute("schedule", schedule);
        model.addAttribute("roleMap", roleMap);
        model.addAttribute("placeMap", placeMap);
        model.addAttribute("userMap", userMap);

        return "schedule/addschedule";
    }

    @POST
    @RequestMapping(value = "/schedule/insertschedule")
    @Secured(value = {"ROLE_ADMIN","ROLE_USER"})
    public String registerSchedule(Schedule schedule, BindingResult result, Model model, Locale locale) {
        String returnPage = null;
        try {
            schedule.stringToDate();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        schedule.setId_user(userService.findUserByEmail(UserUtilities.getLoggedUser()).getId());

        //new ScheduleAddValidator().validate(schedule, result);

        if (result.hasErrors()) {
            returnPage = "schedule/addschedule";
        } else {

            //scheduleRepository.save(schedule);
            //scheduleService.saveSchedule(schedule);
            scheduleService.saveScheduleNew(schedule);
            //scheduleService.insertScheduleString(schedule);
            model.addAttribute("message", messageSource.getMessage("schedule.add.success", null, locale));
            model.addAttribute("schedule", new Schedule());
            returnPage = "schedule/addschedule";
        }

        return returnPage;


    }

/*
    @POST
    @RequestMapping(value = "/schedule/edit/updateschedule")
    @Secured(value = {"ROLE_ADMIN","ROLE_USER"})
    public String editSchedule(Schedule schedule, BindingResult result, Model model, Locale locale) {

        String returnPage = null;
        try {
            schedule.stringToDate();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        //schedule.setId_user(userService.findUserByEmail(UserUtilities.getLoggedUser()).getId());
        //new ScheduleAddValidator().validate(schedule, result);


        if (result.hasErrors()) {
            returnPage = "schedule/editschedule";
        } else {
            scheduleService.updateSchedule(schedule);

            model.addAttribute("message", messageSource.getMessage("schedule.add.success", null, locale));
            model.addAttribute("schedule", new Schedule());
            returnPage = "schedule/editschedule";
        }

        return returnPage;


    }*/


    void matchPlaces(Schedule schedule){
        schedule.setPlaces(placesService.findPlacesById(schedule.getId_places()));
    }

    void matchUsers(Schedule schedule){
        schedule.setUser(userService.findUserById(schedule.getId_user()));
    }

    void matchUsers(Hour hour){
        hour.setUser(userService.findUserById(hour.getId_user()));
    }

    public Map<Integer, String> prepareRoleMap() {
        Locale locale = Locale.getDefault();
        Map<Integer, String> roleMap = new HashMap<Integer, String>();
        roleMap.put(1, messageSource.getMessage("word.admin", null, locale));
        roleMap.put(2, messageSource.getMessage("word.user", null, locale));
        roleMap.put(3, messageSource.getMessage("word.controller", null, locale));
        return roleMap;
    }

    public Map<Integer, String> preparePlaceMap() {
        Locale locale = Locale.getDefault();
        Map<Integer, String> roleMap = new HashMap<Integer, String>();
        roleMap.put(1, "Skrzyżowanie Szlak z Warszawską");
        roleMap.put(2, "AGH Czarnowiejska przystanek");
        roleMap.put(3,  "AGH UR");
        return roleMap;
    }

}
