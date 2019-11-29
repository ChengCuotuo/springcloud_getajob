package cn.nianzuochen.getajob.vo.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

/**
 * @Author: xinggui.xia
 * @Date: 2018/08/21
 */
@Getter
@Setter
@ToString
public class LoadLoginLogReq {

    /**
     * 搜索的开始时间
     */
    private Date startDate;

    /**
     * 搜索结束时间
     */
    private Date endDate;

    /**
     * 搜索的类型：
     *  0 表示没有特殊限定
     *  1 搜索本日
     *  2 搜索本周
     *  3 搜索本月
     */
    private Integer limit;

    private Integer start;

    private Integer size;
}
