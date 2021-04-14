package com.whale.boot.web.utils.enums;

/**
 * @author litian
 */

public enum DataStatusEnum {
    // 数据状态
    STATUS_OVER(2, "完成"),
    STATUS_START(1, "启用"),
    STATUS_STOP(0, "停用");

    /**
     * 键
     */
    private int key;
    /**
     * 值
     */
    private String value;

    DataStatusEnum(int key, String value) {
        this.key = key;
        this.value = value;
    }

    public static String getValue(Integer key) {
        for (DataStatusEnum item : DataStatusEnum.values()) {
            if (key.equals(item.getKey())) {
                return item.value;
            }
        }
        return null;
    }

    public String getValue() {
        return value;
    }

    public int getKey() {
        return key;
    }
}
