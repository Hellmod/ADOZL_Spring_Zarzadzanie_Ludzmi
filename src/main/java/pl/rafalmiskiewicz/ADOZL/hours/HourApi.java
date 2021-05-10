package pl.rafalmiskiewicz.ADOZL.hours;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.rafalmiskiewicz.ADOZL.user.UserService;
import pl.rafalmiskiewicz.ADOZL.utilities.UserUtilities;

import java.util.List;

@RestController
public class HourApi {

    @Autowired
    private HourService hourService;

    @Autowired
    private UserService userService;

    @RequestMapping({ "/api/hour" })
    public String firstPage() {
        List<Hour> hourList = hourService.findAllByUserId(userService.findUserByEmail(UserUtilities.getLoggedUser()).getId());
        return new Gson().toJson(hourList );
    }
}
