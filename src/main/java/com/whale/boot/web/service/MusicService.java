package com.whale.boot.web.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.whale.boot.web.bean.Music;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author litian
 * @since 2020-12-2
 */
public interface MusicService extends IService<Music> {

    List<Music> selectList(Music music);
}
