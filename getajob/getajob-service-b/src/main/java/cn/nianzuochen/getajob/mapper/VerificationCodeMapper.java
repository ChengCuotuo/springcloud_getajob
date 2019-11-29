package cn.nianzuochen.getajob.mapper;

import cn.nianzuochen.getajob.domain.dao.VerificationCode;
import org.apache.ibatis.annotations.Param;

public interface VerificationCodeMapper {
    VerificationCode selectByUserId(Integer userId);

    int insert(VerificationCode record);

    int insertSelective(VerificationCode record);

    int updateByUserId(@Param("vericode") VerificationCode record);
}