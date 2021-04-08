package com.gbq.boot.web.controller;

import com.gbq.boot.web.bean.Manager;
import com.gbq.boot.web.service.IpAddressService;
import com.gbq.boot.web.service.ManagerService;
import com.gbq.boot.web.config.shiro.MySessionManager;
import com.gbq.boot.web.utils.BusinessException;
import com.gbq.boot.web.utils.ConstUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;


/**
 * 登录Controller
 * @author 阿前
 * 2019年1月4日09:48:21
 */
@RestController
@RequestMapping("")
public class LoginController {

    @Resource
    private ManagerService managerService;
    @Resource
    private IpAddressService ipAddressService;


    /*
     * 响应验证码页面
     * @return
     */
   /* @ResponseBody
    @RequestMapping(value="/validateCode", method = RequestMethod.GET)
    public String validateCode(HttpServletRequest request,HttpServletResponse response) throws Exception{
        // 设置响应的类型格式为图片格式
        response.setContentType("image/jpeg");
        //禁止图像缓存。
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);

        HttpSession session = request.getSession();

        ValidateCode vCode = new ValidateCode(120,40,5,100);
        session.setAttribute("code", vCode.getCode());
        vCode.write(response.getOutputStream());
        return null;
    } */

    /**
     * 向邮箱发送验证码 邮箱便是以后的用户名
     * @param username 用户名
     * @return result
     */
    @PostMapping("/login/sendOutEmail")
    public HashMap<String, Object> sendOutEmail(String username){
        HashMap<String,Object> result = new HashMap<>();
        //发送邮箱信息
        managerService.sendOutEmail(username);
        return  result;
    }

    /**
     * 登录
     * @param loginForm  登录人信息
     * @param bindingResult 参数验证
     * @return result
     */
    @PostMapping("/login/login")
    public HashMap<String, Object> loginVer(Manager loginForm, BindingResult bindingResult) {
        HashMap<String,Object> result = new HashMap<>();
        try {
            if (bindingResult.hasErrors()) {
                List<ObjectError> allErrors = bindingResult.getAllErrors();
                for (ObjectError error : allErrors) {
                    result.put("error", error);
                }
                return result;
            }
            Subject subject = SecurityUtils.getSubject();
            //使用户的登录信息创建令牌
            UsernamePasswordToken token = new UsernamePasswordToken(loginForm.getUsername(), loginForm.getPassword());
            //登录验证
            subject.login(token);

            Object object = subject.getPrincipal();
            Manager manager = (Manager) object;

            if (manager.getType().equals(ConstUtil.BOLG_MIN)){
                throw new BusinessException("博主无权限登录后台");
            }

            //更新ip
            ipAddressService.update(manager.getId());

            result.put("manager",manager);
            result.put(MySessionManager.AUTHORIZATION, subject.getSession().getId());
        } catch (UnknownAccountException e) {
            result.put("error",e.getMessage());
            result.put("status",-1);
            return result;
        }
        return result;
    }

    /**
     * 登录
     * @param loginForm  登录人信息
     * @param bindingResult 参数验证
     * @return result
     */
    @PostMapping("/login")
    public HashMap<String, Object> login(Manager loginForm, BindingResult bindingResult) {
        HashMap<String,Object> result = new HashMap<>();
        try {
            if (bindingResult.hasErrors()) {
                List<ObjectError> allErrors = bindingResult.getAllErrors();
                for (ObjectError error : allErrors) {
                    result.put("error", error);
                }
                return result;
            }
            Subject subject = SecurityUtils.getSubject();
            //使用户的登录信息创建令牌
            UsernamePasswordToken token = new UsernamePasswordToken(loginForm.getUsername(), loginForm.getPassword());
            //登录验证
            subject.login(token);

            Object object = subject.getPrincipal();
            Manager manager = (Manager) object;

            //更新ip
            ipAddressService.update(manager.getId());

            result.put("manager",manager);
            result.put(MySessionManager.AUTHORIZATION, subject.getSession().getId());
        } catch (UnknownAccountException e) {
            result.put("error",e.getMessage());
            result.put("status",-1);
            return result;
        }
        return result;
    }

    /**
     * 博主注册
     * @param manager 注册用户信息
     * @return result
     */
    @PostMapping("/register")
    public HashMap<String, Object> register(Manager manager) {
        HashMap<String,Object> result = new HashMap<>();
        manager.setType("博主");
        Manager manager1 = managerService.saveManager(manager);
        result.put("manager",manager1);
        return result;
    }

   /**
     * 返回未登录状态码
     */
    @RequestMapping("/login/noLogin")
    public HashMap<String, Object> noLogin() {
        HashMap<String,Object> result = new HashMap<>();
        SecurityUtils.getSubject().logout();
        result.put("error","未登录");
        result.put("status",1);
        return result;
    }

}
