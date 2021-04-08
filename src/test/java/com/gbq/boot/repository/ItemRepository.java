package com.gbq.boot.repository;
import com.gbq.boot.bean.Item;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.Collection;
import java.util.List;

/**
 * @Description:定义ItemRepository 接口
 * @Param:
 * 	Item:为实体类
 * 	Long:为Item实体类中主键的数据类型
 * @Author: https://blog.csdn.net/chen_2890
 * @Date: 2018/9/29 0:50
 */

public interface ItemRepository extends ElasticsearchRepository<Item,Long>{
    /**
     * @Description:根据价格区间查询  自定义查询
     * @Param price1
     * @Param price2
     * @Author: https://blog.csdn.net/chen_2890
     */
    List<Item> findByPriceBetween(double price1, double price2);

    List<Item> findByTitle(String title1);

    List<Item> findByTitleIn(Collection<String> ss);

}

