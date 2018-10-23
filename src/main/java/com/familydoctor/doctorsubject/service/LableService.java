package com.familydoctor.doctorsubject.service;

import com.familydoctor.doctorsubject.entity.Lable;

import java.util.List;

public interface LableService {

    /**
     * 添加标签
     *
     * @param lable
     */
    int addLable(Lable lable);

    /**
     * 删除标签
     *
     * @param lable
     */
    int softDel(Lable lable);

    /**
     * 修改标签
     *
     * @param record
     */
    int updateById(Lable record);

    /**
     * 由Id查询对应的标签
     *
     * @param lable
     */
    Lable selectById(Lable lable);

    /**
     * 查询所有标签
     */
    List<Lable> selectAll();

    List<Lable> selectPage(Lable lable);

    /**
     * 多个LableName 查询对应的Lable
     *
     * @param lableName
     */
    List<Lable> selectByName(String[] lableName);

}
