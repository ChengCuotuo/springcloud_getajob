

package cn.nianzuochen.getajob.controller;

import cn.nianzuochen.getajob.service.ForgetPaswordService;
import cn.nianzuochen.getajob.service.RegisterService;
import cn.nianzuochen.getajob.vo.request.GetVerificationCodeReq;
import cn.nianzuochen.getajob.vo.response.RespBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/forgetpassword")
public class ForgetPaswordConsumeController {

    @Autowired
    ForgetPaswordService forgetPaswordService;

    @Autowired
    RegisterService registerService;

    @PostMapping("/tutorsendverficationcode")
    public RespBean tutorGetVerificationCode(@RequestBody GetVerificationCodeReq codeReq) {
        return registerService.tutorGetVerificationCode(codeReq);
    }

    /**
     * 学生忘记密码发送验证码
     * @param codeReq
     * @return
     */
    @PostMapping("/studentsendverficationcode")
    public RespBean studentGetVerificationCode(@RequestBody GetVerificationCodeReq codeReq) {
        return forgetPaswordService.studentGetVerificationCode(codeReq);
    }

    /**
     * @param codeReq
     * @return
     */
    @PostMapping("/updatepassword")
    public RespBean changeTutorPassword(@RequestBody GetVerificationCodeReq codeReq) {
        return  forgetPaswordService.changeTutorPassword(codeReq);
    }
}

