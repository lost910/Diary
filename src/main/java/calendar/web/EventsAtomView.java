package calendar.web;

import calendar.model.CEvent;
import calendar.model.Events;
import com.rometools.rome.feed.atom.Content;
import com.rometools.rome.feed.atom.Entry;
import com.rometools.rome.feed.atom.Feed;
import org.springframework.web.servlet.view.feed.AbstractAtomFeedView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Marat on 19.03.2015.
 */
public class EventsAtomView extends AbstractAtomFeedView {
    @Override
    protected void buildFeedMetadata(Map<String, Object> model,
                                     Feed feed, HttpServletRequest request) {

        feed.setId("tag:springsource.org");
        feed.setTitle("Events");
    }

    @Override
    protected List<Entry> buildFeedEntries(Map<String, Object> map,
                                           HttpServletRequest httpServletRequest,
                                           HttpServletResponse httpServletResponse) throws Exception {

        Events events = (Events)map.get("events");
        List<CEvent> CEventList = events.getCEventList();
        List<Entry> entries = new ArrayList<Entry>(CEventList.size());

        for(CEvent CEvent : CEventList) {
            Entry entry = new Entry();
            entry.setId(String.format("tag:springsource.org, %s", CEvent.getId()));
            entry.setTitle(String.format("CEvent: %s %s", CEvent.getTheme(), CEvent.getDescr()));

            Content summary = new Content();
            summary.setValue(CEvent.getDescr());
            entry.setSummary(summary);

            entries.add(entry);
        }
        httpServletResponse.setContentType("wtf");
        return entries;
    }
}
