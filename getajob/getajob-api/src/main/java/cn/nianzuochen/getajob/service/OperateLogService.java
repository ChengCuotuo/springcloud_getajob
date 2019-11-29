package cn.nianzuochen.getajob.service;

import cn.nianzuochen.getajob.vo.request.OperateLogReq;
import cn.nianzuochen.getajob.vo.response.RespBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(value = "GETAJOBSERVICEB", fallbackFactory = OperateLogServiceFallBack.class)
public interface OperateLogService {
    @DeleteMapping("/deleteoperatelogbyid/{operateId}")
    public String deleteOperateLogByOperateLogId(@PathVariable("operateId") Integer operateId);

    @PostMapping("/loadalloperatelog")
    public RespBean loadAllOperateLog(@RequestBody OperateLogReq operateLogReq);
}
