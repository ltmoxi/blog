package com.whale.boot.web.config.shiro;

import cn.hutool.crypto.SecureUtil;
import com.whale.boot.web.bean.Manager;
import com.whale.boot.web.service.ManagerService;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;


/**
 * @author litian
 */
public class MyShiroRealm extends AuthorizingRealm {

    @Autowired
    private ManagerService managerService;
    @Autowired
    private HttpServletRequest request;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //获取用户信息
        String username = authenticationToken.getPrincipal().toString();
        String password = new String((char[]) authenticationToken.getCredentials());
        Manager manager = managerService.findByName(username);

        //验证登录账号
        if (manager == null) {
            throw new UnknownAccountException("用户名不存在");
        }
        //验证密码
        if (!StringUtils.equals(manager.getPassword(), SecureUtil.md5(password))) {
            throw new UnknownAccountException("密码错误，请重新输入");
        }

//        return new SimpleAuthenticationInfo(manager, SecureUtil.md5(manager.getPassword()), ByteSource.Util.bytes(SecureUtil.md5(manager.getPassword())),getName());
        return new SimpleAuthenticationInfo(manager, password, getName());
    }

}
