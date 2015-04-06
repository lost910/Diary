package calendar.web;

import calendar.model.EventType;
import calendar.service.CalendarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;

import java.text.ParseException;
import java.util.Collection;
import java.util.Locale;

/**
 * Created by Marat on 22.03.2015.
 */
public class EventTypeFormatter implements Formatter<EventType> {
    private final CalendarService cs;

    @Autowired
    public EventTypeFormatter(CalendarService cs) {
        this.cs = cs;
    }

    @Override
    public String print(EventType eventType, Locale locale) {
        return eventType.toString();
    }

    @Override
    public EventType parse(String s, Locale locale) throws ParseException {
        return null;
    }
}
