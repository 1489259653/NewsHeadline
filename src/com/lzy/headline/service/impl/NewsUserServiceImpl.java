package com.lzy.headline.service.impl;

import com.lzy.headline.dao.NewsUserDao;
import com.lzy.headline.dao.impl.NewsUserDaoImpl;
import com.lzy.headline.pojo.NewsUser;
import com.lzy.headline.service.NewsUserService;
import com.lzy.headline.util.MD5Util;

public class NewsUserServiceImpl implements NewsUserService {
private NewsUserDao  newsUserDao =new NewsUserDaoImpl();
    @Override
    public NewsUser findByUsername(String username) {

        return newsUserDao.findByUsername(username);
    }

    @Override
    public NewsUser findByUid(Integer userId) {
        return newsUserDao.findByUid(userId);
    }

    @Override
    public Integer registUser(NewsUser registUser) {
        //将明文密码转换为密文密码
        registUser.setUserPwd(MD5Util.encrypt(registUser.getUserPwd()));
        return newsUserDao.registUser(registUser);
    }
}
