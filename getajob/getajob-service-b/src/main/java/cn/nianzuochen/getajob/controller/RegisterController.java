package cn.nianzuochen.getajob.controller;

import cn.nianzuochen.getajob.service.VerificationCodeService;
import cn.nianzuochen.getajob.vo.request.GetVerificationCodeReq;
import cn.nianzuochen.getajob.vo.response.RespBean;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Api("/注册控制器类")
@RestController
public class RegisterController {
    @Autowired
    VerificationCodeService verificationCodeService;

    /**
     * 1.获取邮箱验证码
     *      需要提供存入导师信息时候预存的 identifier 用户名，密码
     *      提供的 identifier 存在且对应的用户未注册同时用户名不重复，两次密码相同则获取该用户的邮箱，返送邮箱验证码
     * 2.完成注册
     *      重复上面验证，同时对应的验证码匹配，将信息存储数据库，完成注册
     */
    @PostMapping("/tutorregistersendverficationcode")
    public RespBean tutorGetVerificationCode(@RequestBody GetVerificationCodeReq codeReq) {
        int result = verificationCodeService.tutorSendRegisterVerificationCodeReq(codeReq);
        RespBean respBean;
        if (result < 0) {
            respBean = this.beforeReister(result);
        } else {
            respBean = RespBean.ok("验证码已发送至您的邮箱");
        }
        return respBean;
    }

    /**
     * 学生获取邮箱验证码
     * @param codeReq
     * @return
     */
    @PostMapping("/studentregistersendverficationcode")
    public RespBean studentGetVerificationCode(@RequestBody GetVerificationCodeReq codeReq) {
        int result = verificationCodeService.studentSendRegisterVerificationCodeReq(codeReq);
        RespBean respBean;
        if (result < 0) {
            respBean = this.beforeReister(result);
        } else {
            respBean = RespBean.ok("验证码已发送至您的邮箱");
        }
        return respBean;
    }

    private RespBean beforeReister(int errorCode) {
        RespBean respBean = RespBean.build();
        switch (errorCode) {
            case -1:
                respBean = RespBean.error("网站验证码不存在");
                break;
            case -2:
                respBean = RespBean.error("已注册，请勿重复注册");
                break;
            case -3:
                respBean = RespBean.error("用户名重复");
                break;
            case -4:
                respBean = RespBean.error("密码不能为空");
                break;
            case -5:
                respBean = RespBean.error("两次输入密码不同");
                break;
            case -6:
                respBean = RespBean.error("邮箱错误，请联系管理员");
                break;
            case -7:
                respBean = RespBean.error("邮箱验证码错误，请核对验证码");
                break;
        }
        return respBean;
    }

    @PostMapping("/tutorregister")
    public RespBean tutorRegister(@RequestBody GetVerificationCodeReq codeReq) {
        int result = verificationCodeService.tutorRegister(codeReq);
        RespBean respBean;
        if (result < 0) {
            respBean = this.beforeReister(result);
        } else {
            respBean = RespBean.ok("注册成功");
        }

        return respBean;
    }

    /**
     * 学生注册信息
     * @param codeReq
     * @return
     */
    @PostMapping("/studentregister")
    public RespBean studentRegister(@RequestBody GetVerificationCodeReq codeReq) {
        int result = verificationCodeService.studentRegister(codeReq);
        RespBean respBean;
        if (result < 0) {
            respBean = this.beforeReister(result);
        } else {
            respBean = RespBean.ok("注册成功");
        }

        return respBean;
    }
}
