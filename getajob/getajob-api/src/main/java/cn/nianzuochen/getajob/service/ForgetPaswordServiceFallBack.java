package cn.nianzuochen.getajob.service;

import cn.nianzuochen.getajob.vo.request.GetVerificationCodeReq;
import cn.nianzuochen.getajob.vo.response.RespBean;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

@Component
public class ForgetPaswordServiceFallBack implements FallbackFactory<ForgetPaswordService> {
    @Override
    public ForgetPaswordService create(Throwable throwable) {
        return new ForgetPaswordService() {
            @Override
            public RespBean tutorGetVerificationCode(GetVerificationCodeReq codeReq) {
                return RespBean.ok("延迟导致操作失败，请稍后重试！");
            }

            @Override
            public RespBean studentGetVerificationCode(GetVerificationCodeReq codeReq) {
                return RespBean.ok("延迟导致操作失败，请稍后重试！");
            }

            @Override
            public RespBean changeTutorPassword(GetVerificationCodeReq codeReq) {
                return RespBean.ok("延迟导致操作失败，请稍后重试！");
            }
        };
    }
}
