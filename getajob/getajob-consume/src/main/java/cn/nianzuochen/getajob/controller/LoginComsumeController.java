package cn.nianzuochen.getajob.controller;

import cn.nianzuochen.getajob.domain.dao.UserInfo;
import cn.nianzuochen.getajob.domain.dao.UserLogin;
import cn.nianzuochen.getajob.service.LoginService;
import cn.nianzuochen.getajob.vo.response.RespBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class LoginComsumeController {
    @Autowired
    LoginService loginService;

    @PostMapping("login")
    public RespBean login(@RequestBody UserLogin userInfo) {
        return loginService.login(userInfo);
    }
}
