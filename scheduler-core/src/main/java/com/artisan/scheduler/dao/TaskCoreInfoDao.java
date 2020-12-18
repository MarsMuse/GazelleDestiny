
package com.artisan.scheduler.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import com.artisan.scheduler.entity.TaskCoreInfoEntity;

import java.util.List;

/**
 * 数据库操作类（表task_core_info）
 *
 * @author generator
 * @since 1.8
 */
@Mapper
public interface TaskCoreInfoDao {

    /**
     * 创建表数据
     *
     * @param entity 表数据
     * @return 受影响行数
     */
    int insert(TaskCoreInfoEntity entity);

    /**
     * 批量创建表数据
     *
     * @param list 表数据列表
     * @return 受影响行数
     */
    int batchInsert(@Param("list") List<TaskCoreInfoEntity> list);

    /**
     * 更新表数据，并发高的情况下应避免使用该方法修改数据，或者应尽量使用独占更新
     *
     * @param entity 表数据
     * @return 受影响行数
     */
    int updateById(TaskCoreInfoEntity entity);

    /**
     * 更新表数据不为空字段. 需关注有默认值的字段
     *
     * @param entity 表数据
     * @return 受影响行数
     */
    int updateByIdSelective(TaskCoreInfoEntity entity);

    /**
     * 通过主键删除表数据
     *
     * @param id 删除的主键
     */
    int deleteById(@Param("id") Integer id);

    /**
     * 独占获取指定表数据
     *
     * @param id 需要查找记录的主键
     * @return null
     */
    TaskCoreInfoEntity lockById(@Param("id") Integer id);

    /**
     * 获取表数据
     *
     * @param id 需要查找记录的主键
     * @return null
     */
    TaskCoreInfoEntity findById(@Param("id") Integer id);

    /**
     * 获取满足条件的所有表数据
     *
     * @param entity 不为空字段作为查询条件查询
     * @return null列表
     */
    List<TaskCoreInfoEntity> findAll(TaskCoreInfoEntity entity);

}

