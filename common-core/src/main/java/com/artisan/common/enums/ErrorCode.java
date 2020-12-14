package com.artisan.common.enums;

/**
 * 错误编码枚举
 *
 * @author xz man
 * @since 1.8
 */
public enum ErrorCode implements BaseEnum<Integer>{
    /**
     * 参数错误
     */
    INVALID_ARGUMENT(501, "参数错误");

    private final Integer code;

    private final String desc;

    ErrorCode(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    @Override
    public Integer getCode() {
        return code;
    }

    @Override
    public String getDesc() {
        return desc;
    }
}
