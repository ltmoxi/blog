package com.gbq.boot.web.bean;

import cn.hutool.core.date.DateTime;
import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author aqian666
 * @since 2019-10-31
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
    @TableField(value = "loginTime",fill = FieldFill.INSERT_UPDATE)
    private String loginTime;

    @TableField(exist=false)
    private String managerName;
}
