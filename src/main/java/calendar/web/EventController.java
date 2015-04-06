package calendar.web;

import calendar.model.CEvent;
import calendar.model.Events;
import calendar.model.User;
import calendar.service.CalendarService;
import calendar.util.CSessionManager;
import calendar.util.UTF8Plantain;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import jdk.nashorn.internal.parser.JSONParser;
import org.joda.time.DateTime;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.text.ParseException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Marat on 17.03.2015.
 */
@Controller
@SessionAttributes("event")
public class EventController {
    private final CalendarService cs;

    @Autowired
    public EventController(CalendarService cs) {
        this.cs = cs;
    }

    @InitBinder
    public void setAllowedFields(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
    }

    @RequestMapping("/events")
    public String showEventList(Map<String, Object> model) {
        Events events = new Events();
        events.getCEventList().addAll(this.cs.getAllEvent());
        model.put("events", events);
        return "events/eventList";
    }

<<<<<<< HEAD
    @RequestMapping("/welcome/{sessionKey}")
    public String showEventListWelcome(@PathVariable("sessionKey") long s_key, Map<String, Object> model) {
        Events events = new Events();
        CSessionManager.CSession s = CSessionManager.findSessionByKey(s_key);
        int u_id = s.getUser_id();

        events.getCEventList().addAll(this.cs.getEventsByLinkedId(u_id));

        model.put("events", events);
        model.put("session_key", s_key);
        model.put("user_login", cs.findUserById(u_id).getLogin());
        return "welcome";
    }

    @RequestMapping(value = "welcome/{sessionKey}/getEvent", method = RequestMethod.POST)
    public void handleAjaxRequest(HttpServletResponse res,
                                  @PathVariable("sessionKey") long s_key,
=======
    @RequestMapping("/welcome/{userId}")
    public String showEventListWelcome(@PathVariable("userId") int id, Map<String, Object> model) {
        Events events = new Events();
        events.getCEventList().addAll(this.cs.getEventsByLinkedId(id));
        model.put("events", events);
        model.put("user_id", id);
        model.put("user_login", cs.findUserById(id).getLogin());
        return "welcome";
    }

    @RequestMapping(value = "welcome/{userId}/getEvent", method = RequestMethod.POST)
    public void handleAjaxRequest(HttpServletResponse res,
                                  @PathVariable("userId") int UserId,
>>>>>>> origin/master
                                  @RequestParam(value = "eventId") int id) {
        try{
           CEvent CEvent = this.cs.findEventById(id);

            Map<String, Object> mapEvent = new HashMap<String, Object>();
            mapEvent.put("id", CEvent.getId());
            mapEvent.put("Theme", CEvent.getTheme());
            mapEvent.put("Descr", CEvent.getDescr());
            mapEvent.put("Date", CEvent.getCr_date().toString());
            mapEvent.put("User_id", CEvent.getUser().getId());
            Gson gson = new Gson();
            String json = gson.toJson(mapEvent);

            res.setContentType("text/html; charset=UTF-8");
            res.setCharacterEncoding( "UTF-8" );
            res.getWriter().write(json);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "users/{userId}/events/new", method = RequestMethod.GET)
    public String initCreationForm(@PathVariable("userId") int userId,
                                   Map<String, Object> model) {
        User user = this.cs.findUserById(userId);
        CEvent CEvent = new CEvent();
        model.put("CEvent", CEvent);
        return "events/createOrUpdateEventForm";
    }

    @RequestMapping(value = "/users/{userId}/events/new", method = RequestMethod.POST)
    public String processCreationForm(@ModelAttribute("event") CEvent CEvent,
                                      BindingResult result, SessionStatus status) {
        new EventValidator().validate(CEvent, result);
        if (result.hasErrors()) {
            return "events/createOrUpdateEventForm";
        } else {
            this.cs.saveEvent(CEvent);
            status.setComplete();
            return "redirect:/events/{eventId}";
        }
    }

    @RequestMapping(value = "/users/*/events/{eventId}/edit", method = RequestMethod.GET)
    public String initUpdateForm(@PathVariable("eventId") int eventId, Map<String, Object> model) {
        CEvent CEvent = this.cs.findEventById(eventId);
        model.put("CEvent", CEvent);
        return "events/createOrUpdateEventForm";
    }

    @RequestMapping(value = "/users/{userId}/events/{eventId}/edit", method = {RequestMethod.PUT, RequestMethod.POST})
    public String processUpdateForm(@ModelAttribute("event") CEvent CEvent,
                                    BindingResult result, SessionStatus status) {

        new EventValidator().validate(CEvent, result);
        if (result.hasErrors()) {
            return "events/createOrUpdateEventForm";
        } else {
            this.cs.saveEvent(CEvent);
            status.setComplete();
            return "redirect:/events/{eventId}";
        }
    }

    @RequestMapping("/CreateEvent")
    public String CreateEvent(Map<String, Object> model) {
        return "CreateEvent";
    }

<<<<<<< HEAD
    @RequestMapping(value = "welcome/{sessionKey}/UpdateEvent",
            method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
    public void UpdateEvent(HttpServletResponse res, @PathVariable("sessionKey") long s_key,
=======
    @RequestMapping(value = "welcome/{userId}/UpdateEvent", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
    public void UpdateEvent(HttpServletResponse res, @PathVariable("userId") int userId,
>>>>>>> origin/master
                            @RequestParam(value = "id") int id,
                            @RequestParam(value = "user") int user,
                            @RequestParam(value = "theme") String theme,
                            @RequestParam(value = "descr") String descr){
        try{
//            int userId = CSessionManager.findSessionByKey(s_key).getUser_id();
            CEvent UpdateEvent = cs.findEventById(id);

            UpdateEvent.setDescr(descr);
            UpdateEvent.setTheme(theme);
            cs.saveEvent(UpdateEvent);

            res.setContentType("text/html; charset=UTF-8");
            res.setCharacterEncoding( "UTF-8" );

            res.getWriter().write("done");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

<<<<<<< HEAD
    @RequestMapping(value = "welcome/{sessionKey}/DeleteEvent", method = RequestMethod.POST)
    public void DeleteEvent(HttpServletResponse res, @PathVariable("sessionKey") long s_key,
                            @RequestParam(value = "id") int id){
=======
    @RequestMapping(value = "welcome/{userId}/DeleteEvent", method = RequestMethod.POST)
    public void DeleteEvent(HttpServletResponse res, @PathVariable("userId") int userId, @RequestParam(value = "id") int id){
>>>>>>> origin/master
        try{
            this.cs.deleteEventById(id);
            res.getWriter().write("done");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
