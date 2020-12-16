package com.artisan.scheduler.model.enums;

import com.artisan.common.enums.BaseEnum;

/**
 * 任务状态枚举
 *
 * @author xz man
 * @since 1.8
 */
public enum TaskStatusEnum implements BaseEnum<Short> {
    INIT((short) 1, "任务初始化"),
    READY((short) 2, "任务准备完成"),
    PROCESSING((short) 3, "任务执行中"),
    SUSPEND((short) 4, "任务挂起"),
    SUCCESS((short) 5, "任务执行成功"),
    ERROR((short) 6, "任务执行失败");

    private final Short code;
    private final String desc;

    TaskStatusEnum(Short code, String desc) {
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
