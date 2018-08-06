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
                if (c > 0x7F) {
                    writer.append("&#");
                    writer.append(Integer.toString(c, 10));
                    writer.append(';');
                } else {
                    writer.append(c);
                }
            } else {
                writer.append(entityName);
            }
        }
    }

}
