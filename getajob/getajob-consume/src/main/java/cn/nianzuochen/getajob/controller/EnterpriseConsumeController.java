package cn.nianzuochen.getajob.controller;

import cn.nianzuochen.getajob.domain.dao.Enterprise;
import cn.nianzuochen.getajob.service.EnterpriseClientService;
import cn.nianzuochen.getajob.vo.request.SelectEnterpriseByEnterpriseNameReq;
import cn.nianzuochen.getajob.vo.response.RespBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/enterprise")
public class EnterpriseConsumeController {

    @Autowired
    private EnterpriseClientService enterpriseService;

    @GetMapping("/getenterprisebyid/{id}")
    public Enterprise loadEnterpriseById(@PathVariable("id") Integer id) {
        return enterpriseService.loadEnterpriseById(id);
    }

    @PostMapping("/getallenterprise")
    public RespBean loadAllEnterPriseByEnterpriseName(@RequestBody SelectEnterpriseByEnterpriseNameReq selectEnterpriseByEnterpriseNameReq) {
        return enterpriseService.loadAllEnterPriseByEnterpriseName(selectEnterpriseByEnterpriseNameReq);
    }

    @PostMapping("/addenterprise")
    public RespBean addEnterprise(@RequestBody Enterprise enterprise) {
        return enterpriseService.addEnterprise(enterprise);
    }

    /**
     * 批量删除企业信息
     * @param ids
     * @return
     */
    @DeleteMapping("/deleteenterprises")
    public RespBean deleteEnterprises(@RequestBody String ids) {
       return enterpriseService.deleteEnterprises(ids);
    }

    @DeleteMapping("/deleteenterprisebyid/{id}")
    public RespBean deleteEnterpriseById(@PathVariable("id") Integer id) {
       return enterpriseService.deleteEnterpriseById(id);
    }

    @PostMapping("/updateenterprise")
    public RespBean updateEnterprise(@RequestBody Enterprise enterprise) {
        return enterpriseService.updateEnterprise(enterprise);
    }

    /**
     * 导出所有企业的信息到 excel
     * @return
     */
    @GetMapping("/exportenterprises")
    public ResponseEntity<byte[]> exportEnterprise() {
       return enterpriseService.exportEnterprise();
    }
}
