package com.gbq.boot.web.controller;


import com.gbq.boot.web.bean.IpAddress;
import com.gbq.boot.web.service.IpAddressService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author aqian666
 * @since 2019-10-31
 */
@RestController
@RequestMapping("/ip-address")
public class IpAddressController {

    @Resource
    private IpAddressService ipAddressService;

    /**
     * 分页查询
     */
    @GetMapping("/list")
    public HashMap<String, Object> getArticlePage(IpAddress address, Integer  pageSize, Integer currentPage){
        HashMap<String,Object> result = new HashMap<>();
        if (pageSize != null && currentPage != null){
            PageHelper.startPage(currentPage, pageSize);
            List<IpAddress> articleList= ipAddressService.selectList(address);
            PageInfo<IpAddress> pageInfo = new PageInfo<>(articleList);
            result.put("data",pageInfo);
        }else {
            List<IpAddress>  articleList= ipAddressService.selectList(address);
            result.put("data",articleList);
        }
        return result;
    }


    /**
     * 根据codeId查询订单
     */
    @GetMapping("/{managerId}")
    public HashMap<String, Object> selectIpByManagerId(@PathVariable(value = "managerId") Integer managerId) {
        HashMap<String,Object> result = new HashMap<>();
        // 查询数据
        List<IpAddress> ipAddresses=ipAddressService.selectIpByManagerId(managerId);
        result.put("data",ipAddresses);
        return result;
    }

}
