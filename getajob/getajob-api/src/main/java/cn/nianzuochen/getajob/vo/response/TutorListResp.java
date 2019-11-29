package cn.nianzuochen.getajob.vo.response;

import cn.nianzuochen.getajob.domain.dao.Tutor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class TutorListResp {

    private List<Tutor> tutorList;
    private Integer totalCount;
}
