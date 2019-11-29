package cn.nianzuochen.getajob.controller;

import cn.nianzuochen.getajob.service.impl.OperateLogServiceImpl;
import cn.nianzuochen.getajob.vo.request.OperateLogReq;
import cn.nianzuochen.getajob.vo.response.OperateLogResp;
import cn.nianzuochen.getajob.vo.response.RespBean;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@Api(value = "操作日志控制类")
@RestController
public class OperateLogController {

    @Autowired
    OperateLogServiceImpl operateLogService;

    /**
     * 根据操作日志的 id 删除操作日志
     * @param operateId
     * @return
     */
    @DeleteMapping("/deleteoperatelogbyid/{operateId}")
    public String deleteOperateLogByOperateLogId(@PathVariable("operateId") Integer operateId) {
        int result = operateLogService.deleteByPrimaryKey(operateId);
        if (result != 0) {
            return "删除成功";
        } else {
            return "删除失败";
        }
    }

    /**
     * 查询所有的操作日志
     * @return
     */
    @PostMapping("/loadalloperatelog")
    public RespBean loadAllOperateLog(@RequestBody OperateLogReq operateLogReq) {
        OperateLogResp operateLogResp = operateLogService.selectAllOperateLog(operateLogReq);
        if (operateLogResp == null) {
            return RespBean.error("查询失败");
        } else {
            return RespBean.ok(operateLogResp);
        }
    }
}
