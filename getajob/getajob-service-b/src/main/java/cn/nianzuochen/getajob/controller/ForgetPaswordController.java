

package cn.nianzuochen.getajob.controller;

import cn.nianzuochen.getajob.service.VerificationCodeService;
import cn.nianzuochen.getajob.vo.request.GetVerificationCodeReq;
import cn.nianzuochen.getajob.vo.response.RespBean;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Api("重置密码")
@RestController
public class ForgetPaswordController {

    @Autowired
    VerificationCodeService verificationCodeService;

    @PostMapping("/tutorsendverficationcode")
    public RespBean tutorGetVerificationCode(@RequestBody GetVerificationCodeReq codeReq) {
        int result = verificationCodeService.tutorSendForgetPasswordVerificationCodeReq(codeReq);
        RespBean respBean;
        if (result == -1) {
            respBean = RespBean.error("网站验证码不存在");
        } else if (result == -2) {
            respBean = RespBean.error("用户名错误");
        } else if (result == -3) {
            respBean = RespBean.error("密码不能为空");
        } else if (result == -4) {
            respBean = RespBean.error("邮箱为空请联系管理员");
        } else if (result == -5) {
            respBean = RespBean.error("邮箱格式错误");
        }else {
            respBean = RespBean.ok("验证码已发送至您的邮箱");
        }
        return respBean;
    }

    /**
     * 学生忘记密码发送验证码
     * @param codeReq
     * @return
     */
    @PostMapping("/studentsendverficationcode")
    public RespBean studentGetVerificationCode(@RequestBody GetVerificationCodeReq codeReq) {
        int result = verificationCodeService.studentSendForgetPasswordVerificationCodeReq(codeReq);
        RespBean respBean;
        if (result == -1) {
            respBean = RespBean.error("网站验证码不存在");
        } else if (result == -2) {
            respBean = RespBean.error("用户名错误");
        } else if (result == -3) {
            respBean = RespBean.error("密码不能为空");
        } else if (result == -4) {
            respBean = RespBean.error("邮箱为空请联系管理员");
        } else if (result == -5) {
            respBean = RespBean.error("邮箱格式错误");
        }else {
            respBean = RespBean.ok("验证码已发送至您的邮箱");
        }
        return respBean;
    }

    /**
     * @param codeReq
     * @return
     */
    @PostMapping("/updatepassword")
    public RespBean changeTutorPassword(@RequestBody GetVerificationCodeReq codeReq) {
        int result = verificationCodeService.updatePassword(codeReq);
        RespBean respBean;
        if (result == -1) {
            respBean = RespBean.error("网站验证码不存在");
        } else if (result == -2) {
            respBean = RespBean.error("用户名错误");
        } else if (result == -3) {
            respBean = RespBean.error("密码不能为空");
        }else if (result == -4) {
            respBean = RespBean.error("邮箱验证码错误");
        } else {
            respBean = RespBean.ok("验证码已发送至您的邮箱");
        }

        return  respBean;
    }
}

