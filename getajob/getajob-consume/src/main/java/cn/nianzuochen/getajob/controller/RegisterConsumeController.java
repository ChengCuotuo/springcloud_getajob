package cn.nianzuochen.getajob.controller;

import cn.nianzuochen.getajob.service.RegisterService;
import cn.nianzuochen.getajob.vo.request.GetVerificationCodeReq;
import cn.nianzuochen.getajob.vo.response.RespBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegisterConsumeController {
    @Autowired
    RegisterService registerService;

    /**
     * 1.获取邮箱验证码
     *      需要提供存入导师信息时候预存的 identifier 用户名，密码
     *      提供的 identifier 存在且对应的用户未注册同时用户名不重复，两次密码相同则获取该用户的邮箱，返送邮箱验证码
     * 2.完成注册
     *      重复上面验证，同时对应的验证码匹配，将信息存储数据库，完成注册
     */
    @PostMapping("/tutorregistersendverficationcode")
    public RespBean tutorGetVerificationCode(@RequestBody GetVerificationCodeReq codeReq) {
       return registerService.tutorGetVerificationCode(codeReq);
    }

    /**
     * 学生获取邮箱验证码
     * @param codeReq
     * @return
     */
    @PostMapping("/studentregistersendverficationcode")
    public RespBean studentGetVerificationCode(@RequestBody GetVerificationCodeReq codeReq) {
       return registerService.studentGetVerificationCode(codeReq);
    }

    @PostMapping("/tutorregister")
    public RespBean tutorRegister(@RequestBody GetVerificationCodeReq codeReq) {
      return registerService.tutorRegister(codeReq);
    }

    /**
     * 学生注册信息
     * @param codeReq
     * @return
     */
    @PostMapping("/studentregister")
    public RespBean studentRegister(@RequestBody GetVerificationCodeReq codeReq) {
       return registerService.studentRegister(codeReq);
    }
}
