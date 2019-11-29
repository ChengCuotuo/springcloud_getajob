package cn.nianzuochen.getajob.service.impl;

import cn.nianzuochen.getajob.domain.dao.Student;
import cn.nianzuochen.getajob.domain.dao.Tutor;
import cn.nianzuochen.getajob.domain.dao.UserInfo;
import cn.nianzuochen.getajob.domain.dao.VerificationCode;
import cn.nianzuochen.getajob.mapper.StudentMapper;
import cn.nianzuochen.getajob.mapper.TutorMapper;
import cn.nianzuochen.getajob.mapper.UserInfoMapper;
import cn.nianzuochen.getajob.mapper.VerificationCodeMapper;
import cn.nianzuochen.getajob.service.VerificationCodeService;
import cn.nianzuochen.getajob.utils.EmailUtil;
import cn.nianzuochen.getajob.vo.request.GetVerificationCodeReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class VerificationCodeServiceImpl implements VerificationCodeService {

    @Autowired
    VerificationCodeMapper verificationCodeMapper;

    @Autowired
    UserInfoMapper userInfoMapper;

    @Autowired
    TutorMapper tutorMapper;

    @Autowired
    StudentMapper studentMapper;

    /**
     * 生成随机码插入到短信验证码库中
     * @param userId
     * @return
     */
    private VerificationCode getVerificationCode(Integer userId) {
        // 生成验证码
        char[] codes = new char[6];
        for (int i =0; i < 6; i++) {
            int select = (int)(Math.random() * 2);
            codes[i] = select == 0 ? ((char)(int)((Math.random() * 9) + 48)) : ((char)((int)(Math.random() * 25) + 65));
        }

        VerificationCode verificationCode = new VerificationCode(userId, new String(codes));

        return verificationCode;
    }

    /**
     * 检查注册信息的正确性
     * @param codeReq
     * @return
     */
    private int checkVerficationInfo(GetVerificationCodeReq codeReq) {
        UserInfo userInfo = userInfoMapper.selectByIdentifier(codeReq.getIdentifier());

        if (userInfo == null) {
            return -1; // 表示用户不存在，也就是 identifier 无效
        }

        Integer userId = userInfo.getUserid();

        if(userInfo.getUsername() != null || userInfo.getPassword() != null) {
            return  -2; // 表示用户已经注册
        }

        userInfo = userInfoMapper.selectUserByUsername(codeReq.getUsername().trim());
        if (userInfo != null) {
            return -3; // 表示用户名重复
        }

        if (codeReq.getPassword().trim().equals("") || codeReq.getPassword() == null ||
                codeReq.getRepassword().trim().equals("") || codeReq.getRepassword() == null) {
            return -4; // 表示密码为空
        }
        if (!codeReq.getPassword().equals(codeReq.getRepassword())) {
            return -5; // 表示两次输入的密码不相同
        }
        return userId;
    }

    /**
     * 根据给定的邮箱和用户 id 发送邮件
     * @param email
     * @param userId
     */
    private void sendEmail(String email, Integer userId) {
        VerificationCode newVerificationCode = this.getVerificationCode(userId);
        VerificationCode verificationCode = verificationCodeMapper.selectByUserId(userId);
        if (verificationCode == null) {
            verificationCodeMapper.insert(newVerificationCode);
        } else {
            verificationCodeMapper.updateByUserId(newVerificationCode);
        }

        String content = "您好！\r\n"
                + "\r\n"
                + "您正在使用邮箱注册，本次请求的验证码为："
                + "\r\n"+ newVerificationCode.getCode() +"(如非本人操作请忽略本条邮件)"
                + "\r\n"
                + "\r\n"
                + "\r\n"
                + "职准准运营团队\r\n"
                + ""+new Date();
        // 发送邮件
        EmailUtil.SendEmail(email, content);
    }

    /**
     * 发送邮箱验证码
     * @param codeReq
     * @return
     */
    @Override
    public int tutorSendRegisterVerificationCodeReq(GetVerificationCodeReq codeReq) {
        /**
         * 1.根据 identifier 确定用户是否存在
         * 2.判断用户是否是重复注册
         * 3.判断用户的用户名是否重复
         * 4.判断两次密码是否相同
         * 5.均符合之后生成查询用户邮箱，插入验证码（根据 userId 查询如果验证码已经存在就更新），发送验证码
         */
        int userId = checkVerficationInfo(codeReq);
        if (userId < 0) {
            return userId;
        }

        Tutor tutor = tutorMapper.selectByUserId(userId);
        if (tutor != null && tutor.getEmail() != null && !tutor.getEmail().trim().equals("")) {
            String email = tutor.getEmail();
            this.sendEmail(email, userId);
//            System.out.println(content);
            return 0;
        } else {
            return -6; // tutor 信息不全
        }
    }

    @Override
    public int studentSendRegisterVerificationCodeReq(GetVerificationCodeReq codeReq) {
        int userId = checkVerficationInfo(codeReq);
        if (userId < 0) {
            return userId;
        }

        Student student = studentMapper.selectByUserId(userId);
        if (student != null && student.getEmail() != null && !student.getEmail().trim().equals("")) {
            String email = student.getEmail();
            this.sendEmail(email, userId);
//            System.out.println(content);
            return 0;
        } else {
            return -6; // tutor 信息不全
        }
    }

    /**
     * 导师发送忘记密码的邮箱验证
     * @param codeReq
     * @return
     */
    @Override
    public int tutorSendForgetPasswordVerificationCodeReq(GetVerificationCodeReq codeReq) {
        int result = this.checkSendForgetPasswordVerificationCode(codeReq);
        if (result < 0) {
            return result;
        }
        UserInfo userInfo = userInfoMapper.selectByIdentifier(codeReq.getIdentifier());
        Integer userId = userInfo.getUserid();
        Tutor tutor = tutorMapper.selectByUserId(userId);
        String email = tutor.getEmail();
        if (email == null || "".equals(email)) {
            return -4; //邮箱错误，请联系导师
        }
        try {
            this.sendForgetPasswordVerificationCode(email, userId);
        } catch (MailException ex) {
            return -5;
        }
//        System.out.println(content);
        return 0;
    }

    @Override
    public int updatePassword(GetVerificationCodeReq codeReq) {
        /**
         * 根据 identifier 查询出 tutor
         * 存在，则判断对应的 username 是否相同
         * 一致，则发送验证码
         */
        UserInfo userInfo = userInfoMapper.selectByIdentifier(codeReq.getIdentifier());
        if (userInfo == null) {
            return -1; // 用户不存在，即 identifier 无效
        }

        if (!userInfo.getUsername().equals(codeReq.getUsername())) {
            return -2; // 用户不能存在，用户名错误
        }

        if (codeReq.getPassword() == null || "".equals(codeReq.getPassword().trim())) {
            return -3; // 密码为空
        }

        Integer userId = userInfo.getUserid();
        VerificationCode verificationCode = verificationCodeMapper.selectByUserId(userId);
        if (!verificationCode.getCode().equals(codeReq.getVericode())) {
            return -4; // 输入的邮箱验证码 错误
        }

        userInfo.setPassword(codeReq.getPassword().trim());
        userInfoMapper.updateByPrimaryKeySelective(userInfo);
        return 0;
    }

    /**
     * 注册
     * 发送的信息除了要有 checkVerficationInfo 的检测之外还有
     * 输入的验证码是否和存储的信息匹配
     * 匹配成功之后要存储到数据库中，同时修改 tutor 的 isDeleted = 0
     * @param codeReq
     * @return
     */
    @Override
    public int tutorRegister(GetVerificationCodeReq codeReq) {
        int userId = this.checkVerficationInfo(codeReq);
        if (userId < 0) {
            return userId;
        }

        VerificationCode verificationCode = verificationCodeMapper.selectByUserId(userId);
        if (!verificationCode.getCode().equals(codeReq.getVericode())) {
            return -7; // 输入的邮箱验证码 错误
        }

        UserInfo userInfo = userInfoMapper.selectByPrimaryKey(userId);
        userInfo.setUsername(codeReq.getUsername());
        userInfo.setPassword(codeReq.getPassword().trim());
        // 更新 userinfo 信息
        userInfoMapper.updateByPrimaryKeySelective(userInfo);
        // 更新 tutor 信息
        Tutor tutor = tutorMapper.selectByUserId(userId);
        tutor.setIsDeleted(0);
        tutorMapper.updateByPrimaryKeySelective(tutor);

        return 0;
    }

    @Override
    public int studentRegister(GetVerificationCodeReq codeReq) {
        int userId = this.checkVerficationInfo(codeReq);
        if (userId < 0) {
            return userId;
        }

        VerificationCode verificationCode = verificationCodeMapper.selectByUserId(userId);
        if (!verificationCode.getCode().equals(codeReq.getVericode())) {
            return -7; // 输入的邮箱验证码 错误
        }

        UserInfo userInfo = userInfoMapper.selectByPrimaryKey(userId);
        userInfo.setUsername(codeReq.getUsername());
        userInfo.setPassword(codeReq.getPassword().trim());

        // 更新 userinfo 信息
        userInfoMapper.updateByPrimaryKeySelective(userInfo);
        // 更新 tutor 信息
        Student student = studentMapper.selectByUserId(userId);
        student.setIsDeleted(0);
        studentMapper.updateByPrimaryKeySelective(student);

        return 0;

    }

    @Override
    public int studentSendForgetPasswordVerificationCodeReq(GetVerificationCodeReq codeReq) {
        int result = this.checkSendForgetPasswordVerificationCode(codeReq);
        if (result < 0) {
            return result;
        }
        UserInfo userInfo = userInfoMapper.selectByIdentifier(codeReq.getIdentifier());
        Integer userId = userInfo.getUserid();
        Student student = studentMapper.selectByUserId(userId);
        String email = student.getEmail();
        if (email == null || "".equals(email)) {
            return -4; //邮箱错误，请联系导师
        }
        try {
            this.sendForgetPasswordVerificationCode(email, userId);
        } catch (MailException ex) {
            return -5;
        }
//        System.out.println(content);
        return 0;
    }

    private int checkSendForgetPasswordVerificationCode(GetVerificationCodeReq codeReq) {
        /**
         * 根据 identifier 查询出 tutor
         * 存在，则判断对应的 username 是否相同
         * 一致，则发送验证码
         */
        UserInfo userInfo = userInfoMapper.selectByIdentifier(codeReq.getIdentifier());
        if (userInfo == null) {
            return -1; // 用户不存在，即 identifier 无效
        }

        if (!userInfo.getUsername().equals(codeReq.getUsername())) {
            return -2; // 用户不能存在，用户名错误
        }

        if (codeReq.getPassword() == null || "".equals(codeReq.getPassword().trim())) {
            return -3; // 密码为空
        }

        return 0;
    }

    public void sendForgetPasswordVerificationCode(String email, Integer userId)
        throws MailException {
        VerificationCode newVerificationCode = this.getVerificationCode(userId);
        VerificationCode verificationCode = verificationCodeMapper.selectByUserId(userId);
        if (verificationCode == null) {
            verificationCodeMapper.insert(newVerificationCode);
        } else {
            verificationCodeMapper.updateByUserId(newVerificationCode);
        }

        String content = "您好！\r\n"
                + "\r\n"
                + "您正在使用邮箱重置密码，本次请求的验证码为："
                + "\r\n"+ newVerificationCode.getCode() +"(如非本人操作请忽略本条邮件)"
                + "\r\n"
                + "\r\n"
                + "\r\n"
                + "职准准运营团队\r\n"
                + ""+new Date();
        // 发送邮件

        EmailUtil.SendEmail(email, content);
    }
}
