package calendar.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Marat on 06.04.2015.
 */
public class CSessionManager {
    public static class CSession {
        private long key;
        private int user_id;

        public CSession(long key, int user_id) {
            this.key = key;
            this.user_id = user_id;
        }

        public long getKey() {
            return key;
        }

        public void setKey(long key) {
            this.key = key;
        }

        public int getUser_id() {
            return user_id;
        }

        public void setUser_id(int user_id) {
            this.user_id = user_id;
        }
    }

    private static final List<CSession> sessions = new ArrayList<CSession>();
   // private static List<CSession> sessions;

    private static long makeKey() {
        Random rnd = new Random();
        long key = 0;
        boolean done = false;
        do {
            done = true;
            key = rnd.nextLong();
            for(CSession s : sessions) {
                if(s.getKey() == key) {
                    done = false;
                    break;
                }
            }
        } while(!done);
        return key;
    }

    public static CSession getSession(int user_id) {

       // if(sessions == null) sessions = new ArrayList<CSession>();
        for(CSession s : sessions) {
            if(s.getUser_id() == user_id) return s;
        }

        CSession s = new CSession(makeKey(), user_id);
        sessions.add(s);
        return s;
    }

    public static CSession findSessionByKey(long key) {
       // if(sessions == null) sessions = new ArrayList<CSession>();
        for(CSession s : sessions) {
            if(s.getKey() == key) return s;
        }

        return null;
    }

    public static void RemoveSessionByKey(long key) {
        sessions.remove(findSessionByKey(key));
    }
}
