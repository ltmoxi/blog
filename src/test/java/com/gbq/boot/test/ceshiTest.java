package com.gbq.boot.test;

import com.gbq.boot.web.BootApplication;
import com.gbq.boot.repository.ItemRepository;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.terms.StringTerms;
import org.elasticsearch.search.aggregations.metrics.avg.InternalAvg;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.aggregation.AggregatedPage;
import org.springframework.data.elasticsearch.core.query.FetchSourceFilter;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.test.context.junit4.SpringRunner;
import com.gbq.boot.bean.Item;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = BootApplication.class)
public class ceshiTest {
    @Autowired
    private ItemRepository itemRepository;

    /**
     * @Description:定义新增方法
     * @Author: https://blog.csdn.net/chen_2890
     */
    @Test
    public void insert() {
        Item item = new Item(1L, "小米手机7", " 手机",
                "小米", 3499.00);
        itemRepository.save(item);
    }



    /**
     * @Description:定义批量新增方法
     * @Author: https://blog.csdn.net/chen_2890
     */
    @Test
    public void insertList() {
        List<Item> list = new ArrayList<>();
        list.add(new Item(1L, "小米9", "手机", "小米", 3299.00));
        list.add(new Item(2L, "华为pro30", "手机", "华为", 3999.00));
        list.add(new Item(3L, "一加7", "手机", "一加", 2999.00));
        list.add(new Item(4L, "魅族16", "手机", "魅族", 1999.00));
        list.add(new Item(5L, "苹果xs", "手机", "苹果", 5099.00));
        list.add(new Item(6L, "360pro", "手机", "360", 1099.00));
        list.add(new Item(7L, "荣耀V10", "手机", "华为", 899.00 ));
        // 接收对象集合，实现批量新增
        itemRepository.saveAll(list);
    }

    /**
     * @Description:按照价格区间查询  自定义方法
     * 自定义方法
        Spring Data 的另一个强大功能，是根据方法名称自动实现功能。
        比如：你的方法名叫做：findByTitle，那么它就知道你是根据title查询，然后自动帮你完成，无需写实现类。
        当然，方法名称要符合一定的约定  下边为约定
            And	findByNameAndPrice
            Or	findByNameOrPrice
            Is	findByName
            Not	findByNameNot
            Between	findByPriceBetween
            LessThanEqual	findByPriceLessThan
            GreaterThanEqual	findByPriceGreaterThan
            Before	findByPriceBefore
            After	findByPriceAfter
            Like	findByNameLike
            StartingWith	findByNameStartingWith
            EndingWith	findByNameEndingWith
            Contains/Containing	findByNameContaining
            In	findByNameIn(Collection<String>names)
            NotIn	findByNameNotIn(Collection<String>names)
            Near	findByStoreNear
            True	findByAvailableTrue
            False	findByAvailableFalse
            OrderBy	findByAvailableTrueOrderByNameDesc
     * @Author: https://blog.csdn.net/chen_2890
     */
    @Test
    public void queryByPriceBetween(){
        List<Item> list = this.itemRepository.findByPriceBetween(2000.00, 3500.00);
        for (Item item : list) {
            System.out.println("item = " + item.getTitle());
        }
    }


    @Test
    public void queryByTitle(){
        List<Item> list = this.itemRepository.findByTitle("华为");
        for (Item item : list) {
            System.out.println("item = " + item.getTitle());
        }
    }

    @Test
    public void queryByTitleTo(){
        Collection<String> ss =  new ArrayList<>();
        ss.add("华为");
        ss.add("小米");
        List<Item> list = this.itemRepository.findByTitleIn(ss);
        for (Item item : list) {
            System.out.println("item = " + item.getTitle());
        }
    }

