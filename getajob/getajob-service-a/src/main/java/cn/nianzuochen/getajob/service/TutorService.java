package cn.nianzuochen.getajob.service;


import cn.nianzuochen.getajob.vo.request.SelectTutorsReq;
import cn.nianzuochen.getajob.vo.response.TutorListResp;
import cn.nianzuochen.getajob.domain.dao.Tutor;
import cn.nianzuochen.getajob.domain.dto.SimpleTutor;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface TutorService {

    public Tutor selectByPrimaryKey(Integer id);

    public Tutor selectByUserId(Integer userId);

    public TutorListResp selectAllTutors(SelectTutorsReq selectTutorsReq);

    public int insertSelective(Tutor record);

    public List<SimpleTutor> selectAllSimpleTutors();

    public  int logicDeleteById(Integer id);

    public int updateByPrimaryKeySelective(Tutor record);

    ResponseEntity<byte[]> exportAllEnterprise();
}
