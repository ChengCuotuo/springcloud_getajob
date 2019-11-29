package cn.nianzuochen.getajob.service;

import cn.nianzuochen.getajob.vo.request.SelectEnterpriseByEnterpriseNameReq;
import cn.nianzuochen.getajob.vo.response.EnterpriseListResp;
import cn.nianzuochen.getajob.domain.dao.Enterprise;
import org.springframework.http.ResponseEntity;

public interface EnterpriseService {
    Enterprise selectByPrimaryKey(Integer id);

    EnterpriseListResp selectAllEnterPrise(SelectEnterpriseByEnterpriseNameReq selectEnterpriseByEnterpriseNameReq);

    Integer insertSelective(Enterprise record);

    Integer logicDeleteById(Integer id);

    Integer updateByPrimaryKey(Enterprise record);

    ResponseEntity<byte[]> exportAllEnterprise();
}
