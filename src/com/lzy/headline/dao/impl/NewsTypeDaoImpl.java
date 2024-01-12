package com.lzy.headline.dao.impl;

import com.lzy.headline.dao.BaseDao;
import com.lzy.headline.dao.NewsTypeDao;
import com.lzy.headline.pojo.NewsType;

import java.util.List;

public class NewsTypeDaoImpl extends BaseDao implements NewsTypeDao{

    @Override
    public List<NewsType> findAll() {
        String sql ="select tid,tname from news_type";

        return baseQuery(NewsType.class,sql);
    }
}