package com.whale.boot.web.comment;

import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

/**
 *  * 自动填充处理类
 *  * @author litian
 *  * @version 1.0
 *  * @see
 *  
 **/
@Component
public class MybatisObjectHandler implements MetaObjectHandler {


    @Override
    public void insertFill(MetaObject metaObject) {
        this.setInsertFieldValByName("createTime", DateUtil.now(), metaObject)
                .setInsertFieldValByName("loginTime", DateUtil.now(), metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        this.setInsertFieldValByName("loginTime", DateUtil.now(), metaObject);
    }
}

