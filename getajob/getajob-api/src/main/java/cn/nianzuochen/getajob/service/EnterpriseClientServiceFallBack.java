package cn.nianzuochen.getajob.service;

import cn.nianzuochen.getajob.domain.dao.Enterprise;
import cn.nianzuochen.getajob.vo.request.SelectEnterpriseByEnterpriseNameReq;
import cn.nianzuochen.getajob.vo.response.EnterpriseListResp;
import cn.nianzuochen.getajob.vo.response.RespBean;
import feign.hystrix.FallbackFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class EnterpriseClientServiceFallBack implements FallbackFactory<EnterpriseClientService> {
    @Override
    public EnterpriseClientService create(Throwable throwable) {
        return new EnterpriseClientService() {
            @Override
            public Enterprise loadEnterpriseById(Integer id) {
                Enterprise enterprise = new Enterprise();
                enterprise.setId(id);
                enterprise.setName("延迟导致操作失败，请稍后重试！");
                return enterprise;
            }

            @Override
            public RespBean loadAllEnterPriseByEnterpriseName(SelectEnterpriseByEnterpriseNameReq selectEnterpriseByEnterpriseNameReq) {
                EnterpriseListResp enterpriseListResp = new EnterpriseListResp();
                enterpriseListResp.setTotalCount(0);
                return RespBean.ok(enterpriseListResp);
            }

            @Override
            public RespBean addEnterprise(Enterprise enterprise) {
                return RespBean.error("延迟导致操作失败，请稍后重试！");
            }

            @Override
            public RespBean deleteEnterprises(String ids) {
                return RespBean.ok("延迟导致操作失败，请稍后重试！");
            }

            @Override
            public RespBean deleteEnterpriseById(Integer id) {
                return RespBean.ok("延迟导致操作失败，请稍后重试！");
            }

            @Override
            public RespBean updateEnterprise(Enterprise enterprise) {
                return RespBean.ok("延迟导致操作失败，请稍后重试！");
            }

            @Override
            public ResponseEntity<byte[]> exportEnterprise() {
                return null;
            }
        };
    }
}
