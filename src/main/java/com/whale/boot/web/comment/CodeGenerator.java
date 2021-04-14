//package com.whale.boot.web.comment;
//
//import com.baomidou.mybatisplus.core.toolkit.StringPool;
//import com.baomidou.mybatisplus.generator.AutoGenerator;
//import com.baomidou.mybatisplus.generator.InjectionConfig;
//import com.baomidou.mybatisplus.generator.config.*;
//import com.baomidou.mybatisplus.generator.config.po.TableInfo;
//import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
//import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
//
//import java.io.File;
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * @author litian
// */
//public class CodeGenerator {
//
//    public static void main(String[] args) {
//        // 代码生成器
//        AutoGenerator mpg = new AutoGenerator();
//        // 数据源配置
//        DataSourceConfig dsc = new DataSourceConfig();
//        dsc.setUrl("jdbc:mysql://47.105.100.29:3306/blog?useUnicode=true&useSSL=false&characterEncoding=utf8&serverTimezone=UTC&rewriteBatchedStatements=true");
//        dsc.setDriverName("com.mysql.cj.jdbc.Driver");
//        // dsc.setDriverName("com.mysql.jdbc.Driver"); //mysql5.6以下的驱动
//        dsc.setUsername("root");
//        dsc.setPassword("Aqian666.@!");
//        mpg.setDataSource(dsc);
//
//        // 全局配置
//        GlobalConfig gc = new GlobalConfig();
//        String projectPath = System.getProperty("user.dir");
//
//        //作者名称
//        gc.setAuthor("aqian666");
//        gc.setOpen(false);
//
//        // 自定义文件命名，注意 %s 会自动填充表实体属性！
//        gc.setControllerName("%sController");
//        gc.setServiceName("%sService");
//        gc.setServiceImplName("%sServiceImpl");
//        gc.setMapperName("%sMapper");
//        gc.setXmlName("%sMapper");
//        mpg.setGlobalConfig(gc);
//
//        // gc.setSwagger2(true); 实体属性 Swagger2 注解
//        gc.setFileOverride(true);
//        // 不需要ActiveRecord特性的请改为false
//        gc.setActiveRecord(false);
//        // XML 二级缓存
//        gc.setEnableCache(false);
//        // XML ResultMap
//        gc.setBaseResultMap(true);
//        // XML columList
//        gc.setBaseColumnList(false);
//        // 作者
//        gc.setAuthor("aqian666");
//
//        // 包配置
//        PackageConfig pc = new PackageConfig();
//        pc.setParent("com.whale.boot.web");
////        pc.setModuleName("model名"); 自定义包名
//        //dao
//        pc.setMapper("mapper");
//        //servcie
//        pc.setService("service");
//        //controller
//        pc.setController("controller");
//        pc.setEntity("bean");
//
//
//
//        // 自定义配置
//        InjectionConfig cfg = new InjectionConfig() {
//            @Override
//            public void initMap() {
//            }
//        };
//
//        // 模板引擎是 freemarker
//        // 自定义controller的代码模板
//        // 如果模板引擎是 velocity
//        // String templatePath = "/templates/mapper.xml.vm";
//        String templatePath = "/templates/mapper.xml.ftl";
//        // 自定义输出配置
//        List<FileOutConfig> focList = new ArrayList<>();
//
//        // 自定义配置会被优先输出,配置mapper.xml
//        focList.add(new FileOutConfig(templatePath) {
//            @Override
//            public String outputFile(TableInfo tableInfo) {
//                // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
//               /* return projectPath + "/src/main/resources/mappers/" + pc.getModuleName()
//                        + "/" + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;*/
//                //根据自己的位置修改
//                return projectPath + "/boot-whale-dao/src/main/resources/mapper/" +tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
//            }
//        });
//
//        //控制层
//        templatePath = "/templates/controller.java.ftl";
//        // 自定义配置会被优先输出
//        focList.add(new FileOutConfig(templatePath) {
//            @Override
//            public String outputFile(TableInfo tableInfo) {
//                // 自定义输出文件名 + pc.getModuleName()
//                String expand = projectPath + "/boot-whale-web/src/main/java/com/whale/boot/web/" + "controller";
//                return String.format((expand + File.separator + "%s" + ".java"), tableInfo.getControllerName());
//            }
//        });
//
//        //业务层
//        templatePath = "/templates/service.java.ftl";
//        // 自定义配置会被优先输出
//        focList.add(new FileOutConfig(templatePath) {
//            @Override
//            public String outputFile(TableInfo tableInfo) {
//                String expand = projectPath + "/boot-whale-service/src/main/java/com/whale/boot/web/" + "service";
//                return String.format((expand + File.separator + "%s" + ".java"), tableInfo.getServiceName());
//            }
//        });
//        templatePath = "/templates/serviceImpl.java.ftl";
//        // 自定义配置会被优先输出
//        focList.add(new FileOutConfig(templatePath) {
//            @Override
//            public String outputFile(TableInfo tableInfo) {
//                String expand = projectPath + "/boot-whale-service/src/main/java/com/whale/boot/web/" + "service/impl";
//                return String.format((expand + File.separator + "%s" + ".java"), tableInfo.getServiceImplName());
//            }
//        });
//
//        //数据层
//        templatePath = "/templates/mapper.java.ftl";
//        // 自定义配置会被优先输出
//        focList.add(new FileOutConfig(templatePath) {
//            @Override
//            public String outputFile(TableInfo tableInfo) {
//                String expand = projectPath + "/boot-whale-dao/src/main/java/com/whale/boot/web/"+"mapper";
//                return String.format((expand + File.separator + "%s" + ".java"), tableInfo.getMapperName());
//            }
//        });
//
//        //数据层
//        templatePath = "/templates/entity.java.ftl";
//        // 自定义配置会被优先输出
//        focList.add(new FileOutConfig(templatePath) {
//            @Override
//            public String outputFile(TableInfo tableInfo) {
//                String expand = projectPath + "/boot-whale-dao/src/main/java/com/whale/boot/web/"+"bean";
//                return String.format((expand + File.separator + "%s" + ".java"), tableInfo.getEntityName());
//            }
//        });
//
//        mpg.setPackageInfo(pc);
//        cfg.setFileOutConfigList(focList);
//        mpg.setCfg(cfg);
//
//        // 配置模板
//        TemplateConfig templateConfig = new TemplateConfig();
//
//        // 配置自定义输出模板
//        //指定自定义模板路径，注意不要带上.ftl/.vm, 会根据使用的模板引擎自动识别
//        templateConfig.setController(null);
//        //此处设置为null，就不会再java下创建xml的文件夹了
//        templateConfig.setXml(null);
//        mpg.setTemplate(templateConfig);
//
//        // 策略配置
//        StrategyConfig strategy = new StrategyConfig();
//        strategy.setNaming(NamingStrategy.underline_to_camel);
//        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
//        strategy.setEntityLombokModel(true);
//        strategy.setRestControllerStyle(true);
//
//        //表名
//        strategy.setInclude("blog_music");
//        strategy.setControllerMappingHyphenStyle(true);
//        //根据你的表名来建对应的类名，如果你的表名没有什么下划线，比如test，那么你就可以取消这一步
//        strategy.setTablePrefix("blog_");
//        mpg.setStrategy(strategy);
//        mpg.setTemplateEngine(new FreemarkerTemplateEngine());
//        mpg.execute();
//    }
//}
