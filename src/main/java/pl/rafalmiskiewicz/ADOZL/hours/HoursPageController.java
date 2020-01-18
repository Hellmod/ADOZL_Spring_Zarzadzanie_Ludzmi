package pl.rafalmiskiewicz.ADOZL.hours;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.rafalmiskiewicz.ADOZL.user.User;
import pl.rafalmiskiewicz.ADOZL.user.UserService;
import pl.rafalmiskiewicz.ADOZL.utilities.UserUtilities;
import pl.rafalmiskiewicz.ADOZL.validators.HourAddValidator;
import pl.rafalmiskiewicz.ADOZL.validators.UserRegisterValidator;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import java.text.ParseException;
import java.util.List;
import java.util.Locale;


@Controller
public class HoursPageController {

    @Autowired
    MessageSource messageSource;

    @Autowired
    private HourService hourService;

    @Autowired
    private UserService userService;

    @Qualifier("hourRepository")
    @Autowired
    private HourRepository hourRepository;
    /*
        @POST
        @RequestMapping(value = "/hour")
        public String openHourMainPage(Model model) {
            List<Hour> hourList = hourService.findAllByUserId(userService.findUserByEmail(UserUtilities.getLoggedUser()).getId());
            model.addAttribute("hourList", hourList);
            return "hour/hour";
        }
    */
    @POST
    @RequestMapping(value = "/hour")
    public String openHourNewMainPage(Model model) {
        List<Hour> hourList = hourService.findAllByUserId(userService.findUserByEmail(UserUtilities.getLoggedUser()).getId());
        model.addAttribute("hourList", hourList);
        model.addAttribute(new Hour());
        return "hour/hour";
    }
    /**
     @GET
     @RequestMapping(value = "/hour/edit/{id_hour}")
     @Secured(value = {"ROLE_ADMIN"})
     public String getHourIdToEdit(@PathVariable("id_hour") int id, Model model) {

     Hour hour = new Hour();
     hour.setId_hours(id);
     hour = hourService.findHourById(id);
     try {
     hour.dateToString();
     } catch (ParseException e) {
     e.printStackTrace();
     }
     model.addAttribute("hour", hour);

     return "hour/houredit";
     }
     */
    @GET
    @RequestMapping(value = "/hour/edit")
    @Secured(value = {"ROLE_ADMIN"})
    public String getHourIdToEditNew(Hour hour, Model model) {
        //ToDo wyświetlenie godzin do edycji

        System.out.println(hour);
        hour=hourService.findHourById(hour.getId_hours());
        try {
            hour.dateToString();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        model.addAttribute("hour", hour);

        return "hour/edithour";
    }

    @GET
    @RequestMapping(value = "/hour/addhour")
    @Secured(value = {"ROLE_ADMIN","ROLE_USER"})
    public String addHour( Model model) {

        Hour h = new Hour();
        model.addAttribute("hour", h);

        return "hour/addhour";
    }

    @POST
    @RequestMapping(value = "/hour/inserthour")
    public String registerHour(Hour hour,  BindingResult result, Model model, Locale locale) {
        String returnPage = null;
        try {
            hour.stringToDate();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        hour.setId_user(userService.findUserByEmail(UserUtilities.getLoggedUser()).getId());

        new HourAddValidator().validate(hour, result);

        if (result.hasErrors()) {
            returnPage = "hour/addhour";
        } else {
            //hourRepository.save(hour);
            //hourService.saveHour(hour);
            hourService.saveHourNew(hour);
            //hourService.insertHourString(hour);
            model.addAttribute("message", messageSource.getMessage("hour.add.success", null, locale));
            model.addAttribute("hour", new Hour());
            returnPage = "hour/addhour";
        }

        return returnPage;


    }


    @POST
    @RequestMapping(value = "/hour/edit/updatehour")
    public String editHour(Hour hour,  BindingResult result, Model model, Locale locale) {

        String returnPage = null;
        try {
            hour.stringToDate();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        //hour.setId_user(userService.findUserByEmail(UserUtilities.getLoggedUser()).getId());
        new HourAddValidator().validate(hour, result);

        if (result.hasErrors()) {
            returnPage = "hour/edithour";
        } else {
            hourService.updateHour(hour);

            model.addAttribute("message", messageSource.getMessage("hour.add.success", null, locale));
            model.addAttribute("hour", new Hour());
            returnPage = "hour/edithour";
        }

        return returnPage;


    }



}
