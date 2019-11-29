package cn.nianzuochen.getajob.service;

import cn.nianzuochen.getajob.vo.request.GetVerificationCodeReq;
import cn.nianzuochen.getajob.vo.response.RespBean;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

@Component
public class RegisterServiceFallBack implements FallbackFactory<RegisterService> {
    @Override
    public RegisterService create(Throwable throwable) {
        return new RegisterService() {
            @Override
            public RespBean tutorGetVerificationCode(GetVerificationCodeReq codeReq) {
                return RespBean.error("延迟导致操作失败，请稍后重试！");
            }

            @Override
            public RespBean studentGetVerificationCode(GetVerificationCodeReq codeReq) {
                return RespBean.error("延迟导致操作失败，请稍后重试！");
            }

            @Override
            public RespBean tutorRegister(GetVerificationCodeReq codeReq) {
                return RespBean.error("延迟导致操作失败，请稍后重试！");
            }

            @Override
            public RespBean studentRegister(GetVerificationCodeReq codeReq) {
                return RespBean.error("延迟导致操作失败，请稍后重试！");
            }
        };
    }
}
