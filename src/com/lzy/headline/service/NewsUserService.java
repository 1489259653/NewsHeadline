package com.lzy.headline.service;

import com.lzy.headline.pojo.NewsUser;

public interface NewsUserService {
    /**
     * 通过username 查询用户信息
     * @param username 用户名
     * @return 用户NewsUser对象 ||或者 null 时 就是没有找到
     */
    NewsUser findByUsername(String username);

    NewsUser findByUid(Integer userId);

    Integer registUser(NewsUser registUser);
}
