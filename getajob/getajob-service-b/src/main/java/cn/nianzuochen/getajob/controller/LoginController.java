package cn.nianzuochen.getajob.controller;

import cn.nianzuochen.getajob.config.TrueIP;
import cn.nianzuochen.getajob.domain.dao.LoginLog;
import cn.nianzuochen.getajob.domain.dao.UserInfo;
import cn.nianzuochen.getajob.domain.dao.UserLogin;
import cn.nianzuochen.getajob.service.LoginLogService;
import cn.nianzuochen.getajob.service.UserInfoService;
import cn.nianzuochen.getajob.vo.response.RespBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;

@RestController
public class LoginController {

    @Autowired
    UserInfoService userInfoService;

    @Autowired
    LoginLogService loginLogService;

    @Autowired
    HttpServletRequest request;

    @PostMapping("login")
    public RespBean login(@RequestBody UserLogin userInfo) {

        HttpSession session = request.getSession();
        LoginLog loginLog = new LoginLog();
        boolean loginResult = true;

        String loginInfo = "";

        String username = null;
        String password = null;
        UserInfo user = null;
        if (userInfo != null) {
            username = userInfo.getUsername().trim();
            password = userInfo.getPassword().trim();

            if ("".equals(username) || "".equals(password)) {
                loginInfo = "用户名密码不能空";
                loginResult = false;
            } else {
                user = userInfoService.selectByUsername(username);
                if (user == null) {
                    loginInfo = "该用户不存在";
                    loginResult = false;
                } else if (!password.equals(user.getPassword())) {
                    loginInfo = "用户名与密码不匹配";
                    loginResult = false;
                } else {
                    loginInfo = "登录成功";
                }
            }

        } else {
            loginInfo = "用户名密码为空";
            loginResult = false;
        }
        if (loginResult) {
            session.setAttribute("operationUserId", user.getUserid());
            loginLog.setUserId(user.getUserid());
            loginLog.setLoginDesc(loginInfo);
            loginLog.setLoginUserIp(TrueIP.getIpAddr(request));
            loginLog.setLoginTime(new Date());
            loginLog.setIfSuccess(1);
            loginLogService.insertSelective(loginLog);

            return RespBean.ok(loginInfo, user);
        } else{
            loginLog.setUserId(0);
            loginLog.setLoginDesc(loginInfo);
            loginLog.setLoginUserIp(TrueIP.getIpAddr(request));
            loginLog.setLoginTime(new Date());
            loginLog.setIfSuccess(2);
            loginLogService.insertSelective(loginLog);
            return RespBean.error(loginInfo);
        }
    }
}
