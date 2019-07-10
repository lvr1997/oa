package com.lr.oa.oa.interceptor;

import com.lr.oa.oa.entity.User;
import com.lr.oa.oa.utils.constant.ConstantUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * 登录拦截器
 *
 * @author
 */
@Component
public class LoginInterceptor implements HandlerInterceptor {

    /**
     * 请求之前执行
     *
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(ConstantUtils.SESSION_USER);
        if (user != null){

            System.out.println("当前用户已登录，登录用户名为："+user.getName());
            return true;
        }
        request.setAttribute("loginMsg", "您还未登录，请执行登录操作");
        request.getRequestDispatcher("/identity/login").forward(request, response);

        return false;
    }

    /**
     * 视图渲染之前执行
     *
     * @param request
     * @param response
     * @param handler
     * @param modelAndView
     * @throws Exception
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }

    public List<String> getUrl(){
        List<String> staticResourceUrl = new ArrayList<>();
        staticResourceUrl.add("/blockUI/**");
        staticResourceUrl.add("/bootstrap/**");
        staticResourceUrl.add("/css/**");
        staticResourceUrl.add("/dtree/**");
        staticResourceUrl.add("/easyUI/**");
        staticResourceUrl.add("/fonts/**");
        staticResourceUrl.add("/images/**");
        staticResourceUrl.add("/jquery/**");
        staticResourceUrl.add("/main.js");
        staticResourceUrl.add("/createCode");
        return staticResourceUrl;
    }

}
