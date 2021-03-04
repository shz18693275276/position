package com.ruoyi.position.service.impl;

import java.util.List;

import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.position.mapper.PositionMapper;
import com.ruoyi.position.domain.Position;
import com.ruoyi.position.service.IPositionService;

/**
 * 人力资源Service业务层处理
 *
 * @author anan
 * @date 2021-03-01
 */
@Service
public class PositionServiceImpl implements IPositionService {
    @Autowired
    private PositionMapper positionMapper;

    /**
     * 查询人力资源
     *
     * @param id 人力资源ID
     * @return 人力资源
     */
    @Override
    public Position selectPositionById(Long id) {
        return positionMapper.selectPositionById(id);
    }

    /**
     * 查询人力资源列表
     *
     * @param position 人力资源
     * @return 人力资源
     */
    @Override
    public List<Position> selectPositionList(Position position) {
        return positionMapper.selectPositionList(position);
    }

    /**
     * 新增人力资源
     *
     * @param position 人力资源
     * @return 结果
     */
    @Override
    public int insertPosition(Position position) {
        position.setCreateTime(DateUtils.getNowDate());
        return positionMapper.insertPosition(position);
    }

    /**
     * 修改人力资源
     *
     * @param position 人力资源
     * @return 结果
     */
    @Override
    public int updatePosition(Position position) {
        return positionMapper.updatePosition(position);
    }

    /**
     * 批量删除人力资源
     *
     * @param ids 需要删除的人力资源ID
     * @return 结果
     */
    @Override
    public int deletePositionByIds(Long[] ids) {
        return positionMapper.deletePositionByIds(ids);
    }

    /**
     * 删除人力资源信息
     *
     * @param id 人力资源ID
     * @return 结果
     */
    @Override
    public int deletePositionById(Long id) {
        return positionMapper.deletePositionById(id);
    }

    @Override
    public int updateByIds(Long[] ids) {
        return positionMapper.updateByIds(ids);
    }

    @Override
    public List<Position> selectPositions() {
        return positionMapper.selectPositions();
    }
}
