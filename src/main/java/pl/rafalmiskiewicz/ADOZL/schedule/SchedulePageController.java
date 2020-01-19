package pl.rafalmiskiewicz.ADOZL.schedule;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.rafalmiskiewicz.ADOZL.places.PlacesService;
import pl.rafalmiskiewicz.ADOZL.user.UserService;
import pl.rafalmiskiewicz.ADOZL.utilities.UserUtilities;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import java.text.ParseException;
import java.util.List;
import java.util.Locale;


@Controller
public class SchedulePageController {

    @Autowired
    MessageSource messageSource;

    @Autowired
    private ScheduleService scheduleService;

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

    @GET
    @RequestMapping(value = "/schedule/addschedule")
    @Secured(value = {"ROLE_ADMIN","ROLE_USER"})
    public String addSchedule( Model model) {

        Schedule h = new Schedule();
        model.addAttribute("schedule", h);

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


    }


    void matchPlaces(Schedule schedule){
        schedule.setPlaces(placesService.findPlacesById(schedule.getId_places()));
    }



}
