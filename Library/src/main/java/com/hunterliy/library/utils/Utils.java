package com.hunterliy.library.utils;

public class Utils {

    public static String encodeUrlParam(String param){
        return param.replace("/","%%");
    }


    public static String decodeUrlParam(String param){
        return param.replace("%%","/");
    }
}
