package com.gbq.boot.web.bean;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;

/**
 * @author aqian666
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("blog_article")
@Document(indexName = "article",type = "docs", shards = 1, replicas = 0)
public class Article implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 文章标题
     */
    @Field(type = FieldType.Text, analyzer = "ik_max_word")
    @TableField("articleName")
    private String articleName;

    /**
     * 文章封面
     */
    @Field(type = FieldType.Text)
    @TableField("articleHeadPic")
    private String articleHeadPic;

    /**
     * 文章标签
     */
    @Field(type = FieldType.Keyword)
    @TableField("articleTag")
    private String articleTag;

    /**
     * 文章备注
     */
    @Field(type = FieldType.Keyword)
    @TableField("articleRemark")
    private String articleRemark;

    /**
     * 文章阅读量
     */
    @TableField("articleReadCount")
    private Integer articleReadCount;

    /**
     * 文章审核状态 0：未通过；1：通过
     */
    @Field(type = FieldType.Integer)
    @TableField("articleState")
    private Integer articleState;

    /**
     * 文章内容
     */
    @Field(type = FieldType.Text, analyzer = "ik_max_word")
    @TableField("articleContent")
    private String articleContent;

    /**
     * 文章作者id
     */
    @Field(type = FieldType.Integer)
    @TableField("managerId")
    private Integer managerId;

    /**
     * 作者名称
     */
    @Field(type = FieldType.Keyword)
    @TableField("managerName")
    private String managerName;

    /**
     *创建时间
     */
    @Field(type = FieldType.Keyword)
    @TableField(value = "createTime",fill = FieldFill.INSERT)
    private String createTime;

    @TableField("articleType")
    private Integer articleType;

    @TableField("articleStarNum")
    private Integer articleStarNum;

    @TableField("articleConNum")
    private Integer articleConNum;

    @TableField("enclosure")
    private String enclosure;

    /**
     * 最新
     */
    @TableField(exist=false)
    private boolean latest;
    /**
     * 点赞
     */
    @TableField(exist=false)
    private boolean favorite;
    /**
     * 评论
     */
    @TableField(exist=false)
    private boolean commentMost;
    /**
     * 阅读量
     */
    @TableField(exist=false)
    private boolean recommend;

    public String[] getTags(){
        if (articleTag != null){
            String[] split = articleTag.split(",");
            return  split;
        }
        return null;
    }



}