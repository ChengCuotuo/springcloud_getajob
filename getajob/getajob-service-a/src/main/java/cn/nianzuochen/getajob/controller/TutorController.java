package cn.nianzuochen.getajob.controller;

import cn.nianzuochen.getajob.service.StudentService;
import cn.nianzuochen.getajob.service.TutorService;
import cn.nianzuochen.getajob.vo.request.SelectTutorsReq;
import cn.nianzuochen.getajob.vo.response.RespBean;
import cn.nianzuochen.getajob.vo.response.TutorListResp;
import cn.nianzuochen.getajob.domain.dao.Student;
import cn.nianzuochen.getajob.domain.dao.Tutor;
import cn.nianzuochen.getajob.domain.dto.SimpleTutor;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(value = "导师控制类")
@RestController
public class TutorController {

    @Autowired
    TutorService tutorService;

    @Autowired
    StudentService studentService;

    @GetMapping("gettutorbyid/{id}")
    public Tutor loadTutorById(@PathVariable("id") Integer id) {
        return tutorService.selectByPrimaryKey(id);
    }

    @GetMapping("gettutorbyuseruserid/{userId}")
    public RespBean loadTutorByUserId(@PathVariable("userId") Integer userId) {
        Tutor tutor = tutorService.selectByUserId(userId);
        if (tutor != null) {
            return RespBean.ok(tutor);
        } else {
            return RespBean.error("查询失败");
        }
    }

    /**
     * @Desc: 查询所有的导师信息
     * @return
     */
    @PostMapping("/getalltutors")
    public RespBean loadTutors(@RequestBody SelectTutorsReq selectTutorsReq) {
        TutorListResp tutorListResp = tutorService.selectAllTutors(selectTutorsReq);
        if (tutorListResp != null) {
            return RespBean.ok(tutorListResp);
        } else {
            return RespBean.error("查询失败");
        }
    }

    /**
     * 添加导师信息，同时需要在 userinfo 中添加基本的等待用户注册的验证信息
     * @param tutor
     * @return
     */
    @PostMapping("/addtutor")
    public RespBean addTutor(@RequestBody Tutor tutor) {
        int result = tutorService.insertSelective(tutor);
        if (result == -3){
            return RespBean.error("用户名重复！");
        } else if (result > 0){
            return RespBean.ok("添加成功！");
        } else {
            return RespBean.error("添加失败！");
        }
    }

    /**
     * 导师添加学生的时候 isDeleted 为 3 表示待审核
     * @param student
     * @return
     */
    @PostMapping("/tutoraddstudent")
    public RespBean addStudent(@RequestBody Student student) {
        return studentService.addStudent(student, 3);
    }

    /**
     * 查询出简单的导师信息
     * @return
     */
    @GetMapping("/getallsimpletutors")
    public RespBean loadSimpleTutors() {
        List<SimpleTutor> simpleTutorList = tutorService.selectAllSimpleTutors();
        return RespBean.ok(simpleTutorList);
    }

    @DeleteMapping("deletetutors")
    public RespBean deleteTutors(@RequestBody String ids) {
        String[] idArray = ids.split(",");
        if (idArray.length > 0) {
            for (int i = 0; i < idArray.length; i++) {
                if (tutorService.logicDeleteById(Integer.parseInt(idArray[i])) <= 0) {
                    return RespBean.error("删除" + idArray[i] + "失败！");
                }
            }
            return RespBean.ok("删除成功！");
        } else {
            return RespBean.ok("删除成功！");
        }
    }

    @DeleteMapping("/deletetutorbyid/{id}")
    public RespBean deleteTutorById(@PathVariable("id") Integer id) {
        int result = tutorService.logicDeleteById(id);
        if (result > 0) {
            return RespBean.ok("删除成功!");
        } else {
            return RespBean.error("删除失败！");
        }
    }

    @PostMapping("/updatetutor")
    public RespBean updateTutor(@RequestBody Tutor tutor) {
        int result = tutorService.updateByPrimaryKeySelective(tutor);
        if (result <= 0) {
            return RespBean.error("更新失败！");
        } else {
            return RespBean.ok("更新成功！");
        }
    }

    /**
     * 导出导师的信息
     * @return
     */
    @GetMapping("exporttutors")
    public ResponseEntity<byte[]> exportTutors() {
        return tutorService.exportAllEnterprise();
    }
}

