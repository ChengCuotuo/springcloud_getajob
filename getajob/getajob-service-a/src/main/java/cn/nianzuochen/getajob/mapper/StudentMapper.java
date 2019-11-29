package cn.nianzuochen.getajob.mapper;

import cn.nianzuochen.getajob.vo.request.SelectStudentsByTIdReq;
import cn.nianzuochen.getajob.vo.request.SelectStudentsReq;
import cn.nianzuochen.getajob.domain.dao.Student;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StudentMapper {

    Integer deleteByPrimaryKey(Integer id);

    int logicDeleteById(Integer id);

    int insert(Student record);

    int insertSelective(Student record);

    Student selectByPrimaryKey(Integer id);

    Student selectByUserId(Integer userId);

    int updateByPrimaryKeySelective(Student record);

    int updateByPrimaryKey(Student record);

    List<Student> selectAllStudents(@Param("basicReq") SelectStudentsReq selectStudentsReq);

    Integer countAllStudents(@Param("basicReq") SelectStudentsReq selectStudentsReq);

    /**
     * 根据导师的编号查询所有对应的学生的信息
     * @param selectStudentsByTIdReq
     * @return
     */
    List<Student> selectStudentsByTutorId(@Param("selectStudentsByTIdReq") SelectStudentsByTIdReq selectStudentsByTIdReq);

    Integer countStudentsByTId(@Param("selectStudentsByTIdReq") SelectStudentsByTIdReq selectStudentsByTIdReq);

    /**
     * 查询所有导师添加的未入库的学生
     * @param selectStudentsByTIdReq
     * @return
     */
    List<Student> selectAllNotPassStudents(@Param("selectStudentsByTIdReq") SelectStudentsByTIdReq selectStudentsByTIdReq);

    Integer countAllNotPassStudents(@Param("selectStudentsByTIdReq") SelectStudentsByTIdReq selectStudentsByTIdReq);

    List<Student> selectAllNotRegisterStudents(@Param("selectStudentsByTIdReq") SelectStudentsByTIdReq selectStudentsByTIdReq);

    Integer countAllNotRegisterStudents(@Param("selectStudentsByTIdReq") SelectStudentsByTIdReq selectStudentsByTIdReq);

    List<Student> selectAllRegistedStudents(@Param("selectStudentsByTIdReq") SelectStudentsByTIdReq selectStudentsByTIdReq);

    Integer countAllRegistedStudents(@Param("selectStudentsByTIdReq") SelectStudentsByTIdReq selectStudentsByTIdReq);

    Integer passStudent(Integer id);
}