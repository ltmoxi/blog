package com.whale.boot.web.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.whale.boot.web.bean.IpAddress;
import com.whale.boot.web.mapper.IpAddressMapper;
import com.whale.boot.web.service.IpAddressService;
import com.whale.boot.web.utils.BusinessException;
import com.whale.boot.web.utils.IpUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author litian
 * @since 2020-12-2
 */
@Service
@Transactional(rollbackFor = BusinessException.class)
public class IpAddressServiceImpl extends ServiceImpl<IpAddressMapper, IpAddress> implements IpAddressService {

    @Resource
    private IpAddressMapper ipAddressMapper;

    @Override
    public void update(Integer id) {
        try {
            QueryWrapper<IpAddress> queryWrapper = new QueryWrapper<>();
            queryWrapper.lambda().eq(IpAddress::getManagerId, id);
            List<IpAddress> ipAddresses = ipAddressMapper.selectList(queryWrapper);

            if (ipAddresses.stream().anyMatch(x -> x.getIp().equals(IpUtil.getIpAddr()))) {
                //如果已经存在ip，则修改ip访问时间
                IpAddress address = ipAddresses.stream().filter(f -> f.getIp().equals(IpUtil.getIpAddr())).findAny().orElse(null);
                ipAddressMapper.updateById(address);
            } else {
                //不存在则新加入一条访问ip
                IpAddress ipAddress = new IpAddress();
                ipAddress.setIp(IpUtil.getIpAddr());
                ipAddress.setManagerId(id);
                ipAddressMapper.insert(ipAddress);
            }

        } catch (BusinessException e) {
            throw new BusinessException("注册失败！", e.getErrorMessage());
        }

    }

    @Override
    public List<IpAddress> selectList(IpAddress address) {
        return ipAddressMapper.selectLists(address);
    }

    @Override
    public List<IpAddress> selectIpByManagerId(Integer managerId) {
        QueryWrapper<IpAddress> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(IpAddress::getManagerId, managerId);
        return ipAddressMapper.selectList(queryWrapper);
    }
}
