package cn.nianzuochen.getajob.service;

import cn.nianzuochen.getajob.vo.request.GetVerificationCodeReq;

public interface VerificationCodeService {
    public int tutorSendRegisterVerificationCodeReq(GetVerificationCodeReq codeReq);

    public int tutorRegister(GetVerificationCodeReq codeReq);

    public int tutorSendForgetPasswordVerificationCodeReq(GetVerificationCodeReq codeReq);

    public int studentSendRegisterVerificationCodeReq(GetVerificationCodeReq codeReq);

    public int studentRegister(GetVerificationCodeReq codeReq);

    public int studentSendForgetPasswordVerificationCodeReq(GetVerificationCodeReq codeReq);

    public int updatePassword(GetVerificationCodeReq codeReq);
}
