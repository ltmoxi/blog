package com.gbq.boot.web.utils;

/**
 * @author morty
 */
public class ConstUtil {

    /**
     * 文章id为1
     */
    public static final int ARTICLE_ID = 1;
    /**
     * 默认顶级父id
     */
    public static final int DEFAULT_PARENT_ID = 0;
    /**
     * 默认状态
     */
    public static final int DEFAULT_STATE = 1;
    /**
     * 其他状态
     */
    public static final int OTHER_STATE = 0;
    /**
     * 管理员角色类型
     */
    public static final int ADMIN_ROLE_TYPE = 1;
    /**
     * 其他角色类型
     */
    public static final int OTHER_ROLE_TYPE = 2;
    /**
     * 超级管理员
     */
    public static final String SUPER_ADMIN_NAME = "admin";
    /**
     * 超级管理员
     */
    public static final String SUPER_ADMIN = "超级管理员";

    /**
     * 管理员
     */
    public static final String ADMIN = "管理员";

    /**
     * 博主
     */
    public static final String BOLG_MIN = "博主";
    /**
     * 初始密码 or 重置密码
     */
    public static final String INITIAL_CIPHER = "111111";
    /**
     * session中的key
     */
    public static final String CURRENT_USER = "current_user";
    /**
     * 查看集合时候tableId的值
     */
    public static final String TABLE_ID = "0";
    /**
     * 层次模型
     */
    public static final String LOGIN_MODEL = "login_model";
    /**
     * 登录信息
     */
    public static final String LOGIN_USER_INFO = "login_user_info";
    /**
     * 基本日期格式
     */
    public static final String DATE_FORMAT_DEFAULT = "yyyy-MM-dd-HH-mm";



    /**
     * 获取多个汉字中间无空格
     * @param size 汉字个数
     * @return
     */
    public static String getRandomHanZiNoSpace(int size) {

        if (size<=0){
            size=1;
        }
        StringBuffer stringBuffer = new StringBuffer();

        for (int i = 0; i < size; i++) {
            stringBuffer.append(getRandomHanZi());
        }
        return stringBuffer.toString();
    }

    /**
     * 返回一个汉字
     * @param
     * @return
     */
    public static char getRandomHanZi() {
        return (char) (0x4e00 + (int) (Math.random() * (0x9fa5 - 0x4e00 + 1)));
    }
}

