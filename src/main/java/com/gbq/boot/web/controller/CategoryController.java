package com.gbq.boot.web.controller;

import com.gbq.boot.web.bean.Category;
import com.gbq.boot.web.service.CategoryService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.gbq.boot.web.utils.BusinessException;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author aqian666
 * @since 2019-09-12
 */
@RestController
@RequestMapping("/category")
public class CategoryController {
    @Resource
    private CategoryService categoryService;
    

    /**
     * 新增分类
     * @param category 分类内容
     * @return result
     */
    @ResponseBody
    @PostMapping("/save")
    public HashMap<String, Object> saveCategory(Category category){
        HashMap<String,Object> result = new HashMap<>();
        //新增分类
        categoryService.saveCategory(category);
        return result;
    }


    /**
     * 分页查询
     */
    @GetMapping("/list")
    public HashMap<String, Object> getCategoryPage(Category category, Integer pageSize, Integer currentPage){
        HashMap<String,Object> result = new HashMap<>();
        if (pageSize != null && currentPage != null){
            PageHelper.startPage(currentPage, pageSize);
            List<Category>  categorys= categoryService.getCategoryPage(category);
            PageInfo<Category> pageInfo = new PageInfo<>(categorys);
            result.put("data",pageInfo);
        }else {
            List<Category>  categorys= categoryService.getCategoryPage(category);
            result.put("data",categorys);
        }
        return result;
    }

    /**
     * 通过id删除
     */
    @DeleteMapping("/deletes")
    public HashMap<String, Object> delCategory(Integer[] ids) throws BusinessException {
        HashMap<String,Object> result = new HashMap<>();
        categoryService.delCategory(Arrays.asList(ids));
        return result;
    }
}
