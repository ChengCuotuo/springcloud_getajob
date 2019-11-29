package cn.nianzuochen.getajob.service;

import cn.nianzuochen.getajob.vo.request.OperateLogReq;
import cn.nianzuochen.getajob.vo.response.RespBean;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

@Component
public class OperateLogServiceFallBack implements FallbackFactory<OperateLogService> {
    @Override
    public OperateLogService create(Throwable throwable) {
        return new OperateLogService() {
            @Override
            public String deleteOperateLogByOperateLogId(Integer operateId) {
                return "延迟导致操作失败，请稍后重试！";
            }

            @Override
            public RespBean loadAllOperateLog(OperateLogReq operateLogReq) {
                return RespBean.ok("延迟导致操作失败，请稍后重试！");
            }
        };
    }
}