    /**
     * @Description:matchQuery底层采用的是词条匹配查询
     * @Author: https://blog.csdn.net/chen_2890
     */
    @Test
    public void testMatchQuery(){
        // 构建查询条件
        NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();
        // 添加基本分词查询
        queryBuilder.withQuery(QueryBuilders.matchQuery("title", "华为"));
        // 搜索，获取结果
        Page<Item> items = this.itemRepository.search(queryBuilder.build());
        // 总条数
        long total = items.getTotalElements();
        System.out.println("获取的总条数 = " + total);
        for (Item item : items) {
            System.out.println("手机名称是："+item.getTitle());
        }
    }


    /**
     * @Description:
     * termQuery:功能更强大，除了匹配字符串以外，还可以匹配
     * int/long/double/float/....
     * @Author: https://blog.csdn.net/chen_2890
     */
    @Test
    public void testTermQuery(){
        NativeSearchQueryBuilder builder = new NativeSearchQueryBuilder();
        builder.withQuery(QueryBuilders.termQuery("price",1099));
        // 查找
        Page<Item> page = this.itemRepository.search(builder.build());

        for(Item item:page){
            System.out.println("手机是："+item.getTitle());
        }
    }


    /**
     * @Description:布尔查询  多条件查询
     * @Author: https://blog.csdn.net/chen_2890
     */
    @Test
    public void testBlQuery(){
        NativeSearchQueryBuilder builder = new NativeSearchQueryBuilder();

        builder.withQuery(
                QueryBuilders.boolQuery().should(QueryBuilders.matchQuery("title","荣耀"))
                                         .should(QueryBuilders.matchQuery("title","一"))
                                         .should(QueryBuilders.matchQuery("brand","小米"))
        );

        // 查找
        Page<Item> page = this.itemRepository.search(builder.build());
        for(Item item:page){
            System.out.println("手机名称是"+item.getTitle());
        }
    }
    /**
     * @Description:模糊查询
     * must 相当于and，就是都满足
     * should 相当于or，满足一个或多个
     * @Author: https://blog.csdn.net/chen_2890
     */
    @Test
    public void testFuzzyQuery(){
        NativeSearchQueryBuilder builder = new NativeSearchQueryBuilder();
        builder.withQuery(QueryBuilders.fuzzyQuery("title","一"));
        Page<Item> page = this.itemRepository.search(builder.build());
        for(Item item:page){
            System.out.println("手机名称是："+item.getTitle());
        }

    }

    /**
     * @Description: 多条件模糊查询
     * must 相当于and，就是都满足
     * should 相当于or，满足一个或多个
     * @Author: https://blog.csdn.net/chen_2890
     */
    @Test
    public void testFuzzyQuerys(){
        NativeSearchQueryBuilder builder = new NativeSearchQueryBuilder();
        builder.withQuery(
                QueryBuilders.boolQuery().should(QueryBuilders.fuzzyQuery("title","荣耀"))
                                         .should(QueryBuilders.fuzzyQuery("title","一"))
                                         .should(QueryBuilders.fuzzyQuery("brand","小米"))
        );
        Page<Item> page = this.itemRepository.search(builder.build());
        List<Item> content = page.getContent();
        for(Item item:content){
            System.out.println("手机名称是："+item.getTitle());
        }

    }

    /**
     * @Description:分页查询
     * @Author: https://blog.csdn.net/chen_2890
     */
    @Test
    public void searchByPage(){
        // 构建查询条件
        NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();
        // 添加基本分词查询
        queryBuilder.withQuery(QueryBuilders.termQuery("category", "手机"));
        // 分页：
        int page = 0;
        int size = 2;
        queryBuilder.withPageable(PageRequest.of(page,size));

        // 搜索，获取结果
        Page<Item> items = this.itemRepository.search(queryBuilder.build());
        // 总条数
        long total = items.getTotalElements();
        System.out.println("总条数 = " + total);
        // 总页数
        System.out.println("总页数 = " + items.getTotalPages());
        // 当前页
        System.out.println("当前页：" + items.getNumber());
        // 每页大小
        System.out.println("每页大小：" + items.getSize());

        for (Item item : items) {
            System.out.println(item.getTitle());
        }
    }

