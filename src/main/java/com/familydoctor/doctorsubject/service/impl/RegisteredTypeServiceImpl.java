package com.familydoctor.doctorsubject.service.impl;

import com.familydoctor.doctorsubject.entity.RegisteredType;
import com.familydoctor.doctorsubject.mapper.RegisteredTypeMapper;
import com.familydoctor.doctorsubject.service.RegisteredTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegisteredTypeServiceImpl implements RegisteredTypeService {

    @Autowired
    private RegisteredTypeMapper registeredTypeMapper;

    /**
     * 添加挂号类型
     *
     * @param registeredType
     * @return registeredTypeMapper.insert(registeredType)
     */
    @Override
    public int addRegisteredType(RegisteredType registeredType) {
        return registeredTypeMapper.insertSelective(registeredType);
    }

    /**
     * 删除
     *
     * @param registeredType
     * @return registeredTypeMapper.softDelete(id)
     */
    @Override
    public int softDel(RegisteredType registeredType) {
        return registeredTypeMapper.softDelete(registeredType);
    }

    /**
     * 修改
     *
     * @param record
     * @return registeredTypeMapper.updateByPrimaryKey(record)
     */
    @Override
    public int updateById(RegisteredType record) {
        return registeredTypeMapper.updateByPrimaryKeySelective(record);
    }

    /**
     * 查询挂号类别
     *
     * @param id
     * @return id
     */
    @Override
    public RegisteredType selectById(String id) {
        return registeredTypeMapper.selectByPrimaryKey(id);
    }

    /**
     * 查询Branch下所有挂号类型
     *
     * @param id
     * @return registeredTypeMapper.selectByBranch(id)
     */
    @Override
    public List<RegisteredType> selectByBranchId(String id) {
        return registeredTypeMapper.selectByBranch(id);
    }

    /**
     * 查询列表
     *
     * @return registeredTypeMapper.selectAllRegisteredType()
     */
    @Override
    public List<RegisteredType> selectAll() {
        return registeredTypeMapper.selectAllRegisteredType();
    }

    @Override
    public List<RegisteredType> selectPage(RegisteredType registeredType) {
        return registeredTypeMapper.selectPage(registeredType);
    }
}
