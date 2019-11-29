package cn.nianzuochen.getajob.mapper;

import cn.nianzuochen.getajob.vo.request.SelectTutorsReq;
import cn.nianzuochen.getajob.domain.dao.Tutor;
import cn.nianzuochen.getajob.domain.dto.SimpleTutor;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TutorMapper {

    int deleteByPrimaryKey(Integer id);

    /**
     * 逻辑删除，将指定的 tutor 的信息 isDeleted = 1
     * @param id
     * @return
     */
    int logicDeleteById(Integer id);

    int insert(Tutor record);

    int insertSelective(Tutor record);

    Tutor selectByPrimaryKey(Integer id);

    Tutor selectByUserId(Integer userId);

    int updateByPrimaryKeySelective(Tutor record);

    int updateByPrimaryKey(Tutor record);

    List<Tutor> selectAllTutors(@Param("basicReq") SelectTutorsReq selectTutorsReq);

    List<SimpleTutor> selectAllSimpleTutors();

    Integer countAllTutors(@Param("basicReq") SelectTutorsReq selectTutorsReq);
}