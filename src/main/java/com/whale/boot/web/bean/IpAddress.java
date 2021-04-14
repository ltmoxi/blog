package com.whale.boot.web.bean;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 *
 * </p>
 *
 * @author litian
 * @since 2020-12-2
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("blog_ip_address")
public class IpAddress implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField("managerId")
    private Integer managerId;

    /**
     * 访问ip
     */
    private String ip;

    /**
     * 状态
     */
    private Integer status;

    /**
     * 访问时间
     */
    @TableField(value = "loginTime", fill = FieldFill.INSERT_UPDATE)
    private String loginTime;

    @TableField(exist = false)
    private String managerName;
}
