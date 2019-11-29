package cn.nianzuochen.getajob.service;

import cn.nianzuochen.getajob.domain.dao.Student;
import cn.nianzuochen.getajob.vo.request.SelectStudentsByTIdReq;
import cn.nianzuochen.getajob.vo.request.SelectStudentsReq;
import cn.nianzuochen.getajob.vo.response.RespBean;
import feign.hystrix.FallbackFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class StudentServiceFallBack implements FallbackFactory<StudentService> {
    @Override
    public StudentService create(Throwable throwable) {
        return new StudentService() {
            @Override
            public Student loadStudentById(Integer id) {
                Student student = new Student();
                student.setId(id);
                student.setCname("延迟导致操作失败，请稍后重试!");
                return student;
            }

            @Override
            public RespBean loadStudentByUserId(Integer userId) {
                return RespBean.error("延迟导致操作失败，请稍后重试！");
            }

            @Override
            public RespBean loadStudents(SelectStudentsReq selectStudentsReq) {
                return RespBean.error("延迟导致操作失败，请稍后重试！");
            }

            @Override
            public RespBean loadStudentsByTId(SelectStudentsByTIdReq selectStudentsByTIdReq) {
                return RespBean.error("延迟导致操作失败，请稍后重试！");
            }

            @Override
            public RespBean loadAllNotPassedStudent(SelectStudentsByTIdReq selectStudentsByTIdReq) {
                return RespBean.error("延迟导致操作失败，请稍后重试！");
            }

            @Override
            public RespBean loadNotPassedStudentNumber() {
                return RespBean.error("延迟导致操作失败，请稍后重试！");
            }

            @Override
            public RespBean loadAllNotRegisterStudent(SelectStudentsByTIdReq selectStudentsByTIdReq) {
                return RespBean.error("延迟导致操作失败，请稍后重试！");
            }

            @Override
            public RespBean loadAllRegistedStudent(SelectStudentsByTIdReq selectStudentsByTIdReq) {
                return RespBean.error("延迟导致操作失败，请稍后重试！");
            }

            @Override
            public RespBean addStudent(Student student) {
                return RespBean.error("延迟导致操作失败，请稍后重试！");
            }

            @Override
            public RespBean passStudent(Integer id) {
                return RespBean.error("延迟导致操作失败，请稍后重试！");
            }

            @Override
            public RespBean deleteStudents(String ids) {
                return RespBean.error("延迟导致操作失败，请稍后重试！");
            }

            @Override
            public RespBean deleteStudentById(Integer id) {
                return RespBean.error("延迟导致操作失败，请稍后重试！");
            }

            @Override
            public RespBean updateStudent(Student student) {
                return RespBean.error("延迟导致操作失败，请稍后重试！");
            }

            @Override
            public ResponseEntity<byte[]> exportStudents() {
                return null;
            }
        };
    }
}
