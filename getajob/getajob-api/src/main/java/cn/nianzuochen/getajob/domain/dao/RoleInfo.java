package cn.nianzuochen.getajob.domain.dao;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class RoleInfo {
    private Integer roleid;

    private String rolename;

    private String roledesc;

    /**
     * 每种用户可以访问的菜单
     */
    private List<SysFun> sysFuns;
}