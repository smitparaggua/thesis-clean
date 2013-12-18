package com.csg.warrior.dao;

import java.util.Map;

public interface ParentDao<T> {
    void save(T objectToSave);

    void merge(T objectToMerge);

    T queryUniqueResult(String hql, Map<String, Object> queryParameters);
}
