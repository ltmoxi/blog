package com.gbq.boot.web.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gbq.boot.web.bean.Category;

import java.util.List;

public interface CategoryService extends IService<Category> {

    List<Category> findAll();

    void saveCategory(Category category);

    void delCategory(List<Integer> asList);

    List<Category> getCategoryPage(Category category);
}
