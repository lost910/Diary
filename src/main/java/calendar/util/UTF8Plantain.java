package calendar.util;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;

/**
 * Created by Marat on 02.04.2015.
 */
public class UTF8Plantain {
    public static byte[] UTF8ToBytes(String s) {
        Charset cset = Charset.forName("UTF-8");
        ByteBuffer buf = cset.encode(s);
        return buf.array();
    }

    public static byte[] UTF16ToBytes(String s) {
        Charset cset = Charset.forName("UTF-16LE");
        ByteBuffer buf = cset.encode(s);
        return buf.array();
    }

    public static String FromBytes(byte[] b) {
        return new String(b);
    }

    public static String Restore(String source) {
        Charset cset = Charset.forName("UTF-8");
        ByteBuffer buf = cset.encode(source);
        byte[] b = buf.array();
        return new String(b);
    }
}
