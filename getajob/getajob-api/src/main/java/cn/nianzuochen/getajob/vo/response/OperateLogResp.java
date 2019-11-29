package cn.nianzuochen.getajob.vo.response;

import cn.nianzuochen.getajob.domain.dao.OperateLog;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class OperateLogResp {
    private List<OperateLog> operateLogList;
    private Integer totalCount;
}
