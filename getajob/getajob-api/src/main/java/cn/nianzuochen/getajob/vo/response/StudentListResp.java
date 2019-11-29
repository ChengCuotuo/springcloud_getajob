package cn.nianzuochen.getajob.vo.response;

import cn.nianzuochen.getajob.domain.dao.Student;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class StudentListResp {

    private List<Student> studentList;

    private Integer totalCount;
}
