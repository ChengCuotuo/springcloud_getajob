package cn.nianzuochen.getajob.service;

import cn.nianzuochen.getajob.vo.response.StudentListResp;
import cn.nianzuochen.getajob.vo.request.SelectStudentsByTIdReq;
import cn.nianzuochen.getajob.vo.request.SelectStudentsReq;
import cn.nianzuochen.getajob.vo.response.RespBean;
import cn.nianzuochen.getajob.domain.dao.Student;
import org.springframework.http.ResponseEntity;

public interface StudentService {
    public Student selectByPrimaryKey(Integer id);

    public Student selectByUserId(Integer userId);

    public StudentListResp selectAllStudents(SelectStudentsReq selectStudentsReq);

    public StudentListResp selectStudentsByTutorId(SelectStudentsByTIdReq selectStudentsByTIdReq);

    public int logicDeleteById(Integer id);

    public int updateByPrimaryKeySelective(Student record);

    public RespBean addStudent(Student student, int isDeleted);

    public Integer passStudent(Integer id);

    public StudentListResp selectAllNotPassStudents(SelectStudentsByTIdReq selectStudentsByTIdReq);

    public Integer selectNotPassedStudentNumber();

    public StudentListResp selectAllNotRegisterStudents(SelectStudentsByTIdReq selectStudentsByTIdReq);

    public StudentListResp selectAllRegistedStudents(SelectStudentsByTIdReq selectStudentsByTIdReq);

    public ResponseEntity<byte[]> exportStudents();
}
