package com.whale.boot.web.controller;


import com.whale.boot.web.bean.FriendAddress;
import com.whale.boot.web.service.FriendAddressService;
import org.springframework.web.bind.annotation.GetMapping;
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
@RequestMapping("/friend-address")
public class FriendAddressController {

    @Resource
    private FriendAddressService friendAddressService;

    @GetMapping("getFriendAddress")
    public HashMap<String, Object> getFriendAddress() {
        HashMap<String, Object> result = new HashMap<>();
        List<FriendAddress> friendAddresses = friendAddressService.list();
        result.put("data", friendAddresses);
        return result;
    }

}
