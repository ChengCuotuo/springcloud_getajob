package cn.nianzuochen.getajob.domain.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 基本的导师的信息
 * 导师的 id
 * 导师的中文名 + 导师所在院校
 */
@Getter
@Setter
@ToString
public class SimpleTutor {
    private Integer id;
    private String cname;
    private String sch;
}
