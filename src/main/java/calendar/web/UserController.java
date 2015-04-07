package calendar.web;

import calendar.model.User;
import calendar.service.CalendarService;
import calendar.util.CSessionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * Created by Marat on 17.03.2015.
 */
@Controller
@SessionAttributes("user")
public class UserController {
    private final CalendarService cs;

    @Autowired
    public UserController(CalendarService cs) {
        this.cs = cs;
    }

    @InitBinder
    public void setAllowedFields(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
    }

    @RequestMapping(value = "/", method = {RequestMethod.GET, RequestMethod.HEAD})
    public String SignIn(Map<String, Object> model,
    HttpServletRequest request
    ) {
        Cookie[] cooks = request.getCookies();
        Cookie temp = null;
        if(cooks!= null){
            for(Cookie s:cooks)
            {
                if(s.getName().equals("DiaryKey")) {
                    temp = s;
                    break;
                }
            }
        }

        if(temp!=null) {
            CSessionManager.CSession s = CSessionManager.findSessionByKey(Long.parseLong(temp.getValue()));
            if(s!=null){
                return "redirect:welcome/" + s.getKey();
            }
        }
        User user = new User();
        model.put("user", user);
        return "login";
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public String processSignIn(@Valid User user, BindingResult result, SessionStatus status,
                                HttpServletResponse response
    ) {
        User tempuser = null;
        if (result.hasErrors()) {
            return "login";
        } else {
            if(!cs.peekUserByLogin(user.getLogin())){return "login"; }
            tempuser = cs.findUserByLogin(user.getLogin());
            if(tempuser == null) {return "login"; }
            if(!tempuser.getPassword().equals(user.getPassword())) {return "login"; }

            status.setComplete();

            CSessionManager.CSession s = CSessionManager.getSession(tempuser.getId());

            Cookie Cook = new Cookie("DiaryKey", String.valueOf(s.getKey()));
            Cook.setMaxAge(72*60*60);
            response.addCookie(Cook);

            return "redirect:welcome/" + s.getKey();
        }
    }


    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String processRegistration(@Valid User user, BindingResult result, SessionStatus status,
                                HttpServletResponse response){
        cs.saveUser(user);
        CSessionManager.CSession s = CSessionManager.getSession(user.getId());
        Cookie Cook = new Cookie("DiaryKey", String.valueOf(s.getKey()));
        Cook.setMaxAge(72*60*60);
        response.addCookie(Cook);
        status.setComplete();
        return "redirect:welcome/" + s.getKey();
    }

    @RequestMapping("/registration")
    public String Registration(Map<String, Object> model,
                         HttpServletRequest request){
        User user = new User();
        model.put("user", user);
        return "registration";
    }
}
