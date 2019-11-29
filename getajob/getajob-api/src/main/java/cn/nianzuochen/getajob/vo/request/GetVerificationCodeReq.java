package cn.nianzuochen.getajob.vo.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 获取邮箱验证码
 */
@Getter
@Setter
@ToString
public class GetVerificationCodeReq {
    private String identifier;
    private String username;
    private String password;
    private String repassword;
    private String vericode;
}
