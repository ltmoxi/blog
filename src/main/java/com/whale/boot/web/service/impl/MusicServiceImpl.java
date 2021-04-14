package com.whale.boot.web.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.whale.boot.web.bean.Music;
import com.whale.boot.web.mapper.MusicMapper;
import com.whale.boot.web.service.MusicService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author litian
 * @since 2020-12-2
 */
@Service
public class MusicServiceImpl extends ServiceImpl<MusicMapper, Music> implements MusicService {

    @Resource
    private MusicMapper musicMapper;

    @Override
    public List<Music> selectList(Music music) {
        QueryWrapper<Music> musicQueryWrapper = new QueryWrapper<>();
        musicQueryWrapper.lambda().eq(StringUtils.isNotBlank(music.getName()), Music::getName, music.getName())
                .eq(StringUtils.isNotBlank(music.getArtist()), Music::getArtist, music.getArtist());
        return musicMapper.selectList(musicQueryWrapper);
    }
}
