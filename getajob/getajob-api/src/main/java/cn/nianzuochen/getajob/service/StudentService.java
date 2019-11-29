package cn.nianzuochen.getajob.service;

import cn.nianzuochen.getajob.domain.dao.Student;
import cn.nianzuochen.getajob.vo.request.SelectStudentsByTIdReq;
import cn.nianzuochen.getajob.vo.request.SelectStudentsReq;
import cn.nianzuochen.getajob.vo.response.RespBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@FeignClient(value = "GETAJOBSERVICEA", fallbackFactory = StudentServiceFallBack.class)
public interface StudentService {
    @GetMapping("/getstudentbyid/{id}")
    public Student loadStudentById(@PathVariable("id") Integer id);


    @GetMapping("/getstudentbyuserid/{userId}")
    public RespBean loadStudentByUserId(@PathVariable("userId") Integer userId);

    @PostMapping("/getallstudents")
    public RespBean loadStudents(@RequestBody SelectStudentsReq selectStudentsReq);


    @PostMapping("/getstudentsbytid")
    public RespBean loadStudentsByTId(@RequestBody SelectStudentsByTIdReq selectStudentsByTIdReq);

    @PostMapping("/getallnotpassedstudents")
    public RespBean loadAllNotPassedStudent(@RequestBody SelectStudentsByTIdReq selectStudentsByTIdReq);

    @GetMapping("/getnotpassedstudentsnumber")
    public RespBean loadNotPassedStudentNumber();


    @PostMapping("/getallnotregisterstudents")
    public RespBean loadAllNotRegisterStudent(@RequestBody SelectStudentsByTIdReq selectStudentsByTIdReq);

    @PostMapping("/getallregistedstudents")
    public RespBean loadAllRegistedStudent(@RequestBody SelectStudentsByTIdReq selectStudentsByTIdReq);

    @PostMapping("/addstudent")
    public RespBean addStudent(@RequestBody Student student);

    @GetMapping("/passstudent/{id}")
    public RespBean passStudent(@PathVariable("id") Integer id);

    @DeleteMapping("deletestudents")
    public RespBean deleteStudents(@RequestBody String ids);

    @DeleteMapping("/deletestudentbyid/{id}")
    public RespBean deleteStudentById(@PathVariable("id") Integer id);

    @PostMapping("/updatestudent")
    public RespBean updateStudent(@RequestBody Student student);

    @GetMapping("exportstudents")
    public ResponseEntity<byte[]> exportStudents();
}
