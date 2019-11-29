package cn.nianzuochen.getajob.controller;

import cn.nianzuochen.getajob.domain.dao.Student;
import cn.nianzuochen.getajob.domain.dao.Tutor;
import cn.nianzuochen.getajob.service.StudentService;
import cn.nianzuochen.getajob.service.TutorService;
import cn.nianzuochen.getajob.vo.request.SelectTutorsReq;
import cn.nianzuochen.getajob.vo.response.RespBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("tutor")
public class TutorConsumeController {

    @Autowired
    TutorService tutorService;

    @Autowired
    StudentService studentService;


    @GetMapping("gettutorbyid/{id}")
    public Tutor loadTutorById(@PathVariable("id") Integer id) {
        return tutorService.loadTutorById(id);
    }

    @GetMapping("gettutorbyuseruserid/{userId}")
    public RespBean loadTutorByUserId(@PathVariable("userId") Integer userId) {
       return tutorService.loadTutorByUserId(userId);
    }

    /**
     * @Desc: 查询所有的导师信息
     * @return
     */
    @PostMapping("/getalltutors")
    public RespBean loadTutors(@RequestBody SelectTutorsReq selectTutorsReq) {
       return tutorService.loadTutors(selectTutorsReq);
    }

    /**
     * 添加导师信息，同时需要在 userinfo 中添加基本的等待用户注册的验证信息
     * @param tutor
     * @return
     */
    @PostMapping("/addtutor")
    public RespBean addTutor(@RequestBody Tutor tutor) {
       return tutorService.addTutor(tutor);
    }

    /**
     * 导师添加学生的时候 isDeleted 为 3 表示待审核
     * @param student
     * @return
     */
    @PostMapping("/tutoraddstudent")
    public RespBean addStudent(@RequestBody Student student) {
        return studentService.addStudent(student);
    }

    /**
     * 查询出简单的导师信息
     * @return
     */
    @GetMapping("/getallsimpletutors")
    public RespBean loadSimpleTutors() {
       return tutorService.loadSimpleTutors();
    }

    @DeleteMapping("deletetutors")
    public RespBean deleteTutors(@RequestBody String ids) {
       return tutorService.deleteTutors(ids);
    }

    @DeleteMapping("/deletetutorbyid/{id}")
    public RespBean deleteTutorById(@PathVariable("id") Integer id) {
        return tutorService.deleteTutorById(id);
    }

    @PostMapping("/updatetutor")
    public RespBean updateTutor(@RequestBody Tutor tutor) {
       return tutorService.updateTutor(tutor);
    }

    /**
     * 导出导师的信息
     * @return
     */
    @GetMapping("exporttutors")
    public ResponseEntity<byte[]> exportTutors() {
        return tutorService.exportTutors();
    }
}

