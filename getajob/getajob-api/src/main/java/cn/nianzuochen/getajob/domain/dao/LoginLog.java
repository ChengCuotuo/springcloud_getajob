package cn.nianzuochen.getajob.domain.dao;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
public class LoginLog {

    /**
     * 登录日志的 id
     */
    private Integer loginId;

    /**
     * 登录用户的 id
     */
    private Integer userId;

    /**
     * 登录的用户的名字
     */
    private String cname;

    /**
     * 登录时间
     */
    private Date loginTime;

    /**
     * 登录结果
     */
    private Integer ifSuccess;

    /**
     * 登录用户的 Ip
     */
    private String loginUserIp;

    /**
     * 登录描述
     */
    private String loginDesc;

    /**
     * 是否被删除
     */
    private Integer isDeleted;
}