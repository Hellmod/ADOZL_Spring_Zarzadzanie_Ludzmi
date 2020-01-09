package pl.rafalmiskiewicz.ADOZL.hours;

import javax.ws.rs.GET;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.rafalmiskiewicz.ADOZL.admin.AdminService;
import pl.rafalmiskiewicz.ADOZL.user.User;

import java.util.List;


@Controller
public class HoursPageController {

    @Autowired
    private HourService hourService;

    @GET
    @RequestMapping(value = "/hour")
    public String openAdminMainPage(Model model) {
        List<Hour> hourList = getAllUsers();
        model.addAttribute("hourList", hourList);
        return "hour";
    }

    //Pobranie listy userów
    private List<Hour> getAllUsers() {
        List<Hour> usersList = hourService.findAll();
        /*
        for(Hour hour : usersList) {
            int numerRoli = hour.getRoles().iterator().next().getId();
            hour.setNrRoli(numerRoli);
        }
        */

        return usersList;
    }

}
