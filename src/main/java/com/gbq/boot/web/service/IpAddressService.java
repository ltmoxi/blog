package com.gbq.boot.web.service;

import com.gbq.boot.web.bean.IpAddress;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author aqian666
 * @since 2019-10-31
 */
public interface IpAddressService extends IService<IpAddress> {

    void update(Integer id);

    /**
     * 查询
     * @param address
     * @return
     */
    List<IpAddress> selectList(IpAddress address);

    /**
     * 通过用户id查询
     * @param managerId 用户id
     * @return
     */
    List<IpAddress> selectIpByManagerId(Integer managerId);
}
