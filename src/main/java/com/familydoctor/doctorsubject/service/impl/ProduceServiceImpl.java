package com.familydoctor.doctorsubject.service.impl;

import com.familydoctor.doctorsubject.entity.Produce;
import com.familydoctor.doctorsubject.mapper.ProduceMapper;
import com.familydoctor.doctorsubject.service.ProduceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProduceServiceImpl implements ProduceService {

    @Autowired
    private ProduceMapper produceMapper;

    /**
     * 医生套餐添加
     *
     * @param record
     * @return produceMapper.insertSelective(record)
     */
    @Override
    public int insertByDoctor(Produce record) {
        return produceMapper.insertSelective(record);
    }

    /**
     * 由医生Id查询套餐列表
     *
     * @param id
     * @return produceMapper.selectByDoctor(id)
     */
    @Override
    public List<Produce> selectByDoctorId(String id) {
        return produceMapper.selectByDoctor(id);
    }

    /**
     * Id查询套餐
     *
     * @param id
     * @return produceMapper.selectByPrimaryKey(id)
     */
    @Override
    public Produce selectById(String id) {
        return produceMapper.selectByPrimaryKey(id);
    }

    /**
     * 删除套餐
     *
     * @param produce
     * @return produceMapper.softDelete(id)
     */
    @Override
    public int softDel(Produce produce) {
        return produceMapper.softDelete(produce);
    }


    /**
     * 更新套餐
     *
     * @param record
     * @return produceMapper.updateByPrimaryKey(record)
     */
    @Override
    public int updateById(Produce record) {
        return produceMapper.updateByPrimaryKeySelective(record);
    }

    /**
     * 查询同一doctor下所有produce的id
     *
     * @param id
     * @return produceMapper.selectByDoctorIdGetId(id)
     */
    @Override
    public List<String> selectByDoctorToId(String id) {
        return produceMapper.selectByDoctorIdGetId(id);
    }
}
