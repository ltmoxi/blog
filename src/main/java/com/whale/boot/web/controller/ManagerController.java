package com.whale.boot.web.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.whale.boot.web.bean.Manager;
import com.whale.boot.web.service.ManagerService;
import com.whale.boot.web.utils.enums.DataStatusEnum;
import org.apache.shiro.SecurityUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;


/**
 * @author litian
 */
@RestController
@RequestMapping("/manager")
public class ManagerController {

    @Resource
    private ManagerService managerService;

    /**
     * 获取当前登录人
     */
    @GetMapping("/getCurrentManager")
    public HashMap<String, Object> getCurrentManager() {
        HashMap<String, Object> result = new HashMap<>();
        Manager manager = (Manager) SecurityUtils.getSubject().getPrincipal();
        result.put("data", manager);
        return result;
    }

    /**
     * 分页查询
     */
    @ResponseBody
    @GetMapping("/list")
    public HashMap<String, Object> getArticlePage(Manager manager, Integer pageSize, Integer currentPage) {
        HashMap<String, Object> result = new HashMap<>();
        if (pageSize != null && currentPage != null) {
            PageHelper.startPage(currentPage, pageSize);
            List<Manager> articleList = managerService.getManagerByPage(manager);
            PageInfo<Manager> pageInfo = new PageInfo<>(articleList);
            result.put("data", pageInfo);
        } else {
            List<Manager> articleList = managerService.getManagerByPage(manager);
            result.put("data", articleList);
        }
        return result;
    }

    /**
     * 通过id删除
     */
    @DeleteMapping("/delete")
    public HashMap<String, Object> delManager(Integer[] ids) {
        HashMap<String, Object> result = new HashMap<>();
        managerService.delManager(Arrays.asList(ids));
        return result;
    }

    /**
     * 批量禁用用户
     */
    @PutMapping("/stop")
    public HashMap<String, Object> stopManager(Integer[] ids) {
        HashMap<String, Object> result = new HashMap<>();
        managerService.stopManager(Arrays.asList(ids), DataStatusEnum.STATUS_STOP.getKey());
        return result;
    }

    /**
     * 批量启用用户
     */
    @PutMapping("/star")
    public HashMap<String, Object> starManager(Integer[] ids) {
        HashMap<String, Object> result = new HashMap<>();
        managerService.stopManager(Arrays.asList(ids), DataStatusEnum.STATUS_START.getKey());
        return result;
    }

    /**
     * 添加管理员
     *
     * @param manager 注册用户信息
     * @return result
     */
    @PostMapping("/save")
    public HashMap<String, Object> save(Manager manager) {
        HashMap<String, Object> result = new HashMap<>();
        manager.setType("管理员");
        Manager manager1 = managerService.saveManager(manager);
        result.put("manager", manager1);
        return result;
    }

    /**
     * 修改管理员
     *
     * @param manager 注册用户信息
     * @return result
     */
    @PostMapping("/update")
    public HashMap<String, Object> update(Manager manager) {
        HashMap<String, Object> result = new HashMap<>();
        Manager manager1 = managerService.updateManager(manager);
        result.put("manager", manager1);
        return result;
    }
}
