package cn.nianzuochen.getajob.service;

import cn.nianzuochen.getajob.vo.request.GetVerificationCodeReq;
import cn.nianzuochen.getajob.vo.response.RespBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "GETAJOBSERVICEB", fallbackFactory = ForgetPaswordServiceFallBack.class)
public interface ForgetPaswordService {
    @PostMapping("/tutorsendverficationcode")
    public RespBean tutorGetVerificationCode(@RequestBody GetVerificationCodeReq codeReq);

    @PostMapping("/studentsendverficationcode")
    public RespBean studentGetVerificationCode(@RequestBody GetVerificationCodeReq codeReq);

    @PostMapping("/updatepassword")
    public RespBean changeTutorPassword(@RequestBody GetVerificationCodeReq codeReq);
}
