package com.lzy.headline.service.impl;


import com.lzy.headline.dao.NewsTypeDao;
import com.lzy.headline.dao.impl.NewsTypeDaoImpl;
import com.lzy.headline.pojo.NewsType;
import com.lzy.headline.service.NewsTypeService;

import java.util.List;

public class NewsTypeServiceImpl implements NewsTypeService {
    private NewsTypeDao typeDao =new NewsTypeDaoImpl();
    @Override
    public List<NewsType> findAll() {
        return typeDao.findAll();
    }
}