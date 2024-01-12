package com.lzy.headline.service;

import com.lzy.headline.pojo.NewsType;

import java.util.List;

public interface NewsTypeService {
    /**
     * 查询所有头条类型的方法
     * @return 多个头条类型 以List Type返回
     */
    List<NewsType> findAll();
}
