package com.gbq.boot.web.mapper;

import com.gbq.boot.web.bean.IpAddress;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author aqian666
 * @since 2019-10-31
 */
public interface IpAddressMapper extends BaseMapper<IpAddress> {

    List<IpAddress> selectLists(@Param("example") IpAddress address);
}
