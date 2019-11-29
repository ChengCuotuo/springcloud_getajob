package cn.nianzuochen.getajob.aop;

import cn.nianzuochen.getajob.domain.dao.Enterprise;
import cn.nianzuochen.getajob.domain.dao.OperateLog;
import cn.nianzuochen.getajob.domain.dao.Student;
import cn.nianzuochen.getajob.domain.dao.Tutor;
import cn.nianzuochen.getajob.service.impl.OperateLogServiceImpl;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * ToDo 操作日志需要重新命名，对做细致的处理
 */
@Aspect
@Component
public class OperationLogAspect {

    private OperateLog operateLog = new OperateLog();

    @Autowired
    OperateLogServiceImpl operateLogService;

    /**
     * 存储当前登录用户的 id 和 cname
     */
    private static Map<Integer, String> user = new HashMap<>();

    /**
     * 用户的信息由 config 中在用户正确登录之后设置
     * @param u
     * @return
     */
    public static Map<Integer, String> setUserInfo(Map<Integer, String> u) {
        user = u;
        return user;
    }

    /**
     * 切点：添加信息
     */
    @Pointcut("execution(public * cn.nianzuochen.getajob.controller.*.add*(..))")
    public void addCell() {

    }

    /**
     * 切点：删除信息
     */
    @Pointcut("execution(public * cn.nianzuochen.getajob.controller.*.delete*(..))")
    public void deleteCell() {

    }

    /**
     * 切点：更新信息
     */
    @Pointcut("execution(public * cn.nianzuochen.getajob.controller.*.update*(..))")
    public void updateCell() {

    }

    /**
     * 切点：将导师推荐学生入库信息
     */
    @Pointcut("execution(public * cn.nianzuochen.getajob.controller.*.pass*(..))")
    public void passCell() {

    }

    /**
     * 后置增强：添加信息
     * @param joinPoint
     * @param result
     */
    @AfterReturning(value = "addCell()", returning = "result")
    public void addLog(JoinPoint joinPoint, Object result) {
        // 获取方法名称
        String methodName = joinPoint.getSignature().getName();
        // 获取参数内容，企业、导师、学生的添加信息都只有一个参数
        Object arg = joinPoint.getArgs()[0];
        // 需要插入到数据库中的信息
        StringBuilder info = new StringBuilder("method: " + methodName + ", ");
        // 添加的信息是学生
        if (arg instanceof Student) {
            info.append("添加学生：")
                    .append(((Student)arg).getCname());
        } else if (arg instanceof Tutor) {
            info.append("添加导师：")
                    .append(((Tutor)arg).getCname());
        } else if (arg instanceof Enterprise) {
            info.append("添加企业：")
                    .append(((Enterprise)arg).getName());
        }
        this.insertToDatabase(methodName, info.toString());
    }

    /**
     * 后置增强：删除信息
     * @param joinPoint
     * @param result
     */
    @AfterReturning(value = "deleteCell()", returning = "result")
    public void deleteLog(JoinPoint joinPoint, Object result) {
        // 获取方法名称
        String methodName = joinPoint.getSignature().getName();
        // 获取参数内容，企业、导师、学生的添加信息都只有一个参数
        Object arg = joinPoint.getArgs()[0];
        StringBuilder info = new StringBuilder("method: " + methodName + ", ");
        // 判断是删除的哪种信息
        if (methodName.indexOf("Enterprise") > 0) {
            info.append("删除了企业：");
        } else if (methodName.indexOf("Tutor") > 0) {
            info.append("删除了导师：");
        } else if (methodName.indexOf("Student") > 0) {
            info.append("删除了学生：");
        }
        // 处理传递的参数
        if (arg instanceof String) {
            info.append((String)arg);
        } else if (arg instanceof Integer) {
            info.append((Integer)arg);
        }

        this.insertToDatabase(methodName, info.toString());
    }

    /**
     * 后置增强：更新信息
     * @param joinPoint
     * @param result
     */
    @AfterReturning(value = "updateCell()", returning = "result")
    public void updateLog(JoinPoint joinPoint, Object result) {
        // 获取方法名称
        String methodName = joinPoint.getSignature().getName();
        // 获取参数内容，企业、导师、学生的添加信息都只有一个参数
        Object arg = joinPoint.getArgs()[0];
        StringBuilder info = new StringBuilder("method: " + methodName + ", ");
        // 判断是删除的哪种信息
        if (arg instanceof Student) {
            info.append("更新学生：")
                    .append(((Student)arg).getId() + "-")
                    .append(((Student)arg).getCname());
        } else if (arg instanceof Tutor) {
            info.append("更新导师：")
                    .append(((Tutor)arg).getId() + "-")
                    .append(((Tutor)arg).getCname());
        } else if (arg instanceof Enterprise) {
            info.append("更新企业：")
                    .append(((Enterprise)arg).getId() + "-")
                    .append(((Enterprise)arg).getName());
        }
        this.insertToDatabase(methodName, info.toString());
    }

    /**
     * 后置增强：更新信息
     * @param joinPoint
     * @param result
     */
    @AfterReturning(value = "passCell()", returning = "result")
    public void passLog(JoinPoint joinPoint, Object result) {
        // 获取方法名称
        String methodName = joinPoint.getSignature().getName();
        // 获取参数内容学生的编号
        Object arg = joinPoint.getArgs()[0];
        StringBuilder info = new StringBuilder("method: " + methodName + ", ");
        info.append("允许入库：")
                .append((Integer)arg);
        this.insertToDatabase(methodName, info.toString());
    }

    /**
     * 向数据库中插入操作日志，将重复的内容提取出来
     * @param operateName
     * @param infos
     */
    public void insertToDatabase(String operateName, String infos) {
        Integer userId = (Integer) OperationLogAspect.user.keySet().toArray()[0];
        String cname = OperationLogAspect.user.get(userId);

        operateLog.setUserId(userId);
        operateLog.setCname(cname);
        operateLog.setOperateName(operateName);
        operateLog.setOperateDesc("执行的操作是：" + infos);
        operateLog.setOperateTime(new Date());
        operateLogService.insert(operateLog);
    }

}
