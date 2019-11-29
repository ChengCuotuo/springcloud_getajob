package cn.nianzuochen.getajob.controller;

import cn.nianzuochen.getajob.service.OperateLogService;
import cn.nianzuochen.getajob.vo.request.OperateLogReq;
import cn.nianzuochen.getajob.vo.response.RespBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/system/operatelog")
public class OperateLogConsumeController {

    @Autowired
    OperateLogService operateLogService;

    /**
     * 根据操作日志的 id 删除操作日志
     * @param operateId
     * @return
     */
    @DeleteMapping("/deleteoperatelogbyid/{operateId}")
    public String deleteOperateLogByOperateLogId(@PathVariable("operateId") Integer operateId) {
        return operateLogService.deleteOperateLogByOperateLogId(operateId);
    }

    /**
     * 查询所有的操作日志
     * @return
     */
    @PostMapping("/loadalloperatelog")
    public RespBean loadAllOperateLog(@RequestBody OperateLogReq operateLogReq) {
        return operateLogService.loadAllOperateLog(operateLogReq);
    }
}
