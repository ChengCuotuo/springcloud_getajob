package cn.nianzuochen.getajob.service;

import cn.nianzuochen.getajob.vo.request.GetVerificationCodeReq;
import cn.nianzuochen.getajob.vo.response.RespBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "GETAJOBSERVICEB", fallbackFactory = RegisterServiceFallBack.class)
public interface RegisterService {
    @PostMapping("/tutorregistersendverficationcode")
    public RespBean tutorGetVerificationCode(@RequestBody GetVerificationCodeReq codeReq);

    @PostMapping("/studentregistersendverficationcode")
    public RespBean studentGetVerificationCode(@RequestBody GetVerificationCodeReq codeReq);

    @PostMapping("/tutorregister")
    public RespBean tutorRegister(@RequestBody GetVerificationCodeReq codeReq);

    @PostMapping("/studentregister")
    public RespBean studentRegister(@RequestBody GetVerificationCodeReq codeReq);
}
