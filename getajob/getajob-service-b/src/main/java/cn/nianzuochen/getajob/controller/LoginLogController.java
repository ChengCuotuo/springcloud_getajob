package cn.nianzuochen.getajob.controller;

import cn.nianzuochen.getajob.service.LoginLogService;
import cn.nianzuochen.getajob.vo.request.LoadLoginLogReq;
import cn.nianzuochen.getajob.vo.response.LoginLogResp;
import cn.nianzuochen.getajob.vo.response.RespBean;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api(value = "登录日志控制器类")
@RestController
public class LoginLogController {

    @Autowired
    LoginLogService loginLogService;


    @PostMapping("/loadLoginLog")
    public RespBean loadUserLoginLog(@RequestBody LoadLoginLogReq loadLoginLogReq) {
        LoginLogResp loginLogResp = loginLogService.selectLoginLog(loadLoginLogReq);

        if (loginLogResp != null) {
            return RespBean.ok(loginLogResp);
        } else {
            return RespBean.error("查询失败！");
        }
    }

    /**
     * 根据登录日志 id 删除登录日志
     * @param loginLogId
     * @return
     */
    @DeleteMapping("/deleteLoginLogById/{loginLogId}")
    public Integer deleteLoginLogById(@PathVariable("loginLogId") Integer loginLogId) {
        return loginLogService.deleteByPrimaryKey(loginLogId);
    }
}
