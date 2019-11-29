package cn.nianzuochen.getajob.service;

import cn.nianzuochen.getajob.domain.dao.UserLogin;
import cn.nianzuochen.getajob.vo.response.RespBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "GETAJOBSERVICEB", fallbackFactory = LoginServiceFallBack.class)
public interface LoginService {
    @PostMapping("login")
    RespBean login(@RequestBody UserLogin userInfo);
}
