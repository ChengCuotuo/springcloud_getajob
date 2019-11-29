package cn.nianzuochen.getajob.controller;

import cn.nianzuochen.getajob.service.LoginLogService;
import cn.nianzuochen.getajob.vo.request.LoadLoginLogReq;
import cn.nianzuochen.getajob.vo.response.RespBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/system/loginlog")
public class LoginLogConsumeController {

    @Autowired
    LoginLogService loginLogService;


    @PostMapping("/loadLoginLog")
    public RespBean loadUserLoginLog(@RequestBody LoadLoginLogReq loadLoginLogReq) {
       return loginLogService.loadUserLoginLog(loadLoginLogReq);
    }

    /**
     * 根据登录日志 id 删除登录日志
     * @param loginLogId
     * @return
     */
    @DeleteMapping("/deleteLoginLogById/{loginLogId}")
    public Integer deleteLoginLogById(@PathVariable("loginLogId") Integer loginLogId) {
        return loginLogService.deleteLoginLogById(loginLogId);
    }
}
