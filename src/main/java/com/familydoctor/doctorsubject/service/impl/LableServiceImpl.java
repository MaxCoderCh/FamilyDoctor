package com.familydoctor.doctorsubject.service.impl;

import com.familydoctor.doctorsubject.entity.Lable;
import com.familydoctor.doctorsubject.mapper.LableMapper;
import com.familydoctor.doctorsubject.service.LableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LableServiceImpl implements LableService {

    @Autowired
    private LableMapper lableMapper;

    /**
     * 添加标签
     *
     * @param lable
     * @return lableMapper.insert(lable)
     */
    @Override
    public int addLable(Lable lable) {
        return lableMapper.insertSelective(lable);
    }

    /**
     * 删除标签
     *
     * @param lable
     * @return lableMapper.softDelete(lable.getId ())
     */
    @Override
    public int softDel(Lable lable) {
        return lableMapper.softDelete(lable);
    }

    /**
     * 修改标签
     *
     * @param record
     * @return lableMapper.updateByPrimaryKey(record)
     */
    @Override
    public int updateById(Lable record) {
        return lableMapper.updateByPrimaryKeySelective(record);
    }

    /**
     * 由Id查询标签
     *
     * @param lable
     * @return lableMapper.selectByPrimaryKey(lable.getId ())
     */
    @Override
    public Lable selectById(Lable lable) {
        return lableMapper.selectByPrimaryKey(lable.getId());
    }

    /**
     * 查询所有标签
     *
     * @return lableMapper.selectRound()
     */
    @Override
    public List<Lable> selectAll() {
        return lableMapper.selectRound();
    }

    @Override
    public List<Lable> selectPage(Lable lable) {
        return lableMapper.selectPage(lable);
    }

    @Override
    public List<Lable> selectByName(String[] lableName) {
        return lableMapper.selectByName(lableName);
    }

}
