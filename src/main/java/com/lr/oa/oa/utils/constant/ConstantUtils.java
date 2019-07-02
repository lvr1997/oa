package com.lr.oa.oa.utils.constant;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpSession;

public class ConstantUtils {

    public static final String VERIFY_CODE = "verify_code";

    public static final String SESSION_USER ="session_user";

    public static HttpSession getSession (){
       // ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest().getSession();
        HttpSession session=((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getSession();
                return session;
    }
}
