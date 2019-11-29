package cn.nianzuochen.getajob.service.impl;
import cn.nianzuochen.getajob.domain.dao.Tutor;
import cn.nianzuochen.getajob.domain.dao.UserInfo;
import cn.nianzuochen.getajob.domain.dto.SimpleTutor;
import cn.nianzuochen.getajob.mapper.TutorMapper;
import cn.nianzuochen.getajob.mapper.UserInfoMapper;
import cn.nianzuochen.getajob.service.TutorService;
import cn.nianzuochen.getajob.utils.Export2Excel;
import cn.nianzuochen.getajob.vo.request.SelectTutorsReq;
import cn.nianzuochen.getajob.vo.response.TutorListResp;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


@Service
public class TutorServiceImpl implements TutorService {

    @Autowired
    TutorMapper tutorMapper;

    @Autowired
    UserInfoMapper userInfoMapper;

    @Override
    public Tutor selectByPrimaryKey(Integer id) {
        Tutor tutor =  tutorMapper.selectByPrimaryKey(id);
        UserInfo userInfo = userInfoMapper.selectByPrimaryKey(tutor.getUserid());
        tutor.setUsername(userInfo.getUsername());
        tutor.setCname(userInfo.getCname());
        return tutor;
    }

    @Override
    public Tutor selectByUserId(Integer userId) {
        Tutor tutor =  tutorMapper.selectByUserId(userId);
        UserInfo userInfo = userInfoMapper.selectByPrimaryKey(tutor.getUserid());
        tutor.setUsername(userInfo.getUsername());
        tutor.setCname(userInfo.getCname());
        return tutor;
    }

    @Override
    public TutorListResp selectAllTutors(SelectTutorsReq selectTutorsReq) {
        TutorListResp tutorListResp = new TutorListResp();
        List<Tutor> tutorList = tutorMapper.selectAllTutors(selectTutorsReq);
        for (Tutor tutor : tutorList) {
            UserInfo userInfo = userInfoMapper.selectByPrimaryKey(tutor.getUserid());
            // 导师的初始验证信息
            tutor.setIdentifier(userInfo.getIdentifier());
            tutor.setCname(userInfo.getCname());
            String username = userInfo.getUsername();
            if (username == null) {
                tutor.setUsername("$此用户未注册$");
            } else {
                tutor.setUsername(username);
            }
        }
        tutorListResp.setTutorList(tutorList);
        tutorListResp.setTotalCount(tutorMapper.countAllTutors(selectTutorsReq));
        return tutorListResp;
    }

    /**
     * 插入导师信息，同时需要在 userinfo 中插入改导师的 identifier 等待注册
     * @param tutor
     * @return
     */
    @Override
    public int insertSelective(Tutor tutor) {
        if (userInfoMapper.selectUserByUsername(tutor.getUsername()) != null) {
            return  -3;
        }

        UserInfo userInfo = new UserInfo();
        // 导师的 roleid  = 2
        userInfo.setRoleid(2);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        String date = simpleDateFormat.format(new Date());
        userInfo.setIdentifier("tutor" + date);
        userInfo.setCname("$此用户未注册$");
        userInfoMapper.insertSelective(userInfo);

        // 获取新添加的用户的 userid
        Integer userId = userInfo.getUserid();
        userInfo.setCname(tutor.getCname());
        userInfo.setIdentifier(userInfo.getIdentifier() + userId);
        // 根据设计的导师的 identifier 是 tutor + yyyyMMdd + userId
        userInfoMapper.updateByPrimaryKeySelective(userInfo);

        // 设置用户的 userid
        tutor.setUserid(userId);
        tutor.setIsDeleted(2); // 2 表示已经分配但是没有注册
        return tutorMapper.insertSelective(tutor);
    }

    @Override
    public List<SimpleTutor> selectAllSimpleTutors() {
        return tutorMapper.selectAllSimpleTutors();
    }

    /**
     * 删除导师信息，是假删除，还需要将 userinfo 中的用户信息真删除
     * @param id
     * @return
     */
    @Override
    public int logicDeleteById(Integer id) {
        int userId = tutorMapper.selectByPrimaryKey(id).getUserid();
        int result = tutorMapper.logicDeleteById(id);
        if (result <= 0) {
            return result;
        } else {
            // 根据导师的 id 查询出 userId 再将 userinfo 对应的用户信息删除
            return userInfoMapper.deleteByPrimaryKey(userId);
        }
    }

    /**
     * 更新导师的信息
     * @param record
     * @return
     */
    @Override
    public int updateByPrimaryKeySelective(Tutor record) {
        return tutorMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public ResponseEntity<byte[]> exportAllEnterprise() {
        Export2Excel export2Excel = new Export2Excel("tutor");
        HSSFSheet sheet = export2Excel.createSheet("tutor");
        List<Tutor> tutorList = tutorMapper.selectAllTutors(new SelectTutorsReq());
        return export2Excel.addInfo(sheet, tutorList);
    }
}
