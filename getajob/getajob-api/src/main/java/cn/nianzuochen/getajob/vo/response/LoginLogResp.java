package cn.nianzuochen.getajob.vo.response;

import cn.nianzuochen.getajob.domain.dao.LoginLog;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * @Author: chunlei.wang
 * @Date: 2018/09/05
 */

@Getter
@Setter
@ToString
public class LoginLogResp {
    private List<LoginLog> loginLogList;
    private Integer totalCount;
}
