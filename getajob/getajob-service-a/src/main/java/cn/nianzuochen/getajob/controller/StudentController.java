package cn.nianzuochen.getajob.controller;

import cn.nianzuochen.getajob.service.StudentService;
import cn.nianzuochen.getajob.vo.request.SelectStudentsByTIdReq;
import cn.nianzuochen.getajob.vo.request.SelectStudentsReq;
import cn.nianzuochen.getajob.vo.response.RespBean;
import cn.nianzuochen.getajob.vo.response.StudentListResp;
import cn.nianzuochen.getajob.domain.dao.Student;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Api(value = "学生控制类")
@RestController
public class StudentController {

    @Autowired
    StudentService studentService;

    @GetMapping("/getstudentbyid/{id}")
    public Student loadStudentById(@PathVariable("id") Integer id) {
        return studentService.selectByPrimaryKey(id);
    }

    @GetMapping("/getstudentbyuserid/{userId}")
    public RespBean loadStudentByUserId(@PathVariable("userId") Integer userId) {
        Student student = studentService.selectByUserId(userId);
        if (student != null) {
            return RespBean.ok(student);
        } else {
            return RespBean.error("查询失败");
        }
    }

    // ToDo 查询信息有两类：没有被删除的有已经入库的
    /**
     * @Desc: 查询所有的学生信息
     * @return
     */
    @PostMapping("/getallstudents")
    public RespBean loadStudents(@RequestBody SelectStudentsReq selectStudentsReq) {
        StudentListResp studentListResp = studentService.selectAllStudents(selectStudentsReq);
        if (studentListResp != null) {
            return RespBean.ok(studentListResp);
        } else {
            return RespBean.error("查询失败！");
        }
    }

    /**
     * 根据导师的编号查询对应的学生
     * @param selectStudentsByTIdReq
     * @return
     */
    @PostMapping("/getstudentsbytid")
    public RespBean loadStudentsByTId(@RequestBody SelectStudentsByTIdReq selectStudentsByTIdReq) {
        StudentListResp studentListResp = studentService.selectStudentsByTutorId(selectStudentsByTIdReq);
        if (studentListResp != null) {
            return RespBean.ok(studentListResp);
        } else {
            return RespBean.error("查询失败！");
        }
    }

    /**
     * 查询所有未入库的学生
     * @param selectStudentsByTIdReq
     * @return
     */
    @PostMapping("/getallnotpassedstudents")
    public RespBean loadAllNotPassedStudent(@RequestBody SelectStudentsByTIdReq selectStudentsByTIdReq) {
        System.out.println(selectStudentsByTIdReq.getTId() + " " +
                selectStudentsByTIdReq.getStart() + " " +
                selectStudentsByTIdReq.getSize());
        StudentListResp studentListResp = studentService.selectAllNotPassStudents(selectStudentsByTIdReq);
        if (studentListResp != null) {
            return RespBean.ok(studentListResp);
        } else {
            return RespBean.error("查询失败！");
        }
    }

    /**
     * 获取还没有入库的学生的人数
     * @return
     */
    @GetMapping("/getnotpassedstudentsnumber")
    public RespBean loadNotPassedStudentNumber() {
        Integer number = studentService.selectNotPassedStudentNumber();
        return RespBean.ok(number);
    }

    /**
     * 查询所有未注册的学生
     * @param selectStudentsByTIdReq
     * @return
     */
    @PostMapping("/getallnotregisterstudents")
    public RespBean loadAllNotRegisterStudent(@RequestBody SelectStudentsByTIdReq selectStudentsByTIdReq) {
        StudentListResp studentListResp = studentService.selectAllNotRegisterStudents(selectStudentsByTIdReq);
        if (studentListResp != null) {
            return RespBean.ok(studentListResp);
        } else {
            return RespBean.error("查询失败！");
        }
    }

    /**
     * 查询所有已注册的学生
     * @param selectStudentsByTIdReq
     * @return
     */
    @PostMapping("/getallregistedstudents")
    public RespBean loadAllRegistedStudent(@RequestBody SelectStudentsByTIdReq selectStudentsByTIdReq) {
        StudentListResp studentListResp = studentService.selectAllRegistedStudents(selectStudentsByTIdReq);
        if (studentListResp != null) {
            return RespBean.ok(studentListResp);
        } else {
            return RespBean.error("查询失败！");
        }
    }

    /**
     * 管理员添加学生的时候 isDeleted 表示已经审核通过
     * @param student
     * @return
     */
    @PostMapping("/addstudent")
    public RespBean addStudent(@RequestBody Student student) {
        return studentService.addStudent(student, 2);
    }

    /**
     * 根据提供的 id 将将学生设置为已通过审核，即 isDekted = 2
     * @param id
     * @return
     */
    @GetMapping("/passstudent/{id}")
    public RespBean passStudent(@PathVariable("id") Integer id){
        Student student = studentService.selectByPrimaryKey(id);
        if (student == null) {
            return RespBean.error("该用户不存在");
        } else {
            studentService.passStudent(id);
            return RespBean.ok("该学生已入库");
        }
    }

    @DeleteMapping("deletestudents")
    public RespBean deleteStudents(@RequestBody String ids) {
        String[] idArray = ids.split(",");
        if (idArray.length > 0) {
            for (int i = 0; i < idArray.length; i++) {
                if (studentService.logicDeleteById(Integer.parseInt(idArray[i])) <= 0) {
                    return RespBean.error("删除" + idArray[i] + "失败！");
                }
            }
            return RespBean.ok("删除成功！");
        } else {
            return RespBean.ok("删除成功！");
        }
    }

    @DeleteMapping("/deletestudentbyid/{id}")
    public RespBean deleteStudentById(@PathVariable("id") Integer id) {
        int result = studentService.logicDeleteById(id);
        if (result <= 0) {
            return RespBean.error("删除失败！");
        } else {
            return RespBean.ok("删除成功！");
        }
    }

    @PostMapping("/updatestudent")
    public RespBean updateStudent(@RequestBody Student student) {
        int result = studentService.updateByPrimaryKeySelective(student);
        if (result <= 0) {
            return RespBean.error("更新失败！");
        } else {
            return RespBean.ok("更新成功！");
        }
    }

    /**
     * 导出所有的学生的信息
     * @return
     */
    @GetMapping("exportstudents")
    public ResponseEntity<byte[]> exportStudents() {
        return studentService.exportStudents();
    }
}
