package calendar.web;

import calendar.model.User;
import calendar.model.UserFile;
import calendar.service.CalendarService;
import calendar.util.CSessionManager;
import calendar.util.UserFileUtils;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

/**
 * Created by Marat on 12.04.2015.
 */

@Controller
@SessionAttributes("file")
public class UserFileController {
    private final CalendarService cs;

    @Autowired
    public UserFileController(CalendarService cs) {
        this.cs = cs;
    }

    @InitBinder
    public void setAllowedFields(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
    }

    @RequestMapping(value = "welcome/{sessionKey}/deleteFile", method = RequestMethod.GET)
    public String deleteFile(@PathVariable("sessionKey") long s_key,
                             @RequestParam("file_id") int id) {
        cs.deleteFileId(id);
        return "redirect:/";
    }

    @RequestMapping(value = "welcome/{sessionKey}/downloadFile", method = RequestMethod.GET)
    public String downloadFile(@PathVariable("sessionKey") long s_key,
                               @RequestParam("file_id") int id) {

        UserFile userFile = cs.findFileById(id);
        try {
            byte[] bytes = UserFileUtils.readFile(userFile.getFname());

        } catch (IOException e) {

        }

        return "redirect:/";
    }

    @RequestMapping(value = "welcome/{sessionKey}/uploadFile", method = RequestMethod.POST)
    public String uploadFile(@PathVariable("sessionKey") long s_key,
                             @RequestParam("file") MultipartFile file,
                             @RequestParam("fname") String fname)
    {

        if(file.isEmpty()) {
            System.out.println("empty file");
            return "redirect:/";
        }

        try {
            int user_id = CSessionManager.findSessionByKey(s_key).getUser_id();

            String t = "";
            int i, pos = 0;
            for(i = 0; i < fname.length(); i++) {
                if(fname.charAt(i) == '/') {
                    pos = i + 1;
                }
            }

            for(i = pos; i < fname.length(); i++) {
                t += fname.charAt(i);
            }

            UserFileUtils.writeFile(file.getBytes(), fname);

            cs.addNewFile(t, file.getBytes().length, user_id);
        }
        catch (IOException e) {
            System.out.println(e.toString());
        }

        return "redirect:/";
    }
}
