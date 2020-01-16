package pl.rafalmiskiewicz.ADOZL.hours;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.rafalmiskiewicz.ADOZL.user.User;
import pl.rafalmiskiewicz.ADOZL.user.UserService;
import pl.rafalmiskiewicz.ADOZL.utilities.UserUtilities;
import pl.rafalmiskiewicz.ADOZL.validators.UserRegisterValidator;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import java.util.List;
import java.util.Locale;


@Controller
public class HoursPageController {

    @Autowired
    private HourService hourService;

    @Autowired
    private UserService userService;

    @POST
    @RequestMapping(value = "/hour")
    public String openAdminMainPage(Model model) {
        List<Hour> hourList = hourService.findAllByUserId(userService.findUserByEmail(UserUtilities.getLoggedUser()).getId());
        model.addAttribute("hourList", hourList);
        return "hour/hour";
    }

    @GET
    @RequestMapping(value = "/hour/edit/{id_hour}")
    @Secured(value = {"ROLE_ADMIN"})
    public String getHourIdToEdit(@PathVariable("id_hour") int id, Model model) {

        Hour hour = new Hour();
        hour = hourService.findHourById(id);

        model.addAttribute("hour", hour);

        return "hour/houredit";
    }

    @GET
    @RequestMapping(value = "/hour/addhour")
    @Secured(value = {"ROLE_ADMIN"})
    public String addHour( Model model) {

        Hour h = new Hour();
        model.addAttribute("hour", h);

        return "hour/houredit";
    }

    @POST
    @RequestMapping(value = "/hour/inserthour")
    public String registerHour(Hour hour, Model model) {
/*
        String returnPage = null;
        user.setMark(50);
        user.setActive(1);
        user.setIs_fired(false);
        user.setIs_new(true);


        User userExist = userService.findUserByEmail(user.getEmail());

        new UserRegisterValidator().validateEmailExist(userExist, result);

        new UserRegisterValidator().validate(user, result);


        if (result.hasErrors()) {
            returnPage = "register";
        } else {
            userService.saveUser(user);
            model.addAttribute("message", messageSource.getMessage("user.register.success", null, locale));
            model.addAttribute("user", new User());
            returnPage = "register";
        }
        */
        return "hour/houredit";


    }



}
