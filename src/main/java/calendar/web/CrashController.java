package calendar.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Marat on 17.03.2015.
 */
@Controller
public class CrashController {
    @RequestMapping(value = "/oups", method = RequestMethod.GET)
    public String triggerException() {
        throw new RuntimeException("Successful crashed.");
    }
}
