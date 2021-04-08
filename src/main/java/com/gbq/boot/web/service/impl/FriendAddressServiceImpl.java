package com.gbq.boot.web.service.impl;

import com.gbq.boot.web.bean.FriendAddress;
import com.gbq.boot.web.mapper.FriendAddressMapper;
import com.gbq.boot.web.service.FriendAddressService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gbq.boot.web.utils.BusinessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author aqian666
 * @since 2019-10-23
 */
@Service
@Transactional(rollbackFor= BusinessException.class)
public class FriendAddressServiceImpl extends ServiceImpl<FriendAddressMapper, FriendAddress> implements FriendAddressService {

}
