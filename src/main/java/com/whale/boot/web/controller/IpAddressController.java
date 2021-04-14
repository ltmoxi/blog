package com.whale.boot.web.controller;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.whale.boot.web.bean.IpAddress;
import com.whale.boot.web.service.IpAddressService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author litian
 * @since 2020-12-2
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
    public HashMap<String, Object> getArticlePage(IpAddress address, Integer pageSize, Integer currentPage) {
        HashMap<String, Object> result = new HashMap<>();
        if (pageSize != null && currentPage != null) {
            PageHelper.startPage(currentPage, pageSize);
            List<IpAddress> articleList = ipAddressService.selectList(address);
            PageInfo<IpAddress> pageInfo = new PageInfo<>(articleList);
            result.put("data", pageInfo);
        } else {
            List<IpAddress> articleList = ipAddressService.selectList(address);
            result.put("data", articleList);
        }
        return result;
    }


    /**
     * 根据codeId查询订单
     */
    @GetMapping("/{managerId}")
    public HashMap<String, Object> selectIpByManagerId(@PathVariable(value = "managerId") Integer managerId) {
        HashMap<String, Object> result = new HashMap<>();
        // 查询数据
        List<IpAddress> ipAddresses = ipAddressService.selectIpByManagerId(managerId);
        result.put("data", ipAddresses);
        return result;
    }

}
