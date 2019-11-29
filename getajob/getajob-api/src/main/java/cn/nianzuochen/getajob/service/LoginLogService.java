package cn.nianzuochen.getajob.service;

import cn.nianzuochen.getajob.vo.request.LoadLoginLogReq;
import cn.nianzuochen.getajob.vo.response.RespBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(value = "GETAJOBSERVICEB", fallbackFactory = LoginLogServiceFallBack.class)
public interface LoginLogService {
    @PostMapping("/loadLoginLog")
    public RespBean loadUserLoginLog(@RequestBody LoadLoginLogReq loadLoginLogReq);

    @DeleteMapping("/deleteLoginLogById/{loginLogId}")
    public Integer deleteLoginLogById(@PathVariable("loginLogId") Integer loginLogId);
}
