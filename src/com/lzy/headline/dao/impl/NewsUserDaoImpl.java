package com.lzy.headline.dao.impl;

import com.lzy.headline.dao.BaseDao;
import com.lzy.headline.dao.NewsUserDao;
import com.lzy.headline.pojo.NewsUser;

import java.util.List;

public class NewsUserDaoImpl extends BaseDao implements NewsUserDao{

    @Override
    public NewsUser findByUsername(String username) {
        String sql= """
                    select
                        uid,
                        username,
                        user_pwd userPwd,
                        nick_name nickName
                    from
                        news_user
                    where
                        username = ?
                    """;
        List<NewsUser> newsUsers =baseQuery(NewsUser.class,sql,username);

        return newsUsers!=null &&newsUsers.size()>0 ?newsUsers.get(0):null;
    }

    @Override
    public NewsUser findByUid(Integer uid) {
        String sql= """
                    select
                        uid,
                        username,
                        user_pwd userPwd,
                        nick_name nickName
                    from
                        news_user
                    where
                        uid = ?
                    """;
        List<NewsUser> newsUsers =baseQuery(NewsUser.class,sql,uid);

        return newsUsers!=null &&newsUsers.size()>0 ?newsUsers.get(0):null;

    }

    @Override
    public Integer registUser(NewsUser registUser) {
        String sql = """
                insert into news_user values(DEFAULT,?,?,?)
                """;

        return baseUpdate(sql,
                registUser.getUsername(),
                registUser.getUserPwd(),
                registUser.getNickName());
    }
}