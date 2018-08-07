package com.mgmsec.HackMyTeeth.HackMyTeeth.service;

import org.springframework.stereotype.Service;

import java.util.HashMap;
@Service
public class XssServiceImpl implements XssService {
    public static final HashMap m = new HashMap();
    static {
        m.put(34, "&quot;"); // < - less-than
        m.put(60, "&lt;"); // < - less-than
        m.put(62, "&gt;"); // > - greater-than
        m.put(38, "&amp;"); // > - greater-than
//        m.put(39, "Apostrophe"); // > - greater-than
//        m.put(47, "Slash"); // > - greater-than

    }
    @Override
    public boolean preventXSS(String input) {
        return false;
    }

    @Override
    public String escapeHtml(String str) {
        try {
            StringBuffer writer = new StringBuffer((int)
                    (str.length() * 1.5));
            escape(writer, str);
            System.out.println("encoded string is " + writer.toString());
            return writer.toString();
        } catch (Exception ioe) {
            ioe.printStackTrace();
            return null;
        }
    }
    static void escape(StringBuffer writer, String str) {

        int len = str.length();
        for (int i = 0; i < len; i++) {
            char c = str.charAt(i);
            int ascii = (int) c;
            String entityName = (String) m.get(ascii);
            if (entityName == null) {
                if (c > 0x7F || c == '"' || c == '<' || c == '>' || c == '&' || c == '/' || c == 39) {
                    writer.append("&#");
                    writer.append(Integer.toString(c, 10));
                    writer.append(';');
                } else{
                    writer.append(c);
                }
            } else {
                writer.append(entityName);
            }
        }
    }
    public  String escapeJS(String s) {
        StringBuilder write = new StringBuilder((int)(s.length() * 1.5));
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (!(c < 256  && (c >= '0' && c <= '9') || (c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z') || (c >= 128 && c <= 237))) {
                write.append("\\x");
                write.append(Integer.toHexString((int) c));
            } else {
                write.append(c);
            }
        }
        return write.toString();
    }

}
