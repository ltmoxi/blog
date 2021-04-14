package com.whale.boot.web.utils.enums;

import java.util.ArrayList;
import java.util.List;

public enum ArticleEnum {

    TECHNICAL_EXCHANGE(1001, "springBoot"),
    CODE_SHARING(1002, "源码分享"),
    PROGRAMMED_LIFE(1003, "程序人生");

    private static final List<ArticleEnum> lists = new ArrayList<ArticleEnum>();

    static {
        lists.add(TECHNICAL_EXCHANGE);
        lists.add(CODE_SHARING);
        lists.add(PROGRAMMED_LIFE);
    }

    private Integer type;

    private String name;

    private ArticleEnum(Integer type, String name) {
        this.type = type;
        this.name = name;
    }

    public static List<ArticleEnum> getArticleEnum() {
        return lists;
    }

    public static String getNameByType(String type) {
        for (ArticleEnum codesEnum : ArticleEnum.values()) {
            if (codesEnum.getType().equals(type)) {
                return codesEnum.getName();
            }
        }
        return "";
    }

    public static Integer getIdByName(String name) {
        for (ArticleEnum codesEnum : ArticleEnum.values()) {
            if (codesEnum.getName().equals(name)) {
                return codesEnum.getType();
            }
        }
        return null;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
