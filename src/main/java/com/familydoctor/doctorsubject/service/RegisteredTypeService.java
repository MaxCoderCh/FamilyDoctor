package com.familydoctor.doctorsubject.service;

import com.familydoctor.doctorsubject.entity.RegisteredType;

import java.util.List;

public interface RegisteredTypeService {

    /**
     * 添加挂号类型
     *
     * @param registeredType
     */
    int addRegisteredType(RegisteredType registeredType);

    /**
     * 删除
     *
     * @param registeredType
     */
    int softDel(RegisteredType registeredType);

    /**
     * 修改
     *
     * @param record
     */
    int updateById(RegisteredType record);

    /**
     * 由Id查询挂号类别
     *
     * @param id
     */
    RegisteredType selectById(String id);

    /**
     * 查询科室下所有挂号类型
     *
     * @param id
     * @return
     */
    List<RegisteredType> selectByBranchId(String id);

    /**
     * 查询挂号类别列表
     */
    List<RegisteredType> selectAll();

    List<RegisteredType> selectPage(RegisteredType registeredType);

}
