package com.ruoyi.position.service;

import java.util.List;

import com.ruoyi.position.domain.Position;

/**
 * 人力资源Service接口
 *
 * @author anan
 * @date 2021-03-01
 */
public interface IPositionService {
    /**
     * 查询人力资源
     *
     * @param id 人力资源ID
     * @return 人力资源
     */
    public Position selectPositionById(Long id);

    /**
     * 查询人力资源列表
     *
     * @param position 人力资源
     * @return 人力资源集合
     */
    public List<Position> selectPositionList(Position position);

    /**
     * 新增人力资源
     *
     * @param position 人力资源
     * @return 结果
     */
    public int insertPosition(Position position);

    /**
     * 修改人力资源
     *
     * @param position 人力资源
     * @return 结果
     */
    public int updatePosition(Position position);

    /**
     * 批量删除人力资源
     *
     * @param ids 需要删除的人力资源ID
     * @return 结果
     */
    public int deletePositionByIds(Long[] ids);

    /**
     * 删除人力资源信息
     *
     * @param id 人力资源ID
     * @return 结果
     */
    public int deletePositionById(Long id);

    /**
     * 根据Id批量修改
     * @param ids
     * @return
     */
    public int updateByIds(Long[] ids);

    /**
     * 查询带复核的档案
     * @return
     */
    public List<Position> selectPositions();
}
