package com.els.tyoa.Util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

public class MyUtil {
    public static String decoder(String str) throws UnsupportedEncodingException {
        return URLDecoder.decode(str,"UTF-8");
    }

    public static String newdecoder(String str) throws UnsupportedEncodingException {
        return URLDecoder.decode(URLEncoder.encode(str,"ISO8859-1"),"UTF-8");
    }
}
