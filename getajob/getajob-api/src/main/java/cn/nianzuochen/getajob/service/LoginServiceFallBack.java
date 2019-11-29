package cn.nianzuochen.getajob.service;

import cn.nianzuochen.getajob.domain.dao.UserLogin;
import cn.nianzuochen.getajob.vo.response.RespBean;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

@Component
public class LoginServiceFallBack implements FallbackFactory<LoginService> {
    @Override
    public LoginService create(Throwable throwable) {
        return new LoginService() {
            @Override
            public RespBean login(UserLogin userInfo) {
                return RespBean.ok("延迟导致操作失败，请稍后重试！");
            }
        };
    }
}
