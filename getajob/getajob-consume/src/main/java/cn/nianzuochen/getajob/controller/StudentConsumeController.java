package cn.nianzuochen.getajob.controller;

import cn.nianzuochen.getajob.domain.dao.Student;
import cn.nianzuochen.getajob.service.StudentService;
import cn.nianzuochen.getajob.vo.request.SelectStudentsByTIdReq;
import cn.nianzuochen.getajob.vo.request.SelectStudentsReq;
import cn.nianzuochen.getajob.vo.response.RespBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("student")
public class StudentConsumeController {

    @Autowired
    StudentService studentService;

    @GetMapping("/getstudentbyid/{id}")
    public Student loadStudentById(@PathVariable("id")  Integer id) {
        return studentService.loadStudentById(id);
    }

    @GetMapping("/getstudentbyuserid/{userId}")
    public RespBean loadStudentByUserId(@PathVariable("userId") Integer userId) {
       return studentService.loadStudentByUserId(userId);
    }

    // ToDo 查询信息有两类：没有被删除的有已经入库的
    /**
     * @Desc: 查询所有的学生信息
     * @return
     */
    @PostMapping("/getallstudents")
    public RespBean loadStudents(@RequestBody SelectStudentsReq selectStudentsReq) {
        return studentService.loadStudents(selectStudentsReq);
    }

    /**
     * 根据导师的编号查询对应的学生
     * @param selectStudentsByTIdReq
     * @return
     */
    @PostMapping("/getstudentsbytid")
    public RespBean loadStudentsByTId(@RequestBody SelectStudentsByTIdReq selectStudentsByTIdReq) {
        return studentService.loadStudentsByTId(selectStudentsByTIdReq);
    }

    /**
     * 查询所有未入库的学生
     * @param selectStudentsByTIdReq
     * @return
     */
    @PostMapping("/getallnotpassedstudents")
    public RespBean loadAllNotPassedStudent(@RequestBody SelectStudentsByTIdReq selectStudentsByTIdReq) {
        return studentService.loadAllNotPassedStudent(selectStudentsByTIdReq);
    }

    /**
     * 获取还没有入库的学生的人数
     * @return
     */
    @GetMapping("/getnotpassedstudentsnumber")
    public RespBean loadNotPassedStudentNumber() {
       return studentService.loadNotPassedStudentNumber();
    }

    /**
     * 查询所有未注册的学生
     * @param selectStudentsByTIdReq
     * @return
     */
    @PostMapping("/getallnotregisterstudents")
    public RespBean loadAllNotRegisterStudent(@RequestBody SelectStudentsByTIdReq selectStudentsByTIdReq) {
       return studentService.loadAllNotRegisterStudent(selectStudentsByTIdReq);
    }

    /**
     * 查询所有已注册的学生
     * @param selectStudentsByTIdReq
     * @return
     */
    @PostMapping("/getallregistedstudents")
    public RespBean loadAllRegistedStudent(@RequestBody SelectStudentsByTIdReq selectStudentsByTIdReq) {
       return studentService.loadAllRegistedStudent(selectStudentsByTIdReq);
    }

    /**
     * 管理员添加学生的时候 isDeleted 表示已经审核通过
     * @param student
     * @return
     */
    @PostMapping("/addstudent")
    public RespBean addStudent(@RequestBody Student student) {
        return studentService.addStudent(student);
    }

    /**
     * 根据提供的 id 将将学生设置为已通过审核，即 isDekted = 2
     * @param id
     * @return
     */
    @GetMapping("/passstudent/{id}")
    public RespBean passStudent(@PathVariable("id") Integer id){
        return studentService.passStudent(id);
    }

    @DeleteMapping("deletestudents")
    public RespBean deleteStudents(@RequestBody String ids) {
       return studentService.deleteStudents(ids);
    }

    @DeleteMapping("/deletestudentbyid/{id}")
    public RespBean deleteStudentById(@PathVariable("id") Integer id) {
       return studentService.deleteStudentById(id);
    }

    @PostMapping("/updatestudent")
    public RespBean updateStudent(@RequestBody Student student) {
        return studentService.updateStudent(student);
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
