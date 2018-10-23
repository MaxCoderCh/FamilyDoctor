package com.familydoctor.doctorsubject.service.impl;

import com.familydoctor.doctorsubject.entity.Frequency;
import com.familydoctor.doctorsubject.mapper.FrequencyMapper;
import com.familydoctor.doctorsubject.service.FrequencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FrequencyServiceImpl implements FrequencyService {

    @Autowired
    private FrequencyMapper frequencyMapper;

    /**
     * 物理删除给药
     *
     * @param frequency
     */
    @Override
    public int deleteById(Frequency frequency) {
        return frequencyMapper.deleteByPrimaryKey(frequency.getId());
    }

    /**
     * 逻辑删除给药
     *
     * @param frequency
     */
    @Override
    public int softDel(Frequency frequency) {
        return frequencyMapper.softDelete(frequency);
    }

    /**
     * 添加给药
     *
     * @param record
     */
    @Override
    public int addFrequency(Frequency record) {
        return frequencyMapper.insertSelective(record);
    }

    /**
     * 由Id查询对应给药
     *
     * @param frequency
     */
    @Override
    public Frequency selectById(Frequency frequency) {
        return frequencyMapper.selectByPrimaryKey(frequency.getId());
    }

    /**
     * 更新给药
     *
     * @param frequency
     */
    @Override
    public int updateById(Frequency frequency) {
        return frequencyMapper.updateByPrimaryKeySelective(frequency);
    }

    /**
     * 查询给药列表
     *
     * @return frequencyMapper.selectRound()
     */
    @Override
    public List<Frequency> selectAll() {
        return frequencyMapper.selectRound();
    }

    @Override
    public List<Frequency> selectPage(Frequency frequency) {
        return frequencyMapper.selectPage(frequency);
    }
}
