package com.lzy.headline.test;

import com.lzy.headline.dao.BaseDao;
import com.lzy.headline.pojo.NewsType;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

public class Dao {
    private static BaseDao baseDao;
    @BeforeClass
    public static void initBaseDao(){
        baseDao =new BaseDao();

    }
    @Test
    public void testBase(){

        String sql ="select tid,tname from news_type";
        List<NewsType> sysUserList= baseDao.baseQuery(NewsType.class,sql);
        sysUserList.forEach(System.out::println);
    }
}
