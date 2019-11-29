package cn.nianzuochen.getajob.service;

import cn.nianzuochen.getajob.domain.dao.Tutor;
import cn.nianzuochen.getajob.vo.request.SelectTutorsReq;
import cn.nianzuochen.getajob.vo.response.RespBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@FeignClient(value = "GETAJOBSERVICEA", fallbackFactory = TutorServiceFallBack.class)
public interface TutorService {
    @GetMapping("gettutorbyid/{id}")
    public Tutor loadTutorById(@PathVariable("id") Integer id);

    @GetMapping("gettutorbyuseruserid/{userId}")
    public RespBean loadTutorByUserId(@PathVariable("userId") Integer userId);


    @PostMapping("/getalltutors")
    public RespBean loadTutors(@RequestBody SelectTutorsReq selectTutorsReq);

    @PostMapping("/tutoraddstudent")
    public RespBean addTutor(@RequestBody Tutor tutor);

    @GetMapping("/getallsimpletutors")
    public RespBean loadSimpleTutors();

    @DeleteMapping("deletetutors")
    public RespBean deleteTutors(@RequestBody String ids);

    @DeleteMapping("/deletetutorbyid/{id}")
    public RespBean deleteTutorById(@PathVariable("id") Integer id);

    @PostMapping("/updatetutor")
    public RespBean updateTutor(@RequestBody Tutor tutor);

    @GetMapping("exporttutors")
    public ResponseEntity<byte[]> exportTutors();
}
