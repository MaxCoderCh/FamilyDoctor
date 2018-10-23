package com.familydoctor.doctorsubject.service.impl;

import com.familydoctor.doctorsubject.entity.Lable;
import com.familydoctor.doctorsubject.entity.LableClass;
import com.familydoctor.doctorsubject.mapper.LableClassMapper;
import com.familydoctor.doctorsubject.service.LableClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LableClassServiceImpl implements LableClassService {

    @Autowired
    private LableClassMapper lableClassMapper;

    /**
     * 添加标签类别
     *
     * @param record
     * @return lableClassMapper.insert(record)
     */
    @Override
    public int addLableClass(LableClass record) {
        return lableClassMapper.insertSelective(record);
    }

    /**
     * 删除标签类别
     *
     * @param lableClass
     * @return lableClassMapper.softDelete(lableClass.getId ())
     */
    @Override
    public int softDel(LableClass lableClass) {
        return lableClassMapper.softDelete(lableClass);
    }

    /**
     * 更新标签列表
     *
     * @param record
     * @return lableClassMapper.updateByPrimaryKey(record)
     */
    @Override
    public int updateById(LableClass record) {
        return lableClassMapper.updateByPrimaryKeySelective(record);
    }

    /**
     * 由Id查询对应的标签类别
     *
     * @param id
     * @return lableClassMapper.selectByPrimaryKey(lableClass.getId ())
     */
    @Override
    public LableClass selectById(String id) {
        return lableClassMapper.selectByPrimaryKey(id);
    }

    /**
     * 查询所有标签类
     *
     * @return lableClassMapper.selectRound()
     */
    @Override
    public List<LableClass> selectAll() {
        return lableClassMapper.selectRound();
    }

    @Override
    public List<LableClass> selectPage(LableClass lableClass) {
        return lableClassMapper.selectPage(lableClass);
    }
}
