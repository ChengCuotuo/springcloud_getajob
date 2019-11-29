package cn.nianzuochen.getajob.mapper;

import cn.nianzuochen.getajob.vo.request.SelectEnterpriseByEnterpriseNameReq;
import cn.nianzuochen.getajob.domain.dao.Enterprise;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface EnterpriseMapper {
    int deleteByPrimaryKey(Integer id);

    int logicDeleteById(Integer id);

    int insert(Enterprise record);

    int insertSelective(Enterprise record);

    Enterprise selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Enterprise record);

    int updateByPrimaryKey(Enterprise record);

    List<Enterprise> selectAllEnterprises(@Param("basicReq") SelectEnterpriseByEnterpriseNameReq selectEnterpriseByEnterpriseNameReq);

    Integer countEnterPrises(@Param("basicReq") SelectEnterpriseByEnterpriseNameReq selectEnterpriseByEnterpriseNameReq);
}