package com.whale.boot.web.bean;

import com.baomidou.mybatisplus.annotation.TableName;
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
@TableName("blog_music")
public class Music implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    /**
     * 音乐名称
     */
    private String name;

    /**
     * 作者
     */
    private String artist;

    /**
     * 歌曲来源
     */
    private String url;

    /**
     * 歌曲封面
     */
    private String cover;

    /**
     * 歌词
     */
    private String lrc;


}
