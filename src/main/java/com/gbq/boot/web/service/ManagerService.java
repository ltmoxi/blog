package com.gbq.boot.web.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.gbq.boot.web.bean.Manager;
import com.gbq.boot.web.utils.BusinessException;

import java.util.List;

/**
 * @author aqian666
 */
public interface ManagerService extends IService<Manager> {
    /**
     * 通过用户名查询
     * @param userName
     * @return
     */
    Manager findByName(String userName)throws BusinessException;

    /**
     * 保存用户
     * @param manager
     * @throws BusinessException
     */
    Manager saveManager(Manager manager)throws BusinessException;

    /**
     * 向用户发送邮箱
     * @param username
     */
    void sendOutEmail(String username)throws BusinessException;

    /**
     * 查询全部
     * @param manager
     * @return
     */
    List<Manager> getManagerByPage(Manager manager);

    /**
     * 删除用户
     * @param ids
     */
    void delManager(List<Integer> ids)throws BusinessException;

    /**
     * 禁用用户
     * @param ids
     * @param key
     */
    void stopManager(List<Integer> ids, int key)throws BusinessException;

    /**
     * 修改管理员
     * @param manager
     * @return
     */
    Manager updateManager(Manager manager);
}
