package cn.nianzuochen.getajob.domain.dao;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class VerificationCode {
    private Integer userid;

    private String code;

    public VerificationCode(Integer userid, String code) {
        this.userid = userid;
        this.code = code;
    }
}