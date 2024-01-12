package com.lzy.headline.dao;

import com.lzy.headline.pojo.NewsType;

import java.util.List;

public interface NewsTypeDao {
    /**
     * 查找多个
     * @return
     */
    List<NewsType> findAll();
}
