package com.lzy.headline.service;

import com.lzy.headline.pojo.NewsHeadline;
import com.lzy.headline.pojo.vo.HeadlineDetailVo;
import com.lzy.headline.pojo.vo.HeadlineQueryVo;

import java.util.Map;

public interface NewsHeadlineService {
    /**
     * 根据用户点击的关键字 或者返回的关键字查询
     * @param headlineQueryVo
     * @return
     */
    Map findPage(HeadlineQueryVo headlineQueryVo);

    /**
     * 查询头条详情的接口
     * @param hid
     * @return
     */
    HeadlineDetailVo findHeadLineDetail(int hid);

    int addNewsHeadline(NewsHeadline newsHeadline);

    /**
     *
     * @param hid
     * @return
     */
    NewsHeadline findByHid(Integer hid);

    int update(NewsHeadline newsHeadline);

    /**
     *
     * @param hid
     * @return
     */
    int removeByHid(Integer hid);
}
