
package com.artisan.scheduler.entity;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * 数据库映射类（表task_core_info）
 *
 * @author generator
 * @since 1.8
 */
@Data
public class TaskCoreInfoEntity {

    /**
     * 任务表物理主键
     */
    private Integer id;

    /**
     * 任务唯一标识
     */
    private String taskId;

    /**
     * 服务名
     */
    private String serviceName;

    /**
     * 任务优先级1-10，数字越大优先级越低
     */
    private Integer priority;

    /**
     * 任务类型（0：同步任务，1：异步任务）
     */
    private String taskType;

    /**
     * 任务状态（1：初始化，2：准备完毕，3：执行中，4；挂起，5：执行成功，6：执行失败）
     */
    private Integer taskStatus;

    /**
     * 计划时间
     */
    private LocalDateTime planTime;

    /**
     * 任务参数JSON
     */
    private String parameter;

    /**
     * 服务器创建时间
     */
    private LocalDateTime serverCreateTime;

    /**
     * 服务器更新时间
     */
    private LocalDateTime serverUpdateTime;

}

