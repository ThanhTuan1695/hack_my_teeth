package com.mgmsec.HackMyTeeth.HackMyTeeth.service;

public interface XssService {
    boolean preventXSS(String input);
    String escapeHtml(String string);
    String escapeJS(String string);
}
