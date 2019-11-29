package cn.nianzuochen.getajob.service;

import cn.nianzuochen.getajob.domain.dao.Enterprise;
import cn.nianzuochen.getajob.vo.request.SelectEnterpriseByEnterpriseNameReq;
import cn.nianzuochen.getajob.vo.response.RespBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@FeignClient(value = "GETAJOBSERVICEA", fallbackFactory = EnterpriseClientServiceFallBack.class)
public interface EnterpriseClientService {
    @GetMapping("getenterprisebyid/{id}")
    public Enterprise loadEnterpriseById(@PathVariable("id") Integer id);

    @PostMapping("getallenterprise")
    public RespBean loadAllEnterPriseByEnterpriseName(@RequestBody SelectEnterpriseByEnterpriseNameReq selectEnterpriseByEnterpriseNameReq);

    @PostMapping("addenterprise")
    public RespBean addEnterprise(@RequestBody Enterprise enterprise);

    @DeleteMapping("deleteenterprises")
    public RespBean deleteEnterprises(@RequestBody String ids);

    @DeleteMapping("/deleteenterprisebyid/{id}")
    public RespBean deleteEnterpriseById(@PathVariable("id") Integer id);

    @PostMapping("/updateenterprise")
    public RespBean updateEnterprise(@RequestBody Enterprise enterprise);

    @GetMapping("exportenterprises")
    public ResponseEntity<byte[]> exportEnterprise();
}
