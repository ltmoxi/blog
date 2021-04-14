package com.whale.boot.web.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author litian
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("blog_manager")
public class Manager implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 名称
     */
    private String name;

    /**
     * 用户名 邮箱
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 临时密码
     */
    @TableField("agiPassword")
    private String agiPassword;

    /**
     * 头像
     */
    @TableField("headPic")
    private String headPic;

    /**
     * @since 2020-12-2
     */
    @TableField("createTime")
    private String createTime;

    /**
     * 状态
     */
    private Integer status;

    /**
     * 角色  超级管理员 管理员 博主
     */
    private String type;

    /**
     * 验证码
     */
    @TableField(exist = false)
    private Integer code;

}