    /**
     * @Description:排序查询
     * @Author: https://blog.csdn.net/chen_2890
     */
    @Test
    public void searchAndSort(){
        // 构建查询条件
        NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();
        // 添加基本分词查询
        queryBuilder.withQuery(QueryBuilders.termQuery("category", "手机"));

        // 排序
        queryBuilder.withSort(SortBuilders.fieldSort("price").order(SortOrder.DESC));

        // 搜索，获取结果
        Page<Item> items = this.itemRepository.search(queryBuilder.build());
        // 总条数
        long total = items.getTotalElements();
        System.out.println("总条数 = " + total);

        for (Item item : items) {
            System.out.println("手机的价格是："+item.getTitle()+":"+item.getPrice());
        }
    }

    /**
     * @Description:按照品牌brand进行分组
     * @Author: https://blog.csdn.net/chen_2890
     */
    @Test
    public void testAgg(){
        NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();
        // 不查询任何结果
        queryBuilder.withSourceFilter(new FetchSourceFilter(new String[]{""}, null));
        // 1、添加一个新的聚合，聚合类型为terms，聚合名称为brands，聚合字段为brand
        queryBuilder.addAggregation(
                AggregationBuilders.terms("brands").field("brand"));
        // 2、查询,需要把结果强转为AggregatedPage类型
        AggregatedPage<Item> aggPage = (AggregatedPage<Item>) this.itemRepository.search(queryBuilder.build());
        // 3、解析
        // 3.1、从结果中取出名为brands的那个聚合，
        // 因为是利用String类型字段来进行的term聚合，所以结果要强转为StringTerm类型
        StringTerms agg = (StringTerms) aggPage.getAggregation("brands");
        // 3.2、获取桶
        List<StringTerms.Bucket> buckets = agg.getBuckets();
        // 3.3、遍历
        for (StringTerms.Bucket bucket : buckets) {
            // 3.4、获取桶中的key，即品牌名称
            System.out.println(bucket.getKeyAsString());
            // 3.5、获取桶中的文档数量
            System.out.println(bucket.getDocCount());
        }

    }


    /**
     * @Description:嵌套聚合，求平均值
     * @Author: https://blog.csdn.net/chen_2890
     */
    @Test
    public void testSubAgg(){
        NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();
        // 不查询任何结果
        queryBuilder.withSourceFilter(new FetchSourceFilter(new String[]{""}, null));
        // 1、添加一个新的聚合，聚合类型为terms，聚合名称为brands，聚合字段为brand
        queryBuilder.addAggregation(
                AggregationBuilders.terms("brands").field("brand")
                        .subAggregation(AggregationBuilders.avg("priceAvg").field("price")) // 在品牌聚合桶内进行嵌套聚合，求平均值
        );
        // 2、查询,需要把结果强转为AggregatedPage类型
        AggregatedPage<Item> aggPage = (AggregatedPage<Item>) this.itemRepository.search(queryBuilder.build());
        // 3、解析
        // 3.1、从结果中取出名为brands的那个聚合，
        // 因为是利用String类型字段来进行的term聚合，所以结果要强转为StringTerm类型
        StringTerms agg = (StringTerms) aggPage.getAggregation("brands");
        // 3.2、获取桶
        List<StringTerms.Bucket> buckets = agg.getBuckets();
        // 3.3、遍历
        for (StringTerms.Bucket bucket : buckets) {
            // 3.4、获取桶中的key，即品牌名称  3.5、获取桶中的文档数量
            System.out.println(bucket.getKeyAsString() + "，共" + bucket.getDocCount() + "台");

            // 3.6.获取子聚合结果：
            InternalAvg avg = (InternalAvg) bucket.getAggregations().asMap().get("priceAvg");
            System.out.println("平均售价：" + avg.getValue());
        }

    }


}
