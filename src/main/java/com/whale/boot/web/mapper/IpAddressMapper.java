package com.whale.boot.web.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.whale.boot.web.bean.IpAddress;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author litian
 * @since 2020-12-2
 */
public interface IpAddressMapper extends BaseMapper<IpAddress> {

    List<IpAddress> selectLists(@Param("example") IpAddress address);
}
