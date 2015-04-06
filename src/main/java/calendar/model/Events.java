package calendar.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Marat on 19.03.2015.
 */
@XmlRootElement
public class Events {
    private List<CEvent> CEventList;

    @XmlElement
    public List<CEvent> getCEventList() {
        if(CEventList == null) {
            CEventList = new ArrayList<CEvent>();
        }
        return CEventList;
    }
}
