package com.danielrodrigues.app.utils;

import org.springframework.beans.factory.annotation.Autowired;

import jakarta.servlet.http.HttpServletRequest;

public class BaseUrlUtil {

    private static HttpServletRequest request;
    
    public BaseUrlUtil() {}

    @Autowired
    public BaseUrlUtil(HttpServletRequest request) {
        BaseUrlUtil.request = request;
    }
    
    public static String getUrl() {
        String scheme = request.getScheme();
        String serverName = request.getServerName();
        int serverPort = request.getServerPort();
        String contextPath = request.getContextPath();
        String url = scheme + "://" + serverName + ":" + serverPort + contextPath;
        return url;
    }
}
