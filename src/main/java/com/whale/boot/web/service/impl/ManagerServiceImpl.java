package com.whale.boot.web.service.impl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.whale.boot.web.bean.Manager;
import com.whale.boot.web.mapper.ManagerMapper;
import com.whale.boot.web.service.ManagerService;
import com.whale.boot.web.utils.BusinessException;
import com.whale.boot.web.utils.ConstUtil;
import com.whale.boot.web.utils.enums.DataStatusEnum;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author litian
 */
@Service
@Transactional(rollbackFor = BusinessException.class)
public class ManagerServiceImpl extends ServiceImpl<ManagerMapper, Manager> implements ManagerService {
    @Resource
    private ManagerMapper managerMapper;
    @Resource
    private HttpServletRequest request;
//    @Resource
//    private JavaMailSender javaMailSender;

    @Override
    public Manager findByName(String username) throws BusinessException {
        //通过用户名查询
        QueryWrapper<Manager> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(Manager::getUsername, username)
                .eq(Manager::getStatus, ConstUtil.DEFAULT_STATE);
        return managerMapper.selectOne(queryWrapper);
    }

    @Override
    public Manager saveManager(Manager manager) throws BusinessException {
        try {
            if (manager.getId() == null) {
                manager.setCreateTime(DateUtil.now());
            }

            //先验证邮箱是否已经被注册
            Manager byName = this.findByName(manager.getUsername());
            if (byName != null) {
                throw new BusinessException("已经注册，不能再次注册。");
            }

            manager.setPassword(SecureUtil.md5(manager.getPassword()));
            managerMapper.insert(manager);
            return manager;
        } catch (BusinessException e) {
            throw new BusinessException(e.getErrorMessage());
        }
    }

    @Override
    public void sendOutEmail(String username) throws BusinessException {
//        try {
//            //先验证邮箱是否已经被注册
//            Manager byName = this.findByName(username);
//            if (byName != null) {
//                throw new BusinessException("邮箱已经注册，不能再次注册。");
//            }
//
//            MimeMessage message = javaMailSender.createMimeMessage();
//            MimeMessageHelper helper = new MimeMessageHelper(message, true);
//            helper.setFrom("289373410@qq.com");
//            helper.setTo(username);
//            helper.setSubject("标题：请收好你在阿前的博客的注册码");
//
//            String checkCode = String.valueOf(new Random().nextInt(899999) + 100000);
//            HttpSession session = request.getSession();
//            session.setAttribute("code", checkCode);
//
//            String str = "<h4>欢迎您在阿前的博客注册</h4>" + "<p style='color:#F00'>您的验证码为:" + checkCode + "</p>";
//            //append("<p style='text-align:right'>右对齐</p>");
//            helper.setText(str, true);
//
//       /* FileSystemResource fileSystemResource=new FileSystemResource(new File("C:\\Users\\28937\\Desktop\\搞笑图片\\welcome.gif"));
//        helper.addAttachment("啦啦啦",fileSystemResource);*/
//            javaMailSender.send(message);
//        } catch (BusinessException e) {
//            throw new BusinessException(e.getErrorMessage());
//        } catch (MessagingException e) {
//            e.printStackTrace();
//        }
    }

    @Override
    public List<Manager> getManagerByPage(Manager manager) {
        Manager manager1 = (Manager) SecurityUtils.getSubject().getPrincipal();
        if (manager1.getType().equals(ConstUtil.SUPER_ADMIN)) {
            manager.setType(ConstUtil.ADMIN);
        }
        if (manager1.getType().equals(ConstUtil.ADMIN)) {
            manager.setType(ConstUtil.BOLG_MIN);
        }

        QueryWrapper<Manager> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().like(StringUtils.isNotBlank(manager.getUsername()), Manager::getUsername, manager.getUsername())
                .like(StringUtils.isNotBlank(manager.getName()), Manager::getName, manager.getName())
                .eq(StringUtils.isNotBlank(manager.getType()), Manager::getType, manager.getType())
                .orderByDesc(Manager::getCreateTime)
                .orderByDesc(Manager::getStatus);
        return managerMapper.selectList(queryWrapper);
    }

    @Override
    public void delManager(List<Integer> ids) throws BusinessException {
        try {
            managerMapper.deleteBatchIds(ids);
        } catch (BusinessException e) {
            throw new BusinessException(e.getErrorMessage());
        }
    }

    @Override
    public void stopManager(List<Integer> ids, int key) throws BusinessException {
        try {
            List<Manager> managers = managerMapper.selectBatchIds(ids);
            if (managers.stream().anyMatch(x -> x.getStatus() == key)) {
                throw new BusinessException("已经是" + DataStatusEnum.getValue(key) + "状态");
            }
            managers.forEach(x -> x.setStatus(key));
            this.updateBatchById(managers);
        } catch (BusinessException e) {
            throw new BusinessException(e.getErrorMessage());
        }
    }

    @Override
    public Manager updateManager(Manager manager) {
        QueryWrapper<Manager> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().ne(Manager::getId, manager.getId())
                .eq(Manager::getUsername, manager.getUsername())
                .eq(Manager::getStatus, ConstUtil.DEFAULT_STATE);

        if (managerMapper.selectCount(queryWrapper) > 0) {
            throw new BusinessException("用户名或者昵称已经存在");
        }

        if (manager.getPassword() != null) {
            manager.setPassword(SecureUtil.md5(manager.getPassword()));
        }

        managerMapper.updateById(manager);
        return manager;
    }

}
