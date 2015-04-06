package calendar.web;

import calendar.model.CEvent;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;

/**
 * Created by Marat on 19.03.2015.
 */
public class EventValidator {
    public void validate(CEvent CEvent, Errors errors) {
        String name = CEvent.getTheme();

        if (!StringUtils.hasLength(name)) {
            errors.rejectValue("theme", "required", "required");
        }

        if (CEvent.getCr_date() == null) {
            errors.rejectValue("Creation data", "required", "required");
        }
    }
}
