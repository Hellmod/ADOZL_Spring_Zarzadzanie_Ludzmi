package pl.rafalmiskiewicz.ADOZL.hours;

import javax.ws.rs.GET;
import javax.ws.rs.POST;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.rafalmiskiewicz.ADOZL.user.User;
import pl.rafalmiskiewicz.ADOZL.user.UserService;
import pl.rafalmiskiewicz.ADOZL.utilities.UserUtilities;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
public class HoursPageController {

    @Autowired
    private HourService hourService;

    @Autowired
    private UserService userService;

    @POST
    @RequestMapping(value = "/hour")
    public String openAdminMainPage( Model model) {
        List<Hour> hourList = hourService.findAllByUserId(userService.findUserByEmail(UserUtilities.getLoggedUser()).getId());
        model.addAttribute("hourList", hourList);
        return "hour/hour";
    }

    @GET
    @RequestMapping(value = "/hour/edit/{id_hour}")
    @Secured(value = { "ROLE_ADMIN" })
    public String getHourIdToEdit(@PathVariable("id_hour") int id, Model model) {

        Hour hour = new Hour();
        hour=hourService.findHourById(id);

        model.addAttribute("hour", hour);

        return "hour/houredit";
    }

}
