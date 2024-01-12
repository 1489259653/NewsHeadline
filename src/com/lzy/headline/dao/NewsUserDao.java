package com.lzy.headline.dao;

import com.lzy.headline.pojo.NewsUser;

public interface NewsUserDao {
    /**
     *  通过username 返回 数据库中查询的 数据 并封装为NewsUser的对象
     * @return
     */
    NewsUser findByUsername(String username);

    NewsUser findByUid(Integer uid);

    Integer registUser(NewsUser registUser);
}
