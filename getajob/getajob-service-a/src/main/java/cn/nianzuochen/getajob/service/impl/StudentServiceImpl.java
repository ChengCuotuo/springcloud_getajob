package cn.nianzuochen.getajob.service.impl;

import cn.nianzuochen.getajob.domain.dao.Student;
import cn.nianzuochen.getajob.domain.dao.UserInfo;
import cn.nianzuochen.getajob.mapper.StudentMapper;
import cn.nianzuochen.getajob.mapper.UserInfoMapper;
import cn.nianzuochen.getajob.service.StudentService;
import cn.nianzuochen.getajob.utils.Export2Excel;
import cn.nianzuochen.getajob.vo.request.SelectStudentsByTIdReq;
import cn.nianzuochen.getajob.vo.request.SelectStudentsReq;
import cn.nianzuochen.getajob.vo.response.RespBean;
import cn.nianzuochen.getajob.vo.response.StudentListResp;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    StudentMapper studentMapper;

    @Autowired
    UserInfoMapper userInfoMapper;

    public Student selectByPrimaryKey(Integer id) {
        return studentMapper.selectByPrimaryKey(id);
    }

    @Override
    public Student selectByUserId(Integer userId) {
        Student student =  studentMapper.selectByUserId(userId);
        UserInfo userInfo = userInfoMapper.selectByPrimaryKey(student.getUserid());
        student.setUsername(userInfo.getUsername());
        student.setCname(userInfo.getCname());
        return student;
    }

    @Override
    public StudentListResp selectAllStudents(SelectStudentsReq selectStudentsReq) {
        StudentListResp studentListResp = new StudentListResp();
        List<Student> studentList = studentMapper.selectAllStudents(selectStudentsReq);
        for (Student student : studentList) {
            UserInfo userInfo = userInfoMapper.selectByPrimaryKey(student.getUserid());
            student.setCname(userInfo.getCname());
            String username = userInfo.getUsername();
            if (username == null) {
                student.setUsername("$此用户未注册$");
            } else {
                student.setUsername(username);
            }
            // 设置学生的初始验证信息
            student.setIdentifier(userInfo.getIdentifier());
//            System.out.println(userInfo.getIdentifier());
        }
        studentListResp.setStudentList(studentList);
        studentListResp.setTotalCount(studentMapper.countAllStudents(selectStudentsReq));
        return studentListResp;
    }

    /**
     * 根据导师的编号查询对应的学生信息
     * @param selectStudentsByTIdReq
     * @return
     */
    @Override
    public StudentListResp selectStudentsByTutorId(SelectStudentsByTIdReq selectStudentsByTIdReq) {
        StudentListResp studentListResp = new StudentListResp();
        List<Student> studentList = studentMapper.selectStudentsByTutorId(selectStudentsByTIdReq);
        for (Student student : studentList) {
            UserInfo userInfo = userInfoMapper.selectByPrimaryKey(student.getUserid());
            // 设置学生的初始验证信息
            student.setIdentifier(userInfo.getIdentifier());
            student.setCname(userInfo.getCname());
            String username = userInfo.getUsername();
            if (username == null) {
                student.setUsername("$此用户未注册$");
            } else {
                student.setUsername(username);
            }
        }
        studentListResp.setStudentList(studentList);
        studentListResp.setTotalCount(studentMapper.countStudentsByTId(selectStudentsByTIdReq));
        return studentListResp;
    }

    /**
     * 查询所有未入库的学生
     * @param selectStudentsByTIdReq
     * @return
     */
    @Override
    public StudentListResp selectAllNotPassStudents(SelectStudentsByTIdReq selectStudentsByTIdReq) {
        StudentListResp studentListResp = new StudentListResp();
        List<Student> studentList = studentMapper.selectAllNotPassStudents(selectStudentsByTIdReq);
        for (Student student : studentList) {
            UserInfo userInfo = userInfoMapper.selectByPrimaryKey(student.getUserid());
            student.setIdentifier(userInfo.getIdentifier());
            student.setCname(userInfo.getCname());
        }
        studentListResp.setStudentList(studentList);
        studentListResp.setTotalCount(studentMapper.countAllNotPassStudents(selectStudentsByTIdReq));
        return studentListResp;
    }

    /**
     * 查询所有未入库的学生的人数
     * @return
     */
    @Override
    public Integer selectNotPassedStudentNumber() {
        return studentMapper.countAllNotPassStudents(new SelectStudentsByTIdReq());
    }

    @Override
    public StudentListResp selectAllNotRegisterStudents(SelectStudentsByTIdReq selectStudentsByTIdReq) {
        StudentListResp studentListResp = new StudentListResp();
        List<Student> studentList = studentMapper.selectAllNotRegisterStudents(selectStudentsByTIdReq);
        for (Student student : studentList) {
            UserInfo userInfo = userInfoMapper.selectByPrimaryKey(student.getUserid());
            student.setIdentifier(userInfo.getIdentifier());
            student.setCname(userInfo.getCname());
        }
        studentListResp.setStudentList(studentList);

        return studentListResp;
    }

    @Override
    public StudentListResp selectAllRegistedStudents(SelectStudentsByTIdReq selectStudentsByTIdReq) {
        StudentListResp studentListResp = new StudentListResp();
        List<Student> studentList = studentMapper.selectAllRegistedStudents(selectStudentsByTIdReq);
        for (Student student : studentList) {
            UserInfo userInfo = userInfoMapper.selectByPrimaryKey(student.getUserid());
            student.setIdentifier(userInfo.getIdentifier());
            student.setCname(userInfo.getCname());
        }
        studentListResp.setStudentList(studentList);
        return studentListResp;
    }

    /**
     * 导出所有的学生信息
     * @return
     */
    @Override
    public ResponseEntity<byte[]> exportStudents() {
        Export2Excel export2Excel = new Export2Excel("student");
        HSSFSheet sheet = export2Excel.createSheet("student");
        List<Student> studentList = studentMapper.selectAllStudents(new SelectStudentsReq());
        return export2Excel.addInfo(sheet, studentList);
    }

    /**
     * 插入学生信息，同时需要在 userinfo 中插入改导师的 identifier 等待注册
     * @param student
     * @return
     */
    private int insertSelective(Student student, int isDeleted) {
        // 验证用户名是否重复
        if (userInfoMapper.selectUserByUsername(student.getUsername()) != null) {
            return -3;
        }
        UserInfo userInfo = new UserInfo();
        // 学生的 roleid = 3
        userInfo.setRoleid(3);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        String date = simpleDateFormat.format(new Date());
        userInfo.setIdentifier("stu" + date);
        userInfo.setCname("$此用户未注册$");
        userInfoMapper.insertSelective(userInfo);

        // 获取新添加的用户的 userid
        Integer userId = userInfo.getUserid();
        userInfo.setCname(student.getCname());
        userInfo.setIdentifier(userInfo.getIdentifier() + userId);
        // 根据设计的导师的 identifier 是 tutor + yyyyMMdd + userId
        userInfoMapper.updateByPrimaryKeySelective(userInfo);

        student.setUserid(userId);
        student.setIsDeleted(isDeleted); // 表示已经分配但是没有注册
        return studentMapper.insertSelective(student);
    }

    @Override
    public int logicDeleteById(Integer id) {
        int userId = studentMapper.selectByPrimaryKey(id).getUserid();
        int result = studentMapper.logicDeleteById(id);
        if (result <= 0) {
            return result;
        } else {
            return userInfoMapper.deleteByPrimaryKey(userId);
        }
    }

    @Override
    public int updateByPrimaryKeySelective(Student record) {
        return studentMapper.updateByPrimaryKeySelective(record);
    }

    /**
     * 添加学生信息，添加的时候同时在 userinfo 中添加 identifier 等待注册
     * 管理员添加的收 isDeleted = 2
     * 导师添加的时候 isDeleted = 3  表示待审核
     * @param student
     * @return
     */
    public RespBean addStudent(Student student, int isDeleted) {
        Integer result = this.insertSelective(student, isDeleted);
        if (result == -3){
            return RespBean.error("用户名重复！");
        } else if (result > 0){
            return RespBean.ok("添加成功！");
        } else {
            return RespBean.error("添加失败！");
        }
    }

    @Override
    public Integer passStudent(Integer id) {
        return studentMapper.passStudent(id);
    }
}
