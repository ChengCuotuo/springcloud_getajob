package cn.nianzuochen.getajob.controller;

import cn.nianzuochen.getajob.service.EnterpriseService;
import cn.nianzuochen.getajob.vo.request.SelectEnterpriseByEnterpriseNameReq;
import cn.nianzuochen.getajob.vo.response.EnterpriseListResp;
import cn.nianzuochen.getajob.vo.response.RespBean;
import cn.nianzuochen.getajob.domain.dao.Enterprise;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Api("企业控制器")
@RestController
public class EnterpriseController {

    @Autowired
    EnterpriseService enterpriseService;

    @GetMapping("getenterprisebyid/{id}")
    public Enterprise loadEnterpriseById(@PathVariable("id") Integer id) {
        return enterpriseService.selectByPrimaryKey(id);
    }

    @PostMapping("getallenterprise")
    public RespBean loadAllEnterPriseByEnterpriseName(@RequestBody SelectEnterpriseByEnterpriseNameReq selectEnterpriseByEnterpriseNameReq) {
        EnterpriseListResp enterpriseList = enterpriseService.selectAllEnterPrise(selectEnterpriseByEnterpriseNameReq);
        if (enterpriseList != null) {
            return RespBean.ok(enterpriseList);
        } else {
            return RespBean.error("查询失败！");
        }
    }

    @PostMapping("addenterprise")
    public RespBean addEnterprise(@RequestBody Enterprise enterprise) {
        Integer result = enterpriseService.insertSelective(enterprise);
        if (result == 1) {
            return RespBean.ok("添加成功！");
        } else {
            return RespBean.error("添加失败");
        }
    }

    /**
     * 批量删除企业信息
     * @param ids
     * @return
     */
    @DeleteMapping("deleteenterprises")
    public RespBean deleteEnterprises(@RequestBody String ids) {
        String[] idArray = ids.split(",");
        if (idArray.length > 0) {
            for (int i = 0; i < idArray.length; i++) {
                if (enterpriseService.logicDeleteById(Integer.parseInt(idArray[i])) <= 0) {
                    return RespBean.error("删除" + idArray[i] + "失败！");
                }
            }
            return RespBean.ok("删除成功！");
        } else {
            return RespBean.ok("删除成功！");
        }
    }

    @DeleteMapping("/deleteenterprisebyid/{id}")
    public RespBean deleteEnterpriseById(@PathVariable("id") Integer id) {
        int result = enterpriseService.logicDeleteById(id);
        if (result <= 0) {
            return RespBean.error("删除失败！");
        } else {
            return RespBean.ok("删除成功！");
        }
    }

    @PostMapping("/updateenterprise")
    public RespBean updateEnterprise(@RequestBody Enterprise enterprise) {
        int result = enterpriseService.updateByPrimaryKey(enterprise);
        if (result <= 0) {
            return RespBean.error("更新失败！");
        } else {
            return RespBean.ok("跟新成功！");
        }
    }

    /**
     * 导出所有企业的信息到 excel
     * @return
     */
    @GetMapping("exportenterprises")
    public ResponseEntity<byte[]> exportEnterprise() {
        return enterpriseService.exportAllEnterprise();
    }
}
