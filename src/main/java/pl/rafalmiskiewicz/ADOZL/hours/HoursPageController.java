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

    @Autowired
    private HourRepository hourRepository;

    @POST
    @RequestMapping(value = "/hour")
    @Secured(value = {"ROLE_ADMIN", "ROLE_USER"})
    public String openHourNewMainPage(Model model) {
        List<Hour> hourList = hourService.findAllByUserId(userService.findUserByEmail(UserUtilities.getLoggedUser()).getId());
        for (Hour hour :hourList) {
            hour.generateOnlyString();
        }
        model.addAttribute("hourList", hourList);
        model.addAttribute(new Hour());
        return "hour/hour";
    }

    @GET
    @RequestMapping(value = "/hour/edit")
    @Secured(value = {"ROLE_ADMIN", "ROLE_USER"})
    public String getHourIdToEditNew(Hour hour, Model model) {

        System.out.println(hour);
        hour = hourService.findHourById(hour.getId_hours());
        try {
            hour.dateToString();
            hour.generateOnlyString();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        model.addAttribute("hour", hour);

        return "hour/edithour";
    }

    @GET
    @RequestMapping(value = "/hour/addhour")
    @Secured(value = {"ROLE_ADMIN", "ROLE_USER"})
    public String addHour(Model model) {

        Hour h = new Hour();
        model.addAttribute("hour", h);

        return "hour/addhour";
    }

    @POST
    @RequestMapping(value = "/hour/inserthour")
    @Secured(value = {"ROLE_ADMIN", "ROLE_USER"})
    public String registerHour(Hour hour, BindingResult result, Model model, Locale locale) {
        String returnPage = null;

        new HourAddValidator().validate(hour, result);

        if (result.hasErrors()) {
            model.addAttribute("hour", hour);
            returnPage = "hour/addhour";
        } else {
            try {
                hour.divdedDateToString();
                hour.stringToDate();
            } catch (ParseException e) {
                e.printStackTrace();
            }
            hour.setUser(userService.findUserByEmail(UserUtilities.getLoggedUser()));
            hourService.saveHourNew(hour);
            model.addAttribute("message", messageSource.getMessage("hour.add.success", null, locale));
            model.addAttribute("hour", new Hour());
            returnPage = "hour/addhour";
        }

        return returnPage;


    }


    @POST
    @RequestMapping(value = "/hour/edit/updatehour")
    @Secured(value = {"ROLE_ADMIN", "ROLE_USER"})
    public String editHour(Hour hour, BindingResult result, Model model, Locale locale) {

        String returnPage = null;
        new HourAddValidator().validate(hour, result);

        if (result.hasErrors()) {
            returnPage = "hour/edithour";
        } else {

            try {
                hour.divdedDateToString();
                hour.stringToDate();
            } catch (ParseException e) {
                e.printStackTrace();
            }

            hourService.updateHour(hour);

            model.addAttribute("message", messageSource.getMessage("hour.edit.success", null, locale));
            model.addAttribute("hour", new Hour());
            returnPage = "hour/edithour";
        }

        return returnPage;


    }


}
