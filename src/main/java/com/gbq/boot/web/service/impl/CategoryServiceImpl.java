package com.gbq.boot.web.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gbq.boot.web.bean.Category;
import com.gbq.boot.web.mapper.CategoryMapper;
import com.gbq.boot.web.service.CategoryService;
import com.gbq.boot.web.utils.BusinessException;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {
    @Resource
    private CategoryMapper categoryMapper;

    @Override
    public List<Category> findAll() {
        QueryWrapper<Category> queryWrapper = new QueryWrapper<>();
        return categoryMapper.selectList(queryWrapper);
    }

    @Override
    public void saveCategory(Category category) {
        if(category.getId()==null){
            QueryWrapper<Category> queryWrapper = new QueryWrapper<>();
            int count = categoryMapper.selectCount(queryWrapper);
            if(count>5){
                throw new BusinessException("菜单栏最多五个");
            }
            categoryMapper.insert(category);
        }else {
            categoryMapper.updateById(category);
        }


    }

    @Override
    public void delCategory(List<Integer> asList) {
        categoryMapper.deleteBatchIds(asList);
    }

    @Override
    public List<Category> getCategoryPage(Category category) {
        QueryWrapper<Category> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda()
                .like(StringUtils.isNotBlank(category.getName()), Category::getName, category.getName());

        return categoryMapper.selectList(queryWrapper);
    }
}
