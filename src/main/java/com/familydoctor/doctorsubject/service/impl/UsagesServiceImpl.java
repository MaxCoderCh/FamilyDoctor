package com.familydoctor.doctorsubject.service.impl;

import com.familydoctor.doctorsubject.entity.Usages;
import com.familydoctor.doctorsubject.mapper.UsagesMapper;
import com.familydoctor.doctorsubject.service.UsagesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsagesServiceImpl implements UsagesService {

    @Autowired
    private UsagesMapper usagesMapper;

    /**
     * 物理删除药品使用方法
     *
     * @param id
     * @return usagesMapper.deleteByPrimaryKey(id)
     */
    @Override
    public int deleteById(String id) {
        return usagesMapper.deleteByPrimaryKey(id);
    }

    /**
     * 删除药品使用方法
     *
     * @param usages
     * @return usagesMapper.softDelete(id)
     */
    @Override
    public int softDel(Usages usages) {
        return usagesMapper.softDelete(usages);
    }

    /**
     * 添加药品使用方法
     *
     * @param record
     * @return usagesMapper.insert(record)
     */
    @Override
    public int add(Usages record) {
        return usagesMapper.insertSelective(record);
    }

    /**
     * 选择性添加
     *
     * @param record
     * @return usagesMapper.insertSelective(record)
     */
    @Override
    public int addSelect(Usages record) {
        return usagesMapper.insertSelective(record);
    }

    /**
     * 由Id查询药品使用方法
     *
     * @param id
     * @return usagesMapper.selectByPrimaryKey(id)
     */
    @Override
    public Usages selectById(String id) {
        return usagesMapper.selectByPrimaryKey(id);
    }

    /**
     * 由Id更新药品使用方法
     *
     * @param record
     * @return usagesMapper.updateByPrimaryKey(record)
     */
    @Override
    public int updateById(Usages record) {
        return usagesMapper.updateByPrimaryKeySelective(record);
    }

    /**
     * 动态更新
     *
     * @param record
     * @return usagesMapper.updateByPrimaryKeySelective(record)
     */
    @Override
    public int updateByIdSelect(Usages record) {
        return usagesMapper.updateByPrimaryKeySelective(record);
    }
}
