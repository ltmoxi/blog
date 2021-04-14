package com.whale.boot.web.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.whale.boot.web.bean.IpAddress;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author litian
 * @since 2020-12-2
 */
public interface IpAddressService extends IService<IpAddress> {

    void update(Integer id);

    /**
     * 查询
     *
     * @param address
     * @return
     */
    List<IpAddress> selectList(IpAddress address);

    /**
     * 通过用户id查询
     *
     * @param managerId 用户id
     * @return
     */
    List<IpAddress> selectIpByManagerId(Integer managerId);
}
