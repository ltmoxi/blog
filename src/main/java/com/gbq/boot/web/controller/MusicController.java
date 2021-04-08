package com.gbq.boot.web.controller;


import com.gbq.boot.web.bean.IpAddress;
import com.gbq.boot.web.bean.Music;
import com.gbq.boot.web.service.MusicService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author aqian666
 * @since 2019-12-11
 */
@RestController
@RequestMapping("/music")
public class MusicController {

    @Resource
    private MusicService musicService;

    /**
     * 分页查询
     */
    @GetMapping("/list")
    public HashMap<String, Object> getArticlePage(Music music, Integer  pageSize, Integer currentPage){
        HashMap<String,Object> result = new HashMap<>();
        if (pageSize != null && currentPage != null){
            PageHelper.startPage(currentPage, pageSize);
            List<Music> musicList= musicService.selectList(music);
            PageInfo<Music> pageInfo = new PageInfo<>(musicList);
            result.put("data",pageInfo);
        }else {
            List<Music>  articleList= musicService.selectList(music);
            result.put("data",articleList);
        }
        return result;
    }

}
