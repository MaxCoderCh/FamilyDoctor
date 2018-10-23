package com.familydoctor.doctorsubject.service;

import com.familydoctor.doctorsubject.entity.Usages;

public interface UsagesService {
    int deleteById(String id);

    int softDel(Usages usages);

    int add(Usages record);

    int addSelect(Usages record);

    Usages selectById(String id);

    int updateByIdSelect(Usages record);

    int updateById(Usages record);
}
