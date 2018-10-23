package com.familydoctor.doctorsubject.service;

import com.familydoctor.doctorsubject.entity.LableClass;

import java.util.List;

public interface LableClassService {

    /**
     * 删除标签类别
     *
     * @param lableClass
     */
    int softDel(LableClass lableClass);

    /**
     * 添加标签类别
     *
     * @param record
     */
    int addLableClass(LableClass record);

    /**
     * 由Id查询标签类别
     *
     * @param id
     */
    LableClass selectById(String id);

    /**
     * 查询所有标签类别
     */
    List<LableClass> selectAll();

    /**
     * 更新标签类别
     *
     * @param record
     */
    int updateById(LableClass record);

    List<LableClass> selectPage(LableClass lableClass);
}
