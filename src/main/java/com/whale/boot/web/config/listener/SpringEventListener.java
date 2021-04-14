package com.whale.boot.web.config.listener;

import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.ApplicationContext;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * @author litian
 * @since 2020-12-2
 */
@Component
public class SpringEventListener {
    @EventListener
    public void handleContextRefresh(ContextRefreshedEvent event) {
        ApplicationContext context = event.getApplicationContext();
        DefaultWebSecurityManager manager = (DefaultWebSecurityManager) context.getBean("securityManager");
        AuthorizingRealm realm = (AuthorizingRealm) context.getBean("userRealm");
        manager.setRealm(realm);
    }
}
