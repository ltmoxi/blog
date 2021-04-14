package com.whale.boot.web.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.whale.boot.web.bean.FriendAddress;
import com.whale.boot.web.mapper.FriendAddressMapper;
import com.whale.boot.web.service.FriendAddressService;
import com.whale.boot.web.utils.BusinessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
public class FriendAddressServiceImpl extends ServiceImpl<FriendAddressMapper, FriendAddress> implements FriendAddressService {

}
