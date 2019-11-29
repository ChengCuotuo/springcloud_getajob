package cn.nianzuochen.getajob.vo.response;

import cn.nianzuochen.getajob.domain.dao.Enterprise;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class EnterpriseListResp {
    private List<Enterprise> enterpriseList;
    private Integer totalCount;
}
