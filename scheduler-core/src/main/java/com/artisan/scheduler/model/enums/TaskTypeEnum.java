package com.artisan.scheduler.model.enums;

import com.artisan.common.enums.BaseEnum;

/**
 * 任务类型枚举
 *
 * @author xz man
 * @since 1.8
 */
public enum TaskTypeEnum implements BaseEnum<Short> {
    SYNC_TASK((short) 0, "同步任务"),
    ASYNC_TASK((short) 1, "异步任务");

    private final Short code;
    private final String desc;

    TaskTypeEnum(Short code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    @Override
    public Short getCode() {
        return code;
    }

    @Override
    public String getDesc() {
        return desc;
    }
}
