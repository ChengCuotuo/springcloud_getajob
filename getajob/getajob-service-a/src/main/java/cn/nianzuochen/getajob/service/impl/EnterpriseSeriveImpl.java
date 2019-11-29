package cn.nianzuochen.getajob.service.impl;

import cn.nianzuochen.getajob.domain.dao.Enterprise;
import cn.nianzuochen.getajob.mapper.EnterpriseMapper;
import cn.nianzuochen.getajob.service.EnterpriseService;
import cn.nianzuochen.getajob.utils.Export2Excel;
import cn.nianzuochen.getajob.vo.request.SelectEnterpriseByEnterpriseNameReq;
import cn.nianzuochen.getajob.vo.response.EnterpriseListResp;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnterpriseSeriveImpl implements EnterpriseService {

    @Autowired
    EnterpriseMapper enterpriseMapper;

    @Override
    public Enterprise selectByPrimaryKey(Integer id) {
        return enterpriseMapper.selectByPrimaryKey(id);
    }

    @Override
    public EnterpriseListResp selectAllEnterPrise(SelectEnterpriseByEnterpriseNameReq selectEnterpriseByEnterpriseNameReq) {
        EnterpriseListResp resp = new EnterpriseListResp();
        List<Enterprise> enterprises = enterpriseMapper.selectAllEnterprises(selectEnterpriseByEnterpriseNameReq);
        resp.setEnterpriseList(enterprises);
        resp.setTotalCount(enterpriseMapper.countEnterPrises(selectEnterpriseByEnterpriseNameReq));

        return resp;
    }

    @Override
    public Integer insertSelective(Enterprise record) {
        return enterpriseMapper.insertSelective(record);
    }

    @Override
    public Integer logicDeleteById(Integer id) {
        return enterpriseMapper.logicDeleteById(id);
    }

    /**
     * 更新企业信息
     * @param record
     * @return
     */
    @Override
    public Integer updateByPrimaryKey(Enterprise record) {
        return enterpriseMapper.updateByPrimaryKey(record);
    }

    /**
     * 导出所有企业的信息
     * @return
     */
    @Override
    public ResponseEntity<byte[]> exportAllEnterprise() {
        List<Enterprise> enterpriseList =
                enterpriseMapper.selectAllEnterprises(new SelectEnterpriseByEnterpriseNameReq());
        Export2Excel export2Excel = new Export2Excel("enterprise");
        HSSFSheet sheet = export2Excel.createSheet("enterprise");
        return export2Excel.addInfo(sheet, enterpriseList);
    }
}
