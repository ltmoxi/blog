package com.gbq.boot.web.service;

import com.gbq.boot.web.bean.Music;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author aqian666
 * @since 2019-12-11
 */
public interface MusicService extends IService<Music> {

    List<Music> selectList(Music music);
}
