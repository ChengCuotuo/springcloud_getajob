package cn.nianzuochen.getajob.service;

import cn.nianzuochen.getajob.vo.request.LoadLoginLogReq;
import cn.nianzuochen.getajob.vo.response.RespBean;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

@Component
public class LoginLogServiceFallBack implements FallbackFactory<LoginLogService> {
    @Override
    public LoginLogService create(Throwable throwable) {
        return new LoginLogService() {

            @Override
            public RespBean loadUserLoginLog(LoadLoginLogReq loadLoginLogReq) {
                return RespBean.ok("延迟导致操作失败，请稍后重试！");
            }

            @Override
            public Integer deleteLoginLogById(Integer loginLogId) {
                return 0;
            }
        };
    }
}
