package cn.nianzuochen.getajob.domain.dao;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class UserInfo {

    /**
     * 用户的唯一标识
     */
    private Integer userid;

    /**
     * 验证标识
     */
    @JsonIgnore
    private String identifier;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    @JsonIgnore
    private String password;

    /**
     * 中文名
     */
    private String cname;

    /**
     * 用户的角色
     */
    private Integer roleid;

    /**
     * 用户的权限（允许访问的菜单），不是 RoleRight 因为向前端显示的时候，给的就是当前登录对象可以执行的模块
     */
    private List<RoleInfo> roleInfos;

}