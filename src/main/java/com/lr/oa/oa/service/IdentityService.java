package com.lr.oa.oa.service;

import org.springframework.web.bind.annotation.RequestParam;

public interface IdentityService {

    String userLogin(String name, String pass, String vcode);

}