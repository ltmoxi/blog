package com.gbq.boot.web.utils;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : Latent
 * @createdDate : 2019/8/12
 * @updatedDate
 */
@Data
public class BaseTreeNode {
    /**
     * 子Id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Object id;
    /**
     * 父ID
     */

    /**
     * 被评论人
     */
    @TableField("pid")
    private Object pid;

    @TableField(exist=false)
    private List<BaseTreeNode> replyComments;

    public BaseTreeNode() {
    }


    public List<BaseTreeNode> getReplyComments() {
        return this.replyComments;
    }

    public void setReplyComments(List<BaseTreeNode> child) {
        this.replyComments = child;
    }

    public void addReplyComments(BaseTreeNode baseTreeNode) {
        if (this.replyComments == null) {
            this.setReplyComments(new ArrayList());
        }

        this.getReplyComments().add(baseTreeNode);
    }
}